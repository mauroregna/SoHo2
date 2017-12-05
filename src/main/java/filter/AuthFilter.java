package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.AuthMb;

@WebFilter(filterName="authFilter",urlPatterns="*.xhtml")
public class AuthFilter implements Filter{
	
	private static final List<String> publicPath =
			Arrays.asList(
					"/",
					"/index.xhtml",
					"/register.xhtml",
					"/login.xhtml",
					".*\\.js.xhtml",
					".*\\.css.xhtml");

	@Inject
	private AuthMb authMb;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
	try{	
		HttpServletRequest servReq = (HttpServletRequest) req;
		HttpServletResponse servResp = (HttpServletResponse) resp;
		
		final String path = getCurrentPath(servReq);
				
		if(servReq.getRequestURI().equals("/home.xhtml") && !authMb.isLogged()){
			servResp.sendRedirect("login.xhtml");
		}else if(servReq.getRequestURI().equals("/myprofile.xhtml") && !authMb.isLogged()){
			servResp.sendRedirect("login.xhtml");
		}else if(publicPath.stream().anyMatch((pp) -> path.matches(pp))) {
			chain.doFilter(req, resp);
			return;
		}else if (authMb != null && authMb.isLogged()) {
			chain.doFilter(req, resp);
			return;
		}
	} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	private String getCurrentPath(HttpServletRequest servReq) {
		String uri = servReq.getRequestURI().replaceAll(";.*", "");
		String contextPath = servReq.getContextPath();

		if (uri.startsWith(contextPath)) {
			uri = uri.substring(contextPath.length());
		}
		return uri;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}