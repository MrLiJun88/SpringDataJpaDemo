package cn.njcit.dao;

import cn.njcit.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *  要符合SpringDataJpa的dao层接口规范，需要继承两个接口
 *      1.JpaRepository<操作的实体类类型，实体类中主键属性的类型>,封装了基本CRUD操作
 *      2.JpaSpecificationExecutor<操作的实体类类型>，封装了复杂查询，如分页
 */
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    /**根据客户名称，查询客户信息
     * 以jpql形式查询 ：from Customer where name = ?
     * 配置Jpql语句，在接口方法上使用 @Query注解
     * */
    @Query(value = "from cn.njcit.entity.Customer where name = ?")
    Customer findJpql(String name);

    /**根据客户名称与客户id查询客户
     *    对于多个占位符参数
     *       赋值的时候，默认的情况下，占位符的位置需要和方法参数中的位置保持一致
     *    可以指定占位符参数的位置
     *       ?数字 ：指定此占位符的取值来源
     * */
    @Query(value = "from Customer where id = ?1 and name = ?2")
    Customer findCustomerByNameAndId(Integer id,String name);

    /**根据id更新客户的名字
     * sql : update cst_customer set cst_name = ? where cst_id = ?
     * jpql : update Customer set name = ? where id = ?
     *
     * @Query : 代表的是查询，不是更新操作
     * @Modifying : 代表是更新操作
     * @Transactional : 对于更新/删除操作，需要添加事务的支持,才可以正确执行
     * */
    @Query(value = "update Customer set name = ?2 where id = ?1")
    @Modifying
    void updateCustomerById(Integer id,String name);

    /**使用sql的形式进行查询，如：查询全部的用户信息
     * sql:select * from cst_customer
     * @Query : 配置sql查询
     *     value : sql语句
     *     nativeQuery : 查询方式
     *            true : sql查询
     *            false : jpql查询
     * */
    @Query(value = "select * from cst_customer",nativeQuery = true)
    List<Customer> selectAllBySql();

    /**使用sql语句对用户名进行模糊查询*/
    @Query(value = "select * from cst_customer where cust_name like ?",nativeQuery = true)
    List<Customer> selectByNameLike(String name);

    /**
     * 方法名的预定：
     *     findBy : 查询
     *            对象中的属性名(首字母大写)：条件查询
     *     如：findByName() : 根据客户名称查询
     *
     *    在SpringDataJpa运行阶段：
     *         会根据方法名称进行解析 findyBy from 实体类
     *                              属性名称  where cust_name =
     *     1.根据属性精准查询
     *             findBy + 属性名称(根据属性名称进行完整匹配的查询)
     *     2.根据属性模糊查询
     *             findBy + 属性名称 + "查询方式(Like | isnull)",实现模糊查询
     *             如 ： findByCustNameLike(String name)
     *     3.多条件查询：
     *       findBy + 属性名 + "查询方式" + "多条件连接符"(and | or) + 其他属性名 + "查询方式"
     */
    Customer findByName(String name);
    /**使用方法命令的约定实现模糊查询*/
    List<Customer> findByNameLike(String name);
    /**使用方法命令的约定实现多条件查询
     * 如 ： 以客户名称进行模糊查询和客户所属行业实现精准查询
     * */
    Customer findByNameLikeAndIndustry(String name,String industry);

}
