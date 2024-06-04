package com.iStudent.microservicos.town_country.repository;

import com.iStudent.microservicos.town_country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
