package com.factoria.coders.controllers;

import com.factoria.coders.services.ILikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final ILikeService iLikeService;

    public LikeController(ILikeService iLikeService) {
        this.iLikeService = iLikeService;
    }

    @GetMapping("/products/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<?> toggleLike(@PathVariable("id") Long id) {
        iLikeService.toggleLike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
