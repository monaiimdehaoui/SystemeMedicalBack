package briv.solutions.SystemeMedicalBack.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import briv.solutions.SystemeMedicalBack.models.Utilisateur;
import briv.solutions.SystemeMedicalBack.repositories.IUtilisateurRepository;
import briv.solutions.SystemeMedicalBack.requestModels.SingingUpUserRequestModel;
import briv.solutions.SystemeMedicalBack.services.IUtilisateurService;
import briv.solutions.SystemeMedicalBack.sysenum.UserStateEnum;

@Transactional
public class UtilisateurServiceImpl implements IUtilisateurService {

    @Autowired
    private IUtilisateurRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    public Utilisateur createUser(SingingUpUserRequestModel user) {

        Utilisateur registredUser = new Utilisateur();
        String useremail = user.getEmail();

        Optional<Utilisateur> userexists = userRepository.findUtilisateurByEmail(useremail);

        if (userexists.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "user already exists");
        }

        else {

            BeanUtils.copyProperties(user, registredUser);
            registredUser.setEncryptePassword(bcryptPasswordEncoder.encode(user.getPassword()));
            registredUser.setStatus(UserStateEnum.ACTIVE);

            registredUser = this.userRepository.save(registredUser);
        }
        return registredUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        Optional<Utilisateur> userOptional = userRepository.findUtilisateurByEmail(email);
        Utilisateur user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();

            return new User(user.getEmail(), user.getEncryptePassword(), new ArrayList<>());
        } else
            throw new UsernameNotFoundException("No user with the given Email");
    }

    @Override
    public Utilisateur getUser(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Utilisateur getUserById(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Utilisateur updateUser(String userId, SingingUpUserRequestModel user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Utilisateur> getUsers(int page, int limit, String sort, String direction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(String id) {
        // TODO Auto-generated method stub

    }

}
