package org.vz.finance.integration.net.ui.core.utils;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T extends BaseModel<T>> extends com.baomidou.mybatisplus.mapper.BaseMapper<T> {
    List<String> selectIdPage(@Param("cm") Map<String, Object> var1);

    List<String> selectIdPage(RowBounds var1, @Param("cm") Map<String, Object> var2);
}
