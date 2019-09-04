package test;

import cn.njcit.dao.CustomerDaoBySpecification;
import cn.njcit.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDaoBySpecification customerDao;

    @Test
    public void testSelectByName(){
//        Specification<Customer> spec = new Specification<Customer>() {
//            @Override
//            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                return null;
//            }
//        };
        /**上述代码使用lambda来替代
         * 自定义查询条件：
         *     1.实现Specification接口(提供泛型，查询的对象类型)
         *     2.实现toPredicate()方法(构造查询条件)
         *     3.需要借助方法参数中的两个参数
         *           *root:获取需要查询的对象属性
         *           *CriteriaBuilder:构造查询条件，内部封装了很多查询条件(模糊匹配，精准匹配)
         *
         *     例 ：根据客户名查询客户信息
         *          查询条件：
         *            1.查询方式
         *            2.比较的属性名称
         * */
        Specification<Customer> spec = (Root<Customer> root, CriteriaQuery<?> criteriaQuery,
                                        CriteriaBuilder criteriaBuilder) -> {
            /**获取属性名称*/
            Path<Object> custName = root.get("name");
            /**构造查询方式
             * equal():进行精准匹配
             *    第一个参数：需要比较的属性(path对象)
             *    第二个参数：参数的取值
             * */
            Predicate predicate = criteriaBuilder.equal(custName,"lijun");
            return predicate;
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**根据姓名与所属行业实现多条件查询*/
    @Test
    public void testSelectByNameAndIndustry(){
        Specification<Customer> spec = (Root<Customer> root,
                                        CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            Path<Object> custName = root.get("name");
            Path<Object> industry = root.get("industry");
           Predicate predicate1 = criteriaBuilder.equal(custName,"zhangsan");
           Predicate predicate2 = criteriaBuilder.equal(industry,"php");
           /**将多个查询条件组合到一起(与关系，或关系)
            * and():以 与 的条件将多个条件组合到一起
            * or():以 或 的条件将多个条件组合到一起
            * */
            Predicate result = criteriaBuilder.and(predicate1,predicate2);
           return result;
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 模糊查询，以 li 开头的客户信息
     *
     *  equal():直接对path对象(属性)，然后进行比较即可
     *  gt,lt,ge,le,like:得到path对象，根据path指定比较的参数类型，再去进行比较
     *                   指定参数类型：path.as(类型的class对象)
     */
    @Test
    public void testSelectByLike(){
        Specification<Customer> spec = (Root<Customer> root,
                                        CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            Path<Object> custName = root.get("name");
           Predicate predicate = criteriaBuilder.like(custName.as(String.class),"li%");
           return predicate;
        };
//        List<Customer> list = customerDao.findAll(spec);
        /**根据用户id倒序排序*/
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Customer> list = customerDao.findAll(spec,sort);
        list.forEach(i -> System.out.println(i));
    }

    /**
     * 使用动态sql进行分页查询
     * 分页查询：
     *      Specification:查询条件
     *      Pageable:分页参数
     *          分页参数：查询的页码，每页查询的条数
     *          findAll(Specification,Pageable):带有条件的分页
     *          findAll(Pageable):没有条件的分页
     *     返回：Page(是springDataJpa已经封装好的PageBean对象，数据列表，总条数)
     */
    @Test
    public void testPaging(){
        Specification<Customer> spec = (Root<Customer> root,
                                        CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            return null;
        };
        /**
         * PageRequest类是Pageable接口的实现类
         * 创建PageRequest对象的过程中，需要调用他的构造方法传入两个参数
         *      1.第一个参数：当前查询的开始页数(第一页从 0 开始)
         *      2.第二个参数： 每页查询的数量
         */
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> page =  customerDao.findAll(spec,pageable);
        /**得到分页后的内容*/
        System.out.println(page.getContent());
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }
}
