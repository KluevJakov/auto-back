package ru.jafix.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jafix.auto.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLogin(String login);
}