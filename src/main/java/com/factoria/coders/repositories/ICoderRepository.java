package com.factoria.coders.repositories;

import com.factoria.coders.models.Coder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICoderRepository extends JpaRepository<Coder, Long> {
}
