package org.hamster.weixinmp.dao.repository.resp;

import org.hamster.weixinmp.dao.entity.resp.WxRespVoiceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Repository
public interface WxRespVoiceDao extends
        PagingAndSortingRepository<WxRespVoiceEntity, Long> {
}
