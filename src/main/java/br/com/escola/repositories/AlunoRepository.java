package br.com.escola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.escola.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
