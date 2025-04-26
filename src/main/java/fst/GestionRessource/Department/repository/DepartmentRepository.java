package fst.GestionRessource.Department.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import fst.GestionRessource.Department.model.Department;

@Controller
public interface DepartmentRepository extends JpaRepository<Department, String> {
  Optional<Department> findByName(String name);
}
