package ru.jafix.auto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.jafix.auto.entity.Order;
import ru.jafix.auto.entity.User;
import ru.jafix.auto.repository.OrderRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrdersController {

    @Autowired
    protected OrderRepository orderRepository;

    @GetMapping("/orders")
    public ResponseEntity<?> submitForm() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders;

        if (!user.getRole().getName().equals("ADMIN")) {
            orders = orderRepository.findByUser(user);
        } else {
            orders = orderRepository.findAll();
        }

        return ResponseEntity.ok(orders);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<String> processOrder(@PathVariable UUID id, @RequestBody Map<String, String> request) {
        String status = request.get("status");
        Order order = orderRepository.findById(id).get();
        order.setStatus(status);
        orderRepository.save(order);
        return ResponseEntity.ok("Order processed successfully");
    }
}
