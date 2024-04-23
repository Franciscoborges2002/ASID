package com.iStudent.microservicos.town.repository;

import com.iStudent.microservicos.town.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
