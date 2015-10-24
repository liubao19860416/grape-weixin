package org.hamster.weixinmp.dao.repository.menu;

import org.hamster.weixinmp.dao.entity.menu.WxMenuBtnEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Repository
public interface WxMenuBtnDao extends
        PagingAndSortingRepository<WxMenuBtnEntity, Long> {

}
