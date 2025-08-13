package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.entity.Course;
import com.workintech.spring17challenge.entity.CourseResponse;
import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.*;
import com.workintech.spring17challenge.validation.CalculateGpa;
import com.workintech.spring17challenge.validation.CourseValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/courses")
public class CourseController {
    List<Course> courses;

    private CourseGpa lowCourseGpa;
    private CourseGpa mediumCourseGpa;
    private CourseGpa highCourseGpa;

    @Autowired
    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowCourseGpa,
                            @Qualifier("mediumCourseGpa") CourseGpa mediumCourseGpa,
                            @Qualifier("highCourseGpa") CourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
    }

    @PostConstruct
    public void init() {
        courses = new ArrayList<>();
    }

    @GetMapping
    public List<Course> getAll(){
        return courses;
    }

    @GetMapping("/{name}")
    public Course getByName(@PathVariable String name){
        CourseValidation.isNameValid(name);
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(name)) {
                return course;
            }
        }
        throw new ApiException("Name couldn't find!" , HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse save(@RequestBody Course course) {
        CourseValidation.validateCourse(course);
        courses.add(course);
        int totalGpa = CalculateGpa.calculateGpa(course);
        return new CourseResponse(course, totalGpa);
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Integer id, @RequestBody Course course){
        CourseValidation.isIdValid(id);
        CourseValidation.validateCourse(course);
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                courses.set(i, course);
                int totalGpa = CalculateGpa.calculateGpa(course);
                return new CourseResponse(course, totalGpa);
            }
        }
        throw new ApiException("Course not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public Course delete(@PathVariable int id) {
        Course deleteCourse = null;
        for (Course course : courses) {
            if (course.getId() == id) {
                deleteCourse = course;
                break;
            }
        }
        CourseValidation.validateCourse(deleteCourse);
        courses.remove(deleteCourse);
        return deleteCourse;
    }
}






