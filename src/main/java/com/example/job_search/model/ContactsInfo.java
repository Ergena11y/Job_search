package com.example.job_search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactsInfo {
    private int id;
    private ContactTypes typeId;
    private Resumes resumeId;
    private String value;
}
