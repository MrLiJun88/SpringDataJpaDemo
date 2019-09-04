package cn.njcit.dao;

import cn.njcit.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 联系人dao
 */
public interface LinkManDao extends JpaRepository<LinkMan,Integer>, JpaSpecificationExecutor<LinkMan> {

}
