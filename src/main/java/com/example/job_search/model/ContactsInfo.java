package com.example.job_search.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contacts_info")
public class ContactsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ContactTypes type;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resumes resume;

    @Column(name = "contact_value")
    private String value;
}
