package br.com.escola.model.relationship;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import br.com.escola.model.Materia;
import br.com.escola.model.Usuario;

@Entity
public class MateriaProfessor {

    @EmbeddedId
    MateriaProfessorKey id;

    @ManyToOne
    @MapsId("materiaId")
    @JoinColumn(name = "materiaId")
    Materia materia;

    @ManyToOne
    @MapsId("professorId")
    @JoinColumn(name = "professorId")
    Usuario professor;

}
