package org.vz.finance.integration.net.ui.modules.entity;

public class WmConfig {
    private String id;

    private String confName;

    private String confKey;

    private String confGroup;

    private Integer currentPage = 1;
    private Integer pageSize = 10;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName == null ? null : confName.trim();
    }

    public String getConfKey() {
        return confKey;
    }

    public void setConfKey(String confKey) {
        this.confKey = confKey == null ? null : confKey.trim();
    }

    public String getConfGroup() {
        return confGroup;
    }

    public void setConfGroup(String confGroup) {
        this.confGroup = confGroup == null ? null : confGroup.trim();
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}