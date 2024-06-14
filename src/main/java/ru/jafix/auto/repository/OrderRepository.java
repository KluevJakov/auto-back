package ru.jafix.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jafix.auto.entity.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}