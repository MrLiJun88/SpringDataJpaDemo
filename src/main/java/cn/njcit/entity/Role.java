package cn.njcit.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Role与User是多对多关系
 */
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer role_Id;
    @Column(name = "role_name")
    private String role_Name;
    /**
     * Role与User的多对多关系
     */
//    @ManyToMany(targetEntity = User.class)
//    @JoinTable(name = "mid_user_role",
//            joinColumns = {@JoinColumn(name = "mid_role_id",referencedColumnName = "role_id")},
//            inverseJoinColumns = {@JoinColumn(name = "mid_user_id",referencedColumnName = "user_id")}
//    )
    /**
     * 多对多当中，被动的一方放弃维护权
     * */
    @ManyToMany(mappedBy = "roleSet")
    private Set<User> userSet = new HashSet<>();

    @Override
    public String toString() {
        return "Role{" +
                "role_Id=" + role_Id +
                ", role_Name='" + role_Name + '\'' +
                ", userSet=" + userSet +
                '}';
    }

    public Integer getRole_Id() {
        return role_Id;
    }

    public void setRole_Id(Integer role_Id) {
        this.role_Id = role_Id;
    }

    public String getRole_Name() {
        return role_Name;
    }

    public void setRole_Name(String role_Name) {
        this.role_Name = role_Name;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
