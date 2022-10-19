package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "MGroup")
public class Group {
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "IdGroup", nullable = false)
    private Long idGroup;
    @Column(name = "Name", nullable = false,unique = true)
    private String name;
    @Column(name = "Group Description", nullable = false)
    private String groupDescription;
    @Column(name = "Members Number", nullable = false)
    private int howManyMembers = 0;

    @ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
    private List<ProgramUser> members = new ArrayList<>();

    public Group() {
    }

    public Group(String name, String groupDescription) {
        this.name = name;
        this.groupDescription = groupDescription;
        this.howManyMembers = 0;
    }

    public Long getId() {
        return idGroup;
    }

    public void setId(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public int getHowManyMembers() {
        return howManyMembers;
    }

    public void setHowManyMembers(int howManyMembers) {
        this.howManyMembers = howManyMembers;
    }
    //connections

    public void addNewMemberToGroup(ProgramUser newMember){
        if(!members.contains(newMember)){
            members.add(newMember);
            howManyMembers++;
            newMember.addGroupToList(this);
        }
    }

    public void deleteMemberFromGroup(ProgramUser programUser){
        if(members.contains(programUser)){
            members.remove(programUser);
            howManyMembers--;
        }
    }

    @Override
    public String toString() {
        return "Group " + getName();
    }
}
