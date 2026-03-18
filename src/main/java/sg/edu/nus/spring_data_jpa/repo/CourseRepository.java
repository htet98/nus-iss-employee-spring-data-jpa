package sg.edu.nus.spring_data_jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.spring_data_jpa.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {

}
