package ru.jafix.auto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.jafix.auto.entity.FormRequestDTO;
import ru.jafix.auto.entity.Order;
import ru.jafix.auto.entity.User;
import ru.jafix.auto.repository.OrderRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

}
