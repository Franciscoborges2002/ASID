package com.istudent.microservicos.parent.repository;

import com.istudent.microservicos.parent.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    //Optional<Parent> findByEmail(String email);

}
