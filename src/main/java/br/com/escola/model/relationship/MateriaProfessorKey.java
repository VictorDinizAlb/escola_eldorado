package br.com.escola.model.relationship;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MateriaProfessorKey implements Serializable {

    @Column(name = "materiaId")
    Long materiaId;

    @Column(name = "porfessorId")
    Long professorId;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
