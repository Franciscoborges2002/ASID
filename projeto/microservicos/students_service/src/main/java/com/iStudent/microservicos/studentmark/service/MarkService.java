package com.iStudent.microservicos.studentmark.service;

import com.iStudent.microservicos.studentmark.dto.MarkDTO;
import com.iStudent.microservicos.studentmark.dto.StudentDTO;
import com.iStudent.microservicos.studentmark.model.Mark;
import com.iStudent.microservicos.studentmark.model.Student;
import com.iStudent.microservicos.studentmark.repository.MarkRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarkService {
    private final MarkRepository markRepository;

    private final ModelMapper mapper;

    public List<MarkDTO> getAllMarks() {
        return markRepository
                .findAll()
                .stream()
                .map(this::mapToMarkDTO)
                .toList();
    }

    private MarkDTO mapToMarkDTO(Mark mark) {
        return mapper.map(mark, MarkDTO.class);
    }
}
