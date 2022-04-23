package com.i.lostmytrousers.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Component
//@RequiredArgsConstructor
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint
{

//    @Override
//    public void commence( HttpServletRequest request, HttpServletResponse httpServletResponse, AuthenticationException exception ) throws IOException
//    {
//        httpServletResponse.addHeader( "WWW-Authenticate", "Basic realm=\\" + getRealmName() + "\\" );
//        httpServletResponse.setContentType( "application/json" );
//        httpServletResponse.setStatus( HttpStatus.UNAUTHORIZED.value() );
//
//        PrintWriter printWriter = httpServletResponse.getWriter();
//        printWriter.println( "Unauthorized access" );
//    }
//
//    @PostConstruct
//    public void initRealmname()
//    {
//        setRealmName( "trousers.zh" );
//    }
}