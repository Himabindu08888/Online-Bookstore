package com.bookstore.app.service;

import com.bookstore.app.model.Book;
import com.bookstore.app.model.CartItem;
import com.bookstore.app.model.User;
import com.bookstore.app.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final BookService bookService;

    public CartService(CartItemRepository cartItemRepository, BookService bookService) {
        this.cartItemRepository = cartItemRepository;
        this.bookService = bookService;
    }

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public void addToCart(User user, Long bookId, int quantity) {
        Book book = bookService.findById(bookId);
        CartItem item = cartItemRepository.findByUserAndBook(user, book)
                .orElse(new CartItem(user, book, 0));
        int newQty = item.getQuantity() == null ? quantity : item.getQuantity() + quantity;
        item.setQuantity(Math.min(newQty, book.getStock()));
        cartItemRepository.save(item);
    }

    public void updateQuantity(Long cartItemId, int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
        if (quantity <= 0) {
            cartItemRepository.delete(item);
        } else {
            item.setQuantity(Math.min(quantity, item.getBook().getStock()));
            cartItemRepository.save(item);
        }
    }

    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public double getCartTotal(User user) {
        return getCartItems(user).stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }
}
