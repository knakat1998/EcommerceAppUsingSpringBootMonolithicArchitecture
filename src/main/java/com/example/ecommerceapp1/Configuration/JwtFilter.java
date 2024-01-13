package com.example.ecommerceapp1.Configuration;

import com.example.ecommerceapp1.Service.TokenService;
import org.bson.types.ObjectId;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//  JWT filter to handle authentication by validating JWT tokens.
public class JwtFilter extends GenericFilterBean {

    private TokenService tokenService;
    // creating a constructor to initialize the JwtFilter with a TokenService
    public JwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    /*       req: servlet request.
             res:servlet response.
             filterChain: filter chain for handling multiple requests.
             IOException:If an input or output exception occurs.
             ServletException:If a servlet exception occurs.
             {
             --ServletRequest :encapsulates the Communications from the client to the server
             --ServletResponse :encapsulates the Communication from the Servlet back to the client
             --HttpServletRequest:object can be used to retrieve incoming HTTP request headers and form data.
             --HttpServletResponse:object can be used to set the HTTP response headers
             }
            */
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        String token = httpServletRequest.getHeader("Authorization");

        //for extracting token from the Authorization header

        if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {  // ignorecase for getMethods available in controller(e.g:post,get)
            httpServletResponse.setStatus(HttpServletResponse.SC_OK,"success");
            return ;
        }

        //specific APIs without token--validate if the request does not require a token

        if(allowRequestWithoutToken(httpServletRequest)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK, "success");
            filterChain.doFilter(req,res);
            // filterChain for multiple request (post,get,etc)
        }
        else {
            ObjectId userId = new ObjectId(tokenService.getUserIdToken(token));
            httpServletRequest.setAttribute("userId", userId);
            filterChain.doFilter(req,res);
        }
    }

    // check if the request can be allowed  without token
    public boolean allowRequestWithoutToken(HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().contains("/users"))
            return true; //return true if the request is allowed without a token


        return false; //return false if the request is not allowed without a token
    }
}