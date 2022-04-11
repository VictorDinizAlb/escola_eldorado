package br.com.escola.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.model.Usuario;
import br.com.escola.model.enums.EnumCategorias;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    public List<Usuario> findByCategoria(EnumCategorias categoria);
}
