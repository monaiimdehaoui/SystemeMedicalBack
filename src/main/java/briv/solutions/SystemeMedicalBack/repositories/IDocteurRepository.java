package briv.solutions.SystemeMedicalBack.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import briv.solutions.SystemeMedicalBack.models.Docteur;

@Repository
public interface IDocteurRepository extends CrudRepository<Docteur, String> {

}
