package briv.solutions.SystemeMedicalBack.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient extends Utilisateur {

    @Column(length = 150)
    @NotNull
    private String nom;

    @Column(length = 150)
    @NotNull
    private String prenom;

    @Column
    @NotNull
    private String adress;

    @Column
    @NotNull
    @Size(min = 10, max = 13)
    private int telephone;

    @NotNull
    @Column(length = 6)
    private int numeroAssuranceSocial;

    @NotNull
    @Column
    private String photoUrl;

    @Column
    private String assuranceAutre;

    @ManyToOne
    private List<Avertissement> avertissements;

    @ManyToOne
    private List<Medicament> medicament;

    @Column
    @Size(min = 10, max = 13)
    private int telephone_urgence;
}
