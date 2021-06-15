package briv.solutions.SystemeMedicalBack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Administrateur extends Utilisateur {

    @Column(length = 250)
    @NotNull
    private String nomLegal;

    @Column(nullable = false)
    @Size(min = 10, max = 10)
    private String autorisationPratiqueMedical;

    @Column
    @NotNull
    private String adress;

    @Column
    @NotNull
    private String adressElectronique;

    @Column
    @NotNull
    private String fax;

    @Column
    @NotNull
    @Size(min = 10, max = 13)
    private int telephone;

}
