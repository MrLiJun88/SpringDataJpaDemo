package test;

import cn.njcit.dao.CustomerDao;
import cn.njcit.dao.LinkManDao;
import cn.njcit.entity.Customer;
import cn.njcit.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 对象导航查询测试(针对一对多案例)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    LinkManDao linkManDao;

    /**
     * 出现could not initialize proxy - no Session，需要添加事务注解
     * 对象导航查询：
     *     调用get方法并不会立即发送查询，而是在使用关联对象的时候才会查询
     *     即，对象导航查询默认使用 延迟加载
     */
    @Test
    @Transactional
    public void testQuery1(){
        Customer customer = customerDao.findOne(1);
        Set<LinkMan> set = customer.getLinkManSet();
        System.out.println(customer);
    }

    /**
     * 从联系人对象导航查询它的所属客户
     *    默认：立即加载
     *
     */
    @Test
    @Transactional
    public void testQuery2(){
        LinkMan man = linkManDao.findOne(2);
        Customer customer = man.getCustomer();
        System.out.println(customer);
    }
}
