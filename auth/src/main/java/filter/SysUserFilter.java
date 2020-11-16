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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

public class SysUserFilter implements Filter {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Override
	public void destroy() {
		redisTemplate.delete("passURL");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 请求的url
        String url = request.getRequestURI();
        List<String> list = (List<String>) redisTemplate.opsForValue().get("passURL");
        if(!CollectionUtils.isEmpty(list)) {
        	for(String str : list) {
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
        List<String> list = new ArrayList<>();
        // 保存不拦截的url
        String[] ignoreURLArray = ignoreURL.split(",");
        for (String url : ignoreURLArray) {
        	list.add(url.trim());
        }
        //存入redis
        redisTemplate.opsForValue().set("passURL", list);
	}

}
