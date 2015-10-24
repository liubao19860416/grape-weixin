package org.hamster.weixinmp.dao.repository.msg;

import org.hamster.weixinmp.dao.entity.msg.WxMsgTextEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Repository
public interface WxMsgTextDao extends
        PagingAndSortingRepository<WxMsgTextEntity, Long> {

}
