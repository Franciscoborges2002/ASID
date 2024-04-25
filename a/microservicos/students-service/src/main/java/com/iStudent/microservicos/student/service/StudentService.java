package com.iStudent.microservicos.student.service;

import com.iStudent.microservicos.student.dto.MarkDTO;
import com.iStudent.microservicos.student.dto.StudentDTO;
import com.iStudent.microservicos.student.model.Mark;
import com.iStudent.microservicos.student.model.Parent;
import com.iStudent.microservicos.student.model.Student;
import com.iStudent.microservicos.student.model.Town;
import com.iStudent.microservicos.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper mapper;

    public List<StudentDTO> getAllStudents() {
        return studentRepository
                .findAll()
                .stream()
                .map(this::mapToStudentDTO)
                .toList();
    }

    public Optional<StudentDTO> getStudentById(Long studentId) {
        return studentRepository
                .findById(studentId)
                .map(this::mapToStudentDTO);
    }

    public long addStudent(StudentDTO studentDTO) {
        Town townToMap = townService.findByTownId(studentDTO.getTown().getId());

        Parent parentToMap = parentService.findParentById(studentDTO.getParent() != null
                ? studentDTO.getParent().getId()
                : 0);

        Student student = mapper.map(studentDTO, Student.class);

        student.setTown(townToMap);
        student.setParent(parentToMap);

        studentRepository.save(student);

        return student.getId();
    }

    public boolean addMarkToStudent(Long studentId, MarkDTO markDTO) {
        Mark markToAdd = markRepository.findByMark(markDTO.getMark());
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.addMark(markToAdd);
            studentRepository.save(student);
            return true;
        }

        return false;
    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }



    private StudentDTO mapToStudentDTO(Student student) {
        return mapper.map(student, StudentDTO.class);
    }
}
