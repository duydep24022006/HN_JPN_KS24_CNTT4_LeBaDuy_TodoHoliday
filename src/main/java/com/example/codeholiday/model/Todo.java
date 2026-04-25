package com.example.codeholiday.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Table(name = "todo")
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "status")
    private Status status;

    @Column(name = "priority")
    private Priority priority;

}
