package ru.jafix.auto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jafix.auto.entity.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}