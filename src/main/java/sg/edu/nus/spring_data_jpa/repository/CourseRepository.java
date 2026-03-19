package sg.edu.nus.spring_data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.spring_data_jpa.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
