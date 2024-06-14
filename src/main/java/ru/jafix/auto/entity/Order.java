package ru.jafix.auto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String name;
    private String phone;
    @Column(name = "qfrom")
    private String from;
    @Column(name = "qto")
    private String to;
    private String cargoWeight;
    private String cargoVolume;
    private String products;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
