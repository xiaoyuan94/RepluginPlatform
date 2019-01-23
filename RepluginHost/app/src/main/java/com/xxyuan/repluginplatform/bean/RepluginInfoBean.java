package com.xxyuan.repluginplatform.bean;

import java.io.Serializable;

/**
 * author:xuxiaoyuan
 * date:2019/1/21
 */
public class RepluginInfoBean implements Serializable {
    private String id;
    private String rePluginName;
    private String rePluginUrl;
    private String rePluginDownPath;
    private String rePluginIconUrl;
    public int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRePluginName() {
        return rePluginName;
    }

    public void setRePluginName(String rePluginName) {
        this.rePluginName = rePluginName;
    }

    public String getRePluginUrl() {
        return rePluginUrl;
    }

    public void setRePluginUrl(String rePluginUrl) {
        this.rePluginUrl = rePluginUrl;
    }

    public String getRePluginDownPath() {
        return rePluginDownPath;
    }

    public void setRePluginDownPath(String rePluginDownPath) {
        this.rePluginDownPath = rePluginDownPath;
    }

    public String getRePluginIconUrl() {
        return rePluginIconUrl;
    }

    public void setRePluginIconUrl(String rePluginIconUrl) {
        this.rePluginIconUrl = rePluginIconUrl;
    }
}
