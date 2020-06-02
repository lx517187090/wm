package org.vz.finance.integration.net.ui.core.filter;

import com.baomidou.mybatisplus.toolkit.Sequence;
import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import java.io.IOException;

public class MyLog4j2Filter implements Filter {
    private Sequence sequence = new Sequence();
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ThreadContext.clearAll();
        ThreadContext.put("requestId", String.valueOf(sequence.nextId()));
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
