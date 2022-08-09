package com.factoria.coders.repositories;

import com.factoria.coders.models.Like;
import com.factoria.coders.models.Product;
import com.factoria.coders.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndProduct(@NonNull User user, @NonNull Product product);

    Optional<Like> findByUser_IdAndProduct_Id(Long id, Long id1);


    @Override
    void deleteById(Long aLong);
}
