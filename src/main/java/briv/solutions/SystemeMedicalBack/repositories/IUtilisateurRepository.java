package briv.solutions.SystemeMedicalBack.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import briv.solutions.SystemeMedicalBack.models.Utilisateur;

@Repository
public interface IUtilisateurRepository extends CrudRepository<Utilisateur, String> {

    public Optional<Utilisateur> findUtilisateurByEmail(String email);
}
