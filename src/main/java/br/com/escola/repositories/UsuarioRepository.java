package br.com.escola.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.escola.models.Usuario;
import br.com.escola.models.enums.EnumCategorias;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);

    public List<Usuario> findByCategoria(EnumCategorias categoria);
}
