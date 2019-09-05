package test;

import cn.njcit.dao.RoleDao;
import cn.njcit.dao.UserDao;
import cn.njcit.entity.Role;
import cn.njcit.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd(){
        User user = new User();
        user.setUser_Name("zhaoliu");
        user.setSubject("C++");

        Role role = new Role();
        role.setRole_Name("father");
        /**配置两个实体类的联系
         * 多对多放弃维护权，被动的一方放弃
         * */
        user.getRoleSet().add(role);
        role.getUserSet().add(user);

        userDao.save(user);
        roleDao.save(role);
    }
    /**测试级联添加，保存一个用户的同时，保存它的关联角色*/
    @Test
    @Transactional
    @Rollback(value = false)
    public void testAddByCascade(){
        User user = new User();
        user.setUser_Name("wangwu");
        user.setSubject("php");

        Role role = new Role();
        role.setRole_Name("doctor");

        user.getRoleSet().add(role);
        role.getUserSet().add(user);

        userDao.save(user);
    }
    /**测试级联删除，保存一个用户的同时，删除它的关联角色*/
    @Test
    @Transactional
    @Rollback(value = false)
    public void testDeleteByCascade(){
       User user = userDao.findOne(1);
       userDao.delete(user);
    }
}
