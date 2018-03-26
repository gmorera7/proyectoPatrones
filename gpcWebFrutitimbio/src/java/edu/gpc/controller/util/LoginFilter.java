package edu.gpc.controller.util;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import edu.sigaf.comun.webapp.SecurityLogin;

public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {  

        FacesContext ctx = FacesContext.getCurrentInstance();
        Boolean logeado = ctx.getApplication().evaluateExpressionGet(ctx,
                "#{loginBean.logeado}", Boolean.class);

        if (!logeado) {
            String contextPath = ((HttpServletRequest) request)
                    .getContextPath();
System.out.print(contextPath);
//            thisCtx.getExternalContext().invalidateSession();


            ((HttpServletResponse) response)
                    .sendRedirect("/gpcWebFrutitimbio/faces/index.xhtml");
        }

        chain.doFilter(request, response);

    }

   

    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }

    public void destroy() {
        // Nothing to do here!
    }

}
