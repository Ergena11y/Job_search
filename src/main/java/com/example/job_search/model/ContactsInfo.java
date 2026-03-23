package com.example.job_search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactsInfo {
    private Integer id;
    private Integer typeId;
    private Integer resumeId;
    private String value;
}
