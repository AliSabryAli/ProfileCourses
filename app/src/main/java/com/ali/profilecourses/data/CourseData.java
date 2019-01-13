package com.ali.profilecourses.data;

import com.ali.profilecourses.model.Course;

import java.util.ArrayList;
import java.util.List;

//Instance of Data internal
public class CourseData {
    private String[] courseNames = {"course 1", "course 2", "course 3", "course 4", "course 5", "course 6", "course 7", "course 8", "course 9 ", "course 10"};

    public List<Course> getCoursesList() {
        List<Course> list = new ArrayList<>();
        for (int i = 0; i < courseNames.length; i++) {
            Course course = new Course(courseNames[i], courseNames[i].replace(" ", "").toLowerCase(), "item");
            list.add(course);
        }
        return list;
    }
}
