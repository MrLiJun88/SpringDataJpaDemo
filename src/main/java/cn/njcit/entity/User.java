package cn.njcit.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User与Role是多对多关系
 */
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_Id;
    @Column(name = "user_name")
    private String user_Name;
    @Column(name = "user_subject")
    private String subject;

    /**
     * 配置User与Role的多对多关系
     *   1.声明表关系
     *   @ManyToMany(targetEntity = 对方实体类的Class)
     *   2.配置中间表(包含两个外键)
     *    当前对象在中间表的外键
     *    joinColumns = {@JoinColumn(name = "",referencedColumnName = "")},
     *    //对方对象在中间表的外键
     *    inverseJoinColumns = {@JoinColumn(name = "",referencedColumnName = "")}
     *
     *    @JoinColumn数组
     *        name:定义的中间表外键名
     *        referencedColumnName:参照的表的主键名称
     */
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinTable(name = "mid_user_role",
            joinColumns = {@JoinColumn(name = "mid_user_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "mid_role_id",referencedColumnName = "role_id")}
    )
    private Set<Role> roleSet = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "user_Id=" + user_Id +
                ", user_Name='" + user_Name + '\'' +
                ", subject='" + subject + '\'' +
                ", roleSet=" + roleSet +
                '}';
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}
