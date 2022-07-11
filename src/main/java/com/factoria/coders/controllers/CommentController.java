package com.factoria.coders.controllers;

import com.factoria.coders.dtos.CommentRequestDto;
import com.factoria.coders.models.Comment;
import com.factoria.coders.repositories.ICommentRepository;
import com.factoria.coders.repositories.IProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    ICommentRepository commentRepository;
    IProductRepository productRepository;

    public CommentController(ICommentRepository commentRepository, IProductRepository productRepository) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/comments")
    List<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    @PostMapping("/comments")
    Comment createComment(@RequestBody CommentRequestDto commentRequestDto) {
        var newComment = new Comment();
        newComment.setComment(commentRequestDto.getComment());
        newComment.setId(commentRequestDto.getId());
        newComment.setProduct(this.productRepository.findById(commentRequestDto.getProductId()).get());

        return this.commentRepository.save(newComment);
    }
}
