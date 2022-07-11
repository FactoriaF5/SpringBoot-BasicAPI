package com.factoria.coders.dtos;

import lombok.Data;

@Data
public class CommentRequestDto {

    private Long id;
    private String comment;
    private Long productId;

}
