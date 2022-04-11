package br.com.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
