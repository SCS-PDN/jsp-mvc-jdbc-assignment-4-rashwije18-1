package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ==========================
    // 1. Show all courses (GET)
    // ==========================
    @GetMapping("/courses")
    public String listCourses(Model model) {

        String sql = "SELECT * FROM courses";
        List<Map<String, Object>> courses = jdbcTemplate.queryForList(sql);

        model.addAttribute("courses", courses);
        return "courses";
    }

    // ==========================
    // 2. Register course (POST)
    // ==========================
    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable("courseId") int courseId,
                                 HttpSession session) {

        // Get logged-in student email from session
        String email = (String) session.getAttribute("email");

        if (email == null) {
            return "redirect:/login";
        }

        // Get student_id using email
        String getStudentSql = "SELECT student_id FROM students WHERE email = ?";
        int studentId = jdbcTemplate.queryForObject(getStudentSql, Integer.class, email);

        // Insert registration
        String insertSql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, CURDATE())";
        jdbcTemplate.update(insertSql, studentId, courseId);

        return "success";
    }
}package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ==========================
    // 1. Show all courses (GET)
    // ==========================
    @GetMapping("/courses")
    public String listCourses(Model model) {

        String sql = "SELECT * FROM courses";
        List<Map<String, Object>> courses = jdbcTemplate.queryForList(sql);

        model.addAttribute("courses", courses);
        return "courses";
    }

    // ==========================
    // 2. Register course (POST)
    // ==========================
    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable("courseId") int courseId,
                                 HttpSession session) {

        // Get logged-in student email from session
        String email = (String) session.getAttribute("email");

        if (email == null) {
            return "redirect:/login";
        }

        // Get student_id using email
        String getStudentSql = "SELECT student_id FROM students WHERE email = ?";
        int studentId = jdbcTemplate.queryForObject(getStudentSql, Integer.class, email);

        // Insert registration
        String insertSql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, CURDATE())";
        jdbcTemplate.update(insertSql, studentId, courseId);

        return "success";
    }
}