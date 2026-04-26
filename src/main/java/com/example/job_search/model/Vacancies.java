package com.example.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vacancies")

public class Vacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

   @ManyToOne
   @JoinColumn(name = "category_id")
    private Category category;


    private float salary;

    @Column(name = "exp_from")
    private Integer expFrom;

    @Column(name = "exp_to")
    private Integer expTo;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name =  "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "vacancy", fetch = FetchType.LAZY)
    private List<RespondedApplicants> respondedApplicants;


}
