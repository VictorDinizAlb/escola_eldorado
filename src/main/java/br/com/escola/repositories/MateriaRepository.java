package br.com.escola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.models.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

}
