package br.com.escola.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.models.Materia;
import br.com.escola.models.Usuario;
import br.com.escola.repositories.MateriaRepository;
import br.com.escola.repositories.UsuarioRepository;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Materia criarMateria(Materia materia) {
        return this.materiaRepo.save(materia);
    }

    public List<Materia> listarMaterias() {
        return this.materiaRepo.findAll();
    }

    public List<Usuario> listarProfessoresPorMateria(Long id) {
        Optional<Materia> materia = this.materiaRepo.findById(id);
        if (materia == null) {
            return null;
        }
        return materia.get().getProfessores();
    }

    public List<Materia> vincularMateriaProfessor(Long materiaId, Long professorId) {
        Materia materia = this.materiaRepo.findById(materiaId).get();
        Usuario professor = this.usuarioRepo.findById(professorId).get();

        materia.setProfessor(professor);
        this.materiaRepo.save(materia);

        return this.listarMaterias();
    }
}
