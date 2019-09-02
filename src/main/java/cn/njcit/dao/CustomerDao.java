package cn.njcit.dao;

import cn.njcit.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *  要符合SpringDataJpa的dao层接口规范，需要继承两个接口
 *      1.JpaRepository<操作的实体类类型，实体类中主键属性的类型>,封装了基本CRUD操作
 *      2.JpaSpecificationExecutor<操作的实体类类型>，封装了复杂查询，如分页
 */
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

}
