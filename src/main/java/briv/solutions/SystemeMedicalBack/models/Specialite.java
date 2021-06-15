package briv.solutions.SystemeMedicalBack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Specialite extends AbstractBaseEntity {

    @Column
    @NotNull
    private String nom;
}
