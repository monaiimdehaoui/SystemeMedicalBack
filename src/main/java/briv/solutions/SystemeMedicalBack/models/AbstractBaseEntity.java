package briv.solutions.SystemeMedicalBack.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AbstractBaseEntity implements Serializable {

    /**
    *
    */

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    protected AbstractBaseEntity() {
        this.id = UUID.randomUUID().toString();
    }
}
