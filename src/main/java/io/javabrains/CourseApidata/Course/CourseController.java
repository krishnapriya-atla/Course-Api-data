package io.javabrains.CourseApidata.Course;

import io.javabrains.CourseApidata.topics.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/topics/{id}/courses")
    public List<Course> getAllCourses(@PathVariable String id) {
        return courseService.getAllCourses(id);
    }

    @GetMapping("/topics/{topicId}/courses/{id}")
    public Course getCourse(@PathVariable String id) {
        return courseService.getCourse(id);
    }

    @PostMapping(value="topics/{topicId}/courses")
    public String addCourse(@RequestBody Course course,@PathVariable String topicId) {
        course.setTopic(new Topic(topicId,"",""));
        return courseService.addCourse(course);
    }

    @PutMapping(value="topics/{topicId}/courses/{id}")
    public void updateCourse(@RequestBody Course course, @PathVariable String topicId) {
        course.setTopic(new Topic(topicId,"",""));
        courseService.updateCourse(course);
    }

    @DeleteMapping(value="topics/{topicId}/courses/{id}")
    public void deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }
}
