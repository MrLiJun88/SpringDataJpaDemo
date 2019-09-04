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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    private LinkManDao linkManDao;
    @Autowired
    private CustomerDao customerDao;

    @Test
    @Transactional//配置事务
    @Rollback(value = false)//设置不自动回滚
    public void testAddCustomerAndLinkMan(){
        Customer customer = new Customer();
        customer.setName("lijun");
        customer.setIndustry("JAVA");

        LinkMan linkMan = new LinkMan();
        linkMan.setLm_name("linkMan1");
        linkMan.setLm_gender("M");
        /**将多的一方加入到一的一方集合中*/
        customer.getLinkManSet().add(linkMan);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
    @Test
    @Transactional//配置事务
    @Rollback(value = false)//设置不自动回滚
    public void testAddCustomerAndLinkMan2(){
        Customer customer = new Customer();
        customer.setName("wangwu");
        customer.setIndustry("Linux");

        LinkMan linkMan = new LinkMan();
        linkMan.setLm_name("linkMan1");
        linkMan.setLm_gender("W");

        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional//配置事务
    @Rollback(value = false)//设置不自动回滚
    public void testAddCustomerAndLinkMan3(){
        Customer customer = new Customer();
        customer.setName("zhaoliu");
        customer.setIndustry("C++");

        LinkMan linkMan = new LinkMan();
        linkMan.setLm_name("linkMan2");
        linkMan.setLm_gender("M");
        /**将多的一方加入到一的一方集合中
         * 由于配置了一的一方到多的一方的关联关系，所以当保存的时候，就已经对外键进行赋值
         * 会产生多余的一条 update()语句（因为一的一方维护了多的一方的外键）
         * 解决方法：只需要在一的一方放弃维护权即可
         * */
        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 测试级联添加
     * 保存客户的同时，保存他的所有联系人
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testAddByCascade(){
        Customer customer = new Customer();
        customer.setName("wmy");
        customer.setIndustry("C++");

        LinkMan linkMan = new LinkMan();
        linkMan.setLm_name("linkMan2");
        linkMan.setLm_gender("M");

        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
    }

    /**
     * 测试级联删除
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testRemoveByCascade(){
        Customer customer = new Customer();
        customer.setName("wmy");
        customer.setIndustry("C++");

        LinkMan linkMan = new LinkMan();
        linkMan.setLm_name("linkMan2");
        linkMan.setLm_gender("M");

        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.delete(customer);
    }

}
