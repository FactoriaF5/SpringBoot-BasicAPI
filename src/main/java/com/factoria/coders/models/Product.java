package com.factoria.coders.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String img;
    @JsonInclude
    @Transient
    @Builder.Default
    private boolean liked = false;

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

    public void addLike(Like like) {
        if (this != like.getProduct()) return;

        this.likes.add(like);
    }

    public boolean isLovedBy(User user) {
        if (user == null) this.liked = false;
        var opLike = this.likes.stream().filter(like -> like.getUser() == user)
                .findFirst();
        if (opLike.isPresent()) {
            this.liked = true;
            return true;
        }
        return false;
    }
}
