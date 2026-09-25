package com.bookstore.app.controller;

import com.bookstore.app.config.AuthUtil;
import com.bookstore.app.model.User;
import com.bookstore.app.service.CartService;
import com.bookstore.app.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @GetMapping("/checkout")
    public String checkoutForm(Authentication authentication, Model model) {
        User user = AuthUtil.currentUser(authentication);
        model.addAttribute("cartItems", cartService.getCartItems(user));
        model.addAttribute("total", cartService.getCartTotal(user));
        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String shippingAddress,
                              Authentication authentication,
                              Model model) {
        User user = AuthUtil.currentUser(authentication);
        try {
            var order = orderService.placeOrder(user, shippingAddress);
            return "redirect:/orders/" + order.getId() + "/confirmation";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cartItems", cartService.getCartItems(user));
            model.addAttribute("total", cartService.getCartTotal(user));
            return "checkout";
        }
    }

    @GetMapping("/orders/{id}/confirmation")
    public String confirmation(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order-confirmation";
    }

    @GetMapping("/orders")
    public String myOrders(Authentication authentication, Model model) {
        User user = AuthUtil.currentUser(authentication);
        model.addAttribute("orders", orderService.getOrdersForUser(user));
        return "orders";
    }
}
