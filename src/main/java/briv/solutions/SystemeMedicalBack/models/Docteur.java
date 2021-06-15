package briv.solutions.SystemeMedicalBack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Docteur extends Utilisateur {

    @Column(length = 200)
    @NotNull
    private String nom;

    @Column(nullable = false)
    @Size(min = 18, max = 18)
    private String autorisationPratique;

    @Column(nullable = false)
    @Email
    private String email;

    @NotNull
    @Column
    private String photoUrl;

    @Column(nullable = false, unique = true)
    private String numeroImmatriculation;

    @Column
    @NotNull
    @Size(min = 10, max = 13)
    private int telephone;

}
