package com.factoria.coders.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private String description;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @JsonSerialize
    public int countComments() {
        return this.comments.size();
    }

    @ManyToOne

    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    @JsonSerialize
    public int likesCount() {
        return likes.size();

    }

}
