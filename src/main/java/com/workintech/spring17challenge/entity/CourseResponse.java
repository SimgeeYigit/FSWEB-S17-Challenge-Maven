package com.workintech.spring17challenge.entity;

import com.workintech.spring17challenge.model.CourseGpa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResponse {
    private Course course;
    private Integer totalGpa;
}
