package org.vz.finance.integration.manage.sys.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.vz.finance.integration.manage.sys.dao.SysLogDao;
import org.vz.finance.integration.manage.sys.service.SysLogService;
import org.vz.finance.integration.manage.sys.util.PageUtils;
import org.vz.finance.integration.manage.sys.util.Query;
import org.vz.finance.integration.model.SysLog;

import java.util.List;
import java.util.Map;

@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysLog> page = this.selectPage(new Query<SysLog>(params).getPage(), getWrapper(params));
        return new PageUtils(page);
    }

    @Override
    public List<SysLog> queryList(Map<String, Object> params) {
        return this.selectList(getWrapper(params));
    }

    private Wrapper<SysLog> getWrapper(Map<String, Object> params) {
        String userName = (String) params.get("username");
        String thirdCode = (String) params.get("thirdCode");
        String operateType = (String) params.get("operateType");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        return new EntityWrapper<SysLog>()
                .like(StringUtils.isNotBlank(userName), "username", userName)
                .eq(StringUtils.isNotBlank(thirdCode), "third_code", thirdCode)
                .eq(StringUtils.isNotBlank(operateType), "operate_type", operateType)
                .ge(StringUtils.isNotBlank(startTime), "CREATE_TIME",
                        DateUtil.beginOfDay(DateUtil.parse(startTime, DatePattern.NORM_DATE_PATTERN)))
                .le(StringUtils.isNotBlank(endTime), "CREATE_TIME",
                        DateUtil.endOfDay(DateUtil.parse(endTime, DatePattern.NORM_DATE_PATTERN)))
                .orderBy("CREATE_TIME", false);
    }
}
