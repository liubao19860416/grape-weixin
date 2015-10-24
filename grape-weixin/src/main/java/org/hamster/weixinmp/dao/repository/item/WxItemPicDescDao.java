package org.hamster.weixinmp.dao.repository.item;

import java.util.List;

import org.hamster.weixinmp.dao.entity.item.WxItemPicDescEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Repository
public interface WxItemPicDescDao extends
        PagingAndSortingRepository<WxItemPicDescEntity, Long> {
    
    public abstract List<WxItemPicDescEntity> findByIdIn(List<Long> ids);
}
