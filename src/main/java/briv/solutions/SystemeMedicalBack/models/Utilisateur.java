package briv.solutions.SystemeMedicalBack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PreRemove;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import briv.solutions.SystemeMedicalBack.sysenum.SysRoleEnum;
import briv.solutions.SystemeMedicalBack.sysenum.UserStateEnum;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE utilisateur SET status = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "status <> 'DELETED'")
public class Utilisateur extends AbstractBaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -4565971805385746136L;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String encryptePassword;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SysRoleEnum Role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStateEnum status;

    @PreRemove
    public void archiveUser() {
        this.status = UserStateEnum.ARCHIVED;
    }

}
