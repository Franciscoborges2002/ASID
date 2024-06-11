package com.iStudent.microservicos.studentmark.repository;

import com.iStudent.microservicos.studentmark.model.Mark;
import com.iStudent.microservicos.studentmark.model.enums.MarkEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    Mark findByMark(MarkEnum mark);
}
