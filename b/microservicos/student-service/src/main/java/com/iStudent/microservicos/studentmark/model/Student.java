package com.iStudent.microservicos.studentmark.model;

import com.iStudent.microservicos.studentmark.model.base.BasePersonEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student extends BasePersonEntity {
    /*@ManyToOne
    private Parent parent;*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "students_clubs",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "club_id", referencedColumnName = "id")
    )
    private Set<Club> clubs;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "students_marks",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "mark_id", referencedColumnName = "id")
    )
    private List<Mark> marks;

    @Column(name = "enroll_date", nullable = false)
    private LocalDate enrollDate;

    public void addMark(Mark mark) {
        marks.add(mark);
    }
}
