package org.hamster.weixinmp.dao.repository.item;

import org.hamster.weixinmp.dao.entity.item.WxItemThumbEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Repository
public interface WxItemThumbDao extends
        PagingAndSortingRepository<WxItemThumbEntity, Long> {

}
