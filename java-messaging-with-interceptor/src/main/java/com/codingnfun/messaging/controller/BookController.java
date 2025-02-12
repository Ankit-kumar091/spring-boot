package com.codingnfun.messaging.controller;

import com.codingnfun.messaging.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BookController {
    private static Map<Integer, Book> boolStore = new HashMap<>();
    static {
        Book book1 = new Book(123, "Atomic Habits", "Amit");
        boolStore.put(book1.getId(), book1);
        Book book2 = new Book(456, "Love of life", "Elesha");
        boolStore.put(book2.getId(), book2);
    }

    @RequestMapping(value = "/")
    public String welcome() {
        return "<h2> Welcome to book store</h2>";
    }

    @RequestMapping(value = "/book")
    public ResponseEntity getBook(@RequestParam("bookId") Integer bookId) {
        System.out.println("Retrieving book...");
        return new ResponseEntity<>(boolStore.get(bookId), HttpStatus.OK);
    }
}
