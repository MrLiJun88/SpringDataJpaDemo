package cn.njcit.dao;

import cn.njcit.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 通过过Specification：实现动态SQL查询
 */
public interface CustomerDaoBySpecification extends JpaRepository<Customer,Integer>, JpaSpecificationExecutor<Customer> {

}
