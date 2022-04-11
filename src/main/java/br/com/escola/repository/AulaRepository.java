package br.com.escola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.Aula;

public interface AulaRepository extends JpaRepository<Aula, Long> {

    public List<Aula> findByUsuarioId(Long id);
}
