package briv.solutions.SystemeMedicalBack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import briv.solutions.SystemeMedicalBack.sysenum.AvertissementEnum;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Avertissement extends AbstractBaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AvertissementEnum cause;

    @Column
    private String message;

}
