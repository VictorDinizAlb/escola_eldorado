package br.com.escola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.escola.models.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long> {

    @Query("SELECT m FROM Materia m left join m.professores pf WHERE pf.id = :id")
    public List<Materia> findProfessoresId(Long id);
}
