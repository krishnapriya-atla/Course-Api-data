package io.javabrains.CourseApidata.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courserepository;

    public List<Course> getAllCourses(String id){
        List<Course> courses=new ArrayList<>();
        courserepository.findByTopicId(id).forEach(courses::add);
        return courses;
    }
    public Course getCourse(String id) {

        return courserepository.findById(id).get();
    }
    public String addCourse(Course course) {
        courserepository.save(course);
        return "";
    }
    public void updateCourse(Course course) {
        courserepository.save(course);
    }
    public void deleteCourse(String id) {
        courserepository.deleteById(id);
    }
}
