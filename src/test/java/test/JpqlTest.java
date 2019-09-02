package test;

import cn.njcit.dao.CustomerDao;
import cn.njcit.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
    @Autowired
    CustomerDao customerDao;

    @Test
    public void testJpql(){
       Customer customer = customerDao.findJpql("hello");
        System.out.println(customer);
    }

    @Test
    public void testFindCustomerByNameAndId(){
        Customer customer = customerDao.findCustomerByNameAndId(7,"hello");
        System.out.println(customer);
    }

    /**
     * 测试jpql的更新操作
     *   springDataJpa中使用jpql完成 更新/删除操作
     *       需要手动添加对事务的支持
     *       默认会执行结束后，回滚事务
     */
    @Test
    @Transactional//对事务的支持
    @Rollback(value = false)//设置不自动回滚
    public void testUpdateCustomerById(){
        customerDao.updateCustomerById(7,"zhangsan");
    }

    @Test
    public void testSelectAllBySql(){
        List<Customer> list = customerDao.selectAllBySql();
        list.forEach(i -> System.out.println(i));
    }
    /**根据sql实现模糊查询*/
    @Test
    public void testSelectByNameLike(){
        List<Customer> list = customerDao.selectByNameLike("l%");
        list.forEach(i -> System.out.println(i));
    }

    @Test
    public void testFindbyName(){
        Customer customer = customerDao.findByName("wmy");
        System.out.println(customer);
    }

    @Test
    public void testFindByNameLike(){
        List<Customer> list = customerDao.findByNameLike("li%");
        list.forEach(i -> System.out.println(i));
    }

    @Test
    public void testFindByNameLikeAndIndustry(){
        Customer customer = customerDao.findByNameLikeAndIndustry("li%","C++");
        System.out.println(customer);
    }
}
