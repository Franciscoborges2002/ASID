package com.example.microservicos.model.validation;

import com.example.microservicos.repository.ClubRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueClubNameValidator implements ConstraintValidator<UniqueClubName, String> {
    private final ClubRepository clubRepository;

    @Autowired
    public UniqueClubNameValidator(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public boolean isValid(String clubName, ConstraintValidatorContext context) {
        return clubRepository.
                findByName(clubName).
                isEmpty();
    }
}
