package briv.solutions.SystemeMedicalBack.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import briv.solutions.SystemeMedicalBack.models.Utilisateur;
import briv.solutions.SystemeMedicalBack.requestModels.SingingUpUserRequestModel;

@Service
public interface IUtilisateurService extends UserDetailsService  {

    public Utilisateur createUser(SingingUpUserRequestModel user);

    public Utilisateur getUser(String email);

    public Utilisateur getUserById(String userId);

    public UserDetails loadUserByUsername(String email);

    public Utilisateur updateUser(String userId, SingingUpUserRequestModel user);

    public List<Utilisateur> getUsers(int page, int limit, String sort, String direction);

    public void deleteUser(String id);

}
