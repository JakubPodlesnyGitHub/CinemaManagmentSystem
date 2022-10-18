package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "SystemUserRole")
@Access(AccessType.FIELD)
public class SysRole {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long idSystemRole;
    @Column(name = "RoleName", nullable = false)
    private String roleName;
    @Column(name = "RoleDescription", nullable = false)
    private String desc;

    @OneToMany(mappedBy = "sysRole")
    private List<ProgramUser> programUsers = new ArrayList<>();

    public SysRole(String roleName, String desc) {
        setRoleName(roleName);
        setDesc(desc);
    }

    public SysRole() {

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getIdSystemRole() {
        return idSystemRole;
    }

    public void setIdSystemRole(Long idSystemRole) {
        this.idSystemRole = idSystemRole;
    }

    //connections

    public void addToUsersCollection(ProgramUser newProgramUser) {
        if (!programUsers.contains(newProgramUser)) {
            programUsers.add(newProgramUser);
            newProgramUser.setSysRole(this);
        }
    }

    public void removeFromUserCollection(ProgramUser programUserToRemove) {
        if (programUsers.contains(programUserToRemove)) {
            programUsers.remove(programUserToRemove);
        }
    }
}
