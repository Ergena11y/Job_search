package com.example.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "responded_applicant_id")
    private RespondedApplicants respondedApplicants;


    private String content;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @Column(name = "sender_email")
    private  String senderEmail;

    @Column(name = "sender_role")
    private  String senderRole;

}
