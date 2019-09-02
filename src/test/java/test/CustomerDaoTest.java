package test;

import cn.njcit.dao.CustomerDao;
import cn.njcit.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    /**如果用户的主键id在数据库中已经存在，则save()对用户是更新操作，而不是添加新的用户信息*/
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setId(6);
        customer.setName("hello");
        customer.setIndustry("PHP");
        customer.setPhone("110112119");
        customerDao.save(customer);
    }

    @Test
    public void testDelete(){
        customerDao.delete(8);
    }
    /**查询全部用户信息*/
    @Test
    public void testFindAll(){
        List<Customer> list = customerDao.findAll();
        list.forEach(i -> System.out.println(i));
    }
    /**查询用户总数*/
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }
    /**判断id为7的用户在数据库中是否存在*/
    @Test
    public void testExist(){
        boolean isExit = customerDao.exists(7);
        System.out.println(isExit);
    }

    /**
     * 根据id从数据库中查询
     * @Transactional :保证getOne()正常运行
     *
     * findOne():调用 em.find() 立即加载，返回一个实体类真实对象，调用即加载
     * getOne():调用 em.Reference() 延迟加载,返回一个基于实体类的动态代理的对象，什么时候用，什么时候加载
     */
    @Test
    @Transactional
    public void testGetOne(){
        Customer customer = customerDao.getOne(7);
        System.out.println(customer);
    }
}
