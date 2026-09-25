package com.bookstore.app.repository;

import com.bookstore.app.model.Book;
import com.bookstore.app.model.CartItem;
import com.bookstore.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndBook(User user, Book book);
    void deleteByUser(User user);
}
