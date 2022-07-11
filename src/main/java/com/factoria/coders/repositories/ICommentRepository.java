package com.factoria.coders.repositories;

import com.factoria.coders.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository <Comment,Long> {
}
