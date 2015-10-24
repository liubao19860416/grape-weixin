package com.saike.grape.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Liubao
 * @2015年3月18日
 * 
 */
public class BaseRequestVO implements Serializable {

    private static final long serialVersionUID = 6355821068324775293L;
    
    @NotNull
    private String appCode;
    @NotBlank
    private String appVersion;
    @Size(min = 0)
    private String deviceId;
    @Length(min = 1)
    private String plateformType;
    private String sourceCode;
    private String userAccount;
    private String userToken;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlateformType() {
        return plateformType;
    }

    public void setPlateformType(String plateformType) {
        this.plateformType = plateformType;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

}
