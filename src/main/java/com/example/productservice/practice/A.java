package com.example.productservice.practice;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "a", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<B> b;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<C> c;
}
