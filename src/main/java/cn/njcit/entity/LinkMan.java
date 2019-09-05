package cn.njcit.entity;

import javax.persistence.*;

@Entity
@Table(name = "cst_linkman")
public class LinkMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Integer lm_id;
    @Column(name = "lkm_name")
    private String lm_name;
    @Column(name = "lkm_gender")
    private String lm_gender;
    @Column(name = "lkm_phone")
    private String lm_phone;
    @Column(name = "lkm_mobile")
    private String lm_mobile;
    @Column(name = "lkm_email")
    private String lm_email;
    @Column(name = "lkm_position")
    private String lm_position;
    @Column(name = "lkm_memo")
    private String lm_memo;

    /**
     * 配置联系人到客户的 多对一 关系
     *  使用注解的形式配置 多对一 关系
     * @OneToMany :配置一对多关系
     *     targetEntity:对方对象的字节码类型
     *      2.配置外键（中间表）
     * @JoinColumn:配置外键
     *        name:外键字段名称
     *        referencedColumnName:参照的主表字段名称
     *       在客户实体类上(一的一方)添加了外键的配置，所以对于客户而言，也具备了维护外键的作用
     *       fetch = FetchType.LAZY: 设置延迟加载
     */
    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
     private Customer customer;

    @Override
    public String toString() {
        return "LinkMan{" +
                "lm_id=" + lm_id +
                ", lm_name='" + lm_name + '\'' +
                ", lm_gender='" + lm_gender + '\'' +
                ", lm_phone='" + lm_phone + '\'' +
                ", lm_mobile='" + lm_mobile + '\'' +
                ", lm_email='" + lm_email + '\'' +
                ", lm_position='" + lm_position + '\'' +
                ", lm_memo='" + lm_memo + '\'' +
                ", customer=" + customer +
                '}';
    }

    public Integer getLm_id() {
        return lm_id;
    }

    public void setLm_id(Integer lm_id) {
        this.lm_id = lm_id;
    }

    public String getLm_name() {
        return lm_name;
    }

    public void setLm_name(String lm_name) {
        this.lm_name = lm_name;
    }

    public String getLm_gender() {
        return lm_gender;
    }

    public void setLm_gender(String lm_gender) {
        this.lm_gender = lm_gender;
    }

    public String getLm_phone() {
        return lm_phone;
    }

    public void setLm_phone(String lm_phone) {
        this.lm_phone = lm_phone;
    }

    public String getLm_mobile() {
        return lm_mobile;
    }

    public void setLm_mobile(String lm_mobile) {
        this.lm_mobile = lm_mobile;
    }

    public String getLm_email() {
        return lm_email;
    }

    public void setLm_email(String lm_email) {
        this.lm_email = lm_email;
    }

    public String getLm_position() {
        return lm_position;
    }

    public void setLm_position(String lm_position) {
        this.lm_position = lm_position;
    }

    public String getLm_memo() {
        return lm_memo;
    }

    public void setLm_memo(String lm_memo) {
        this.lm_memo = lm_memo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
