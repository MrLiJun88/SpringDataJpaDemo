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
        user.setUser_Name("lisi");
        user.setSubject("Java");

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
}
