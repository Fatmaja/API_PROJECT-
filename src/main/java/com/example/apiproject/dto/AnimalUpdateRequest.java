package com.example.apiproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnimalUpdateRequest {
    private String description;
    private String location;
    private String age;
}
