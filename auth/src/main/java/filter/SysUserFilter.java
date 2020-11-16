package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;

public class SysUserFilter implements Filter {
	
	static List<String> passUrls;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 请求的url
        String url = request.getRequestURI();
        if(!CollectionUtils.isEmpty(passUrls)) {
        	for(String str : passUrls) {
        		if(url.indexOf(str) > -1) {
        			filterChain.doFilter(request, response);
        		}
        	}
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 获取web.xml中的初始化参数
        String ignoreURL = filterConfig.getInitParameter("passURL");
        List<String> passUrls = new ArrayList<>();
        // 保存不拦截的url
        String[] ignoreURLArray = ignoreURL.split(",");
        for (String url : ignoreURLArray) {
        	passUrls.add(url.trim());
        }
	}

}
