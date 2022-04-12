package br.com.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

}
