package sg.edu.nus.spring_data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.spring_data_jpa.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
