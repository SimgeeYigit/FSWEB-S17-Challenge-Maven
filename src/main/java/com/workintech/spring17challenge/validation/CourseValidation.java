package com.workintech.spring17challenge.validation;

import com.workintech.spring17challenge.entity.Course;
import com.workintech.spring17challenge.exceptions.ApiException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class CourseValidation {

    public static void isIdValid(Integer id) {
        if (id == 0 || id < 0) {
            throw new ApiException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void isNameValid(String name) {
        if (name == null || name.isBlank()) {
            throw new ApiException("Name cannot be null or empty!", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateCourse(Course course) {
        if (course == null) {
            throw new ApiException("Course name cannot be null!", HttpStatus.BAD_REQUEST);
        } else if (course.getName() == null) {
            throw new ApiException("Course name cannot be null!", HttpStatus.BAD_REQUEST);
        } else if (course.getId() == null) {
            throw new ApiException("Course name cannot be null!", HttpStatus.BAD_REQUEST);
        } else if (course.getGrade() == null) {
            throw new ApiException("Course name cannot be null!", HttpStatus.BAD_REQUEST);
        } else if (course.getCredit() == null) {
            throw new ApiException("Course name cannot be null!", HttpStatus.BAD_REQUEST);
        }
    }
}
