package sg.edu.nus.spring_data_jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.spring_data_jpa.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
