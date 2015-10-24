package org.hamster.weixinmp.dao.repository.auth;

import org.hamster.weixinmp.dao.entity.auth.WxAuthReq;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Repository
public interface WxAuthReqDao extends
        PagingAndSortingRepository<WxAuthReq, Long> {

}
