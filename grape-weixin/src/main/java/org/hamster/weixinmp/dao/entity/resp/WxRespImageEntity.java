package org.hamster.weixinmp.dao.entity.resp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.item.WxItemImageEntity;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 * 
 */
@Entity
@Table(name = WxConfig.TABLE_PREFIX + "resp_music")
public class WxRespImageEntity extends WxBaseRespEntity {

    @ManyToOne
    @JoinColumn(name = "image_id")
    private WxItemImageEntity image;

    public WxItemImageEntity getImage() {
        return image;
    }

    public void setImage(WxItemImageEntity image) {
        this.image = image;
    }

    public WxRespImageEntity() {
        super();
    }

    public WxRespImageEntity(WxItemImageEntity image) {
        super();
        this.image = image;
    }

    @Override
    public String toString() {
        return "WxRespImageEntity [image=" + image + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        WxRespImageEntity other = (WxRespImageEntity) obj;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        return true;
    }

}
