package epsi.exam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; 

@Entity
public class Decoration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
} 