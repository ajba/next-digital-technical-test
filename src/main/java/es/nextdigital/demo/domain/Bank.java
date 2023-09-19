package es.nextdigital.demo.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank")
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
