package com.iStudent.microservicos.studentmark.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iStudent.microservicos.studentmark.dto.*;
import com.iStudent.microservicos.studentmark.extern.ClubCallerService;
import com.iStudent.microservicos.studentmark.extern.TownCallerService;
import com.iStudent.microservicos.studentmark.model.Club;
import com.iStudent.microservicos.studentmark.model.Mark;
import com.iStudent.microservicos.studentmark.model.Student;
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

    private final TownCallerService townCallerController;

    private final ClubCallerService clubCallerController;

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

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = new Student();
        StudentDTO studentDTOReturn = new StudentDTO();

        System.out.println(studentDTO.toString());

        try{
            TownDTO townResponse = townCallerController.findTownByName(studentDTO.getTown());

            System.out.println(townResponse);

            //If exists the town in the town microservice
            if(townResponse.getName().equals(studentDTO.getTown())){

                try {
                    student = mapper.map(studentDTO, Student.class);

                    //Set town to student
                    student.setTown(townResponse.getName());

                    System.out.println(student);

                    //Save to repos
                    studentRepository.save(student);

                    studentDTOReturn = mapper.map(student, StudentDTO.class);
                }catch(Exception e){
                    throw new IllegalArgumentException("Error while creating student");
                }

                return studentDTOReturn;
            }

        }catch(Exception ex){
            throw new IllegalArgumentException("The town you inserted doesnt exist");
        }

        throw new IllegalArgumentException("Couldn't add student!");
    }

    public ClubStudentDTO attachClubToStudent(Long studentId, Long clubId) throws JsonProcessingException {
        //Create var to return
        ClubStudentDTO toRespond = new ClubStudentDTO();

        //Verify if student exists
        Optional<Student> optStudent = studentRepository.findById(studentId);

        //If student doesn't exist
        if(!optStudent.isPresent()){
            toRespond.setSuccess(false);
            toRespond.setMessage("Club doesnt exist!");
            return toRespond;
        }

        //get the actual object of the student
        Student student = optStudent.get();

        //Verify if the club exists with the id, send request to his service
        ClubDTO clubDTO = clubCallerController.findClubById(clubId);

        //Already has the student in the club, return false
        if(student.getClubs().contains(clubDTO)){
            toRespond.setSuccess(false);
            toRespond.setMessage("Student already belongs to this club!");
            return toRespond;
        }

        //Attach to a club the id from the dto
        Club club = new Club();
        club.setId(clubId);

        // Proceed with attaching the student to the club
        // Assuming you have a method to add students to the club
        student.getClubs().add(club);//getStudents().add(student);

        // Save the club after adding the student
        studentRepository.save(student);

        //Make the object to respond to the client
        toRespond.setSuccess(true);
        toRespond.setMessage("Student added to the club!");

        return toRespond;
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
