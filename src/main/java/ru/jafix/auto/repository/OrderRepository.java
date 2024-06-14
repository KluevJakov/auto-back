package ru.jafix.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jafix.auto.entity.Order;
import ru.jafix.auto.entity.User;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUser_Id(UUID id);

    List<Order> findByUser(User user);
}