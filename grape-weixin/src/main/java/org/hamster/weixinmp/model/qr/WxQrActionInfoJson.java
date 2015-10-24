package org.hamster.weixinmp.model.qr;

/**
 * 
 * @author Liubao
 * @2015年7月9日
 *
 */
public class WxQrActionInfoJson {
    private WxQrSceneJson scene;

    public WxQrSceneJson getScene() {
        return scene;
    }

    public void setScene(WxQrSceneJson scene) {
        this.scene = scene;
    }

    public WxQrActionInfoJson(WxQrSceneJson scene) {
        super();
        this.scene = scene;
    }

    public WxQrActionInfoJson() {
        super();
    }

}
