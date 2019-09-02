package test;

import cn.njcit.dao.CustomerDao;
import cn.njcit.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**声明spring提供的单元测试环境*/
@RunWith(SpringJUnit4ClassRunner.class)
/**指定spring容器的信息*/
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /**根据id查询出用户信息*/
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(1);
        System.out.println(customer);
    }
    /**save()保存或者更新，根据主键id属性，如果存在id主键属性，则进行查询，没有则保存*/
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setName("hello");
        customer.setIndustry("php");
        customer.setPhone("7777789888");
        customerDao.save(customer);
    }
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setId(6);
        customer.setName("hello");
        customer.setIndustry("PHP");
        customer.setPhone("110112119");
        customerDao.save(customer);
    }
}
