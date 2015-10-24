package org.hamster.weixinmp.dao.repository.msg;

import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Repository
public interface WxBaseMsgDao extends
        PagingAndSortingRepository<WxBaseMsgEntity, Long> {

}
