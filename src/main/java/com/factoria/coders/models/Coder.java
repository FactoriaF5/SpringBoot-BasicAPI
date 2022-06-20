package com.factoria.coders.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Data
@Entity
@Table(name = "coder")
@AllArgsConstructor
@NoArgsConstructor
public class Coder {
    private  String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
