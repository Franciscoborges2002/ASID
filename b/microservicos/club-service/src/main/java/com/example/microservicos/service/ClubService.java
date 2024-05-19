package com.example.microservicos.service;

import com.example.microservicos.dto.ClubDTO;
import com.example.microservicos.model.Club;
import com.example.microservicos.model.Student;
import com.example.microservicos.repository.ClubRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    private final ModelMapper mapper;
    public List<ClubDTO> getAllClubs() {
        return clubRepository.
                findAll().
                stream().
                map(this::mapToClubDTO).
                toList();
    }

    public Optional<ClubDTO> getClubById(Long clubId) {
        return clubRepository.
                findById(clubId).
                map(this::mapToClubDTO);
    }

    public Club addClub(ClubDTO clubDTO) {
        Club club = mapper.map(clubDTO, Club.class);

        clubRepository.save(club);

        return club;
    }

    @Transactional
    public void deleteClubById(Long clubId) {
        /*Optional<Club> optionalClub = clubRepository.findById(clubId);


        if (optionalClub.isPresent()){
            Club club = optionalClub.get();
            Optional<List<Student>> studentsOptional = studentRepository.findByClubs(club);
            for (Student student : studentsOptional.get()){
                student.removeClub(club);
            }
        }

        clubRepository.deleteById(clubId);*/
    }


    private ClubDTO mapToClubDTO(Club club) {
        return mapper.map(club, ClubDTO.class);
    }


}
