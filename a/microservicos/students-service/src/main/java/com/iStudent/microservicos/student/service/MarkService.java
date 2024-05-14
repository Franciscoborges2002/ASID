package com.iStudent.microservicos.student.service;

import com.iStudent.microservicos.student.dto.MarkDTO;
import com.iStudent.microservicos.student.dto.StudentDTO;
import com.iStudent.microservicos.student.model.Mark;
import com.iStudent.microservicos.student.repository.MarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class MarkService {

    private final MarkRepository markRepository;
    private final ModelMapper mapper;

    public List<MarkDTO> getAllMarks() {
        return markRepository.findAll().stream().map(this::mapToMarkDTO).toList();
    }

    private MarkDTO mapToMarkDTO(Mark mark) {
        return mapper.map(mark, MarkDTO.class);
    }
}
