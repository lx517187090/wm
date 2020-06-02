package org.vz.finance.integration.net.ui.core.constant;

public interface SysConstants {

    int DEFAULT_CURRENT_PAGE = 1;

    int DEFAULT_PAGE_SIZE = 10;

    String CELL_FORMAT_FILTER = "org.vz.finance.integration.net.ui.core.excel.filter.CellFormatFilter";

    /**
     * 操作日志类型 更新
     */
    Integer LOG_TYPE_UPDATE = 2;

    /**
     * 操作日志类型 下载
     */
    Integer LOG_TYPE_DOWNLOAD = 4;

    /**
     * 操作日志类型 删除
     */
    Integer LOG_TYPE_DELETE = 3;

    /**
     * 操作日志类型 导出
     */
    Integer LOG_TYPE_EXPORT = 5;

    /**
     * 角色类型 管理员
     */
    String ROLE_TYPE_SYS = "0";
}
