package br.com.escola.models.relationship;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import br.com.escola.models.Materia;
import br.com.escola.models.Usuario;

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
