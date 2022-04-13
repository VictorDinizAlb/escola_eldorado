package br.com.escola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.models.Aula;

public interface AulaRepository extends JpaRepository<Aula, Long> {

    public List<Aula> findByAlunoId(Long id);

    public List<Aula> findByProfessorId(Long id);
}
