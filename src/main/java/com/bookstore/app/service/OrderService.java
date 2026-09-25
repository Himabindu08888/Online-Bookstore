package com.bookstore.app.service;

import com.bookstore.app.model.*;
import com.bookstore.app.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final BookService bookService;

    public OrderService(OrderRepository orderRepository, CartService cartService, BookService bookService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.bookService = bookService;
    }

    @Transactional
    public Order placeOrder(User user, String shippingAddress) {
        List<CartItem> cartItems = cartService.getCartItems(user);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Your cart is empty.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(shippingAddress);

        double total = 0;
        for (CartItem ci : cartItems) {
            Book book = ci.getBook();
            if (ci.getQuantity() > book.getStock()) {
                throw new IllegalStateException("Not enough stock for: " + book.getTitle());
            }
            OrderItem orderItem = new OrderItem(book, ci.getQuantity(), book.getPrice());
            order.addItem(orderItem);
            total += orderItem.getSubtotal();
            bookService.reduceStock(book, ci.getQuantity());
        }
        order.setTotalAmount(total);

        Order saved = orderRepository.save(order);
        cartService.clearCart(user);
        return saved;
    }

    public List<Order> getOrdersForUser(User user) {
        return orderRepository.findByUserOrderByOrderDateDesc(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByOrderDateDesc();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public void updateStatus(Long orderId, OrderStatus status) {
        Order order = findById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }
}
