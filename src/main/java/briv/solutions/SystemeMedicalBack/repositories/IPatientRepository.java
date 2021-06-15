package briv.solutions.SystemeMedicalBack.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import briv.solutions.SystemeMedicalBack.models.Patient;

@Repository
public interface IPatientRepository extends CrudRepository<Patient, String> {

}
