package briv.solutions.SystemeMedicalBack.requestModels;

import briv.solutions.SystemeMedicalBack.sysenum.SysRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingingUpUserRequestModel {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private SysRoleEnum role;
}
