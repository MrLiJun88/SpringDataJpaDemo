package cn.njcit.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
     * 客户实体类
     *  配置映射关系：
     *   1.实体类与数据库表的关系
     *    @Entity : 声明该类是一个实体类
     *    @Table : 配置实体类与表的映射关系
     *       name: 数据库表的名称
     *
     *   2.实体类中属性与表字段的映射关系
     *      @Id : 声明主键的配置
     *
     *      @GeneratedValue(strategy = GenerationType.IDENTITY) 配置主键的生成策略
     *          IDENTITY: 代表自增，前提是底层数据库必须支持自支增长(对id自增,针对 MySQL 数据库)
     *          SEQUENCE: 代表序列，前提是底层数据库必须支持序列(针对的是 ORACLE 数据库)
     *          TABLE : JAP提供的一种机制，通过一张数据库表的形式帮助我们完成自增
     *          AUTO : 代表由程序自动的帮助我们选择主机生成策略
     *
     *      @Column(name = "cust_id") 配置属性与字段的映射，name 是表中字段的名称
     *
     */
    @Entity
    @Table(name = "cst_customer")
    public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "cust_id")
        private Integer id;
        @Column(name = "cust_name")
        private String name;
        /**客户来源*/
        @Column(name = "cust_source")
        private String source;
        /**客户所属行业*/
        @Column(name = "cust_industry")
        private String industry;
        /**客户级别*/
        @Column(name = "cust_level")
        private String level;
        @Column(name = "cust_address")
        private String address;
        @Column(name = "cust_phone")
        private String phone;
        /**
         * 配置客户与联系人的关系 ，一对多关系
         * 使用注解的形式配置 一对多 关系
         *    1.声明关系
         * @OneToMany :配置一对多关系
         *        targetEntity:对方对象的字节码类型
         *    2.配置外键（中间表）
         * @JoinColumn:配置外键
         *        name:外键字段名称
         *        referencedColumnName:参照的主表字段名称
         *在客户实体类上(一的一方)添加了外键的配置，所以对于客户而言，也具备了维护外键的作用
         */
//        @OneToMany(targetEntity = LinkMan.class)
//        @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    /**
     * @OneToMany:放弃维护权
     *    取值：对方配置关系的属性名称
     *    mappedBy:对方配置关系的属性名称
     *    cascade:配置级联
     *         CascadeType.all ：所有
     *                     MERGE: 更新
     *                     PERSIST: 保存
     *                     REMOVE : 删除
     *   fetch:配置关联对象的加载方式：
     *        EAGER:立即加载
     *        LAZY: 延迟加载
     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<LinkMan> linkManSet = new HashSet<>();

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", industry='" + industry + '\'' +
                ", level='" + level + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", linkManSet=" + linkManSet +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }
}

