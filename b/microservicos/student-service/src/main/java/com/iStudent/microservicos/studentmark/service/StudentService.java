package com.iStudent.microservicos.studentmark.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iStudent.microservicos.studentmark.controller.TownCallerController;
import com.iStudent.microservicos.studentmark.dto.MarkDTO;
import com.iStudent.microservicos.studentmark.dto.StudentDTO;
import com.iStudent.microservicos.studentmark.model.Mark;
import com.iStudent.microservicos.studentmark.model.Student;
import com.iStudent.microservicos.studentmark.model.Town;
import com.iStudent.microservicos.studentmark.repository.MarkRepository;
import com.iStudent.microservicos.studentmark.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    private final MarkRepository markRepository;

    private final ModelMapper mapper;

    private TownCallerController townCallerController;

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

    public long addStudent(StudentDTO studentDTO) throws JsonProcessingException {
        try{
            String townResponse = townCallerController.findTownByName(studentDTO.getTown());

            ObjectMapper objectMapper = new ObjectMapper();
            Town town = objectMapper.readValue(townResponse, Town.class);

            //If exists the town in the town microservice
            if(town.getName().equals(studentDTO.getTown())){

            /*Parent parentToMap = parentService.findParentById(studentDTO.getParent() != null
                ? studentDTO.getParent().getId()
                : 0);*/

                Student student = mapper.map(studentDTO, Student.class);

                student.setTown(town.getName());
                //student.setParent(parentToMap);

                studentRepository.save(student);

                return student.getId();
            }


        }catch(Exception ex){
            throw new IllegalArgumentException("The town you inserted doesnt exist");
        }
        throw new IllegalArgumentException("The town you inserted doesnt exist");
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
        //return false;
    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    private StudentDTO mapToStudentDTO(Student student) {
        return mapper.map(student, StudentDTO.class);
    }


}
