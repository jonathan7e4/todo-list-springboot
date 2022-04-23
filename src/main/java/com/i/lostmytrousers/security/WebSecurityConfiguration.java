package com.i.lostmytrousers.security;

import com.i.lostmytrousers.model.services.DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
//    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final DetailsService detailsService;
    private final PasswordEncoder passwordEncoder;

//    /**
//     * In memory authentication.
//     */
//    @Override
//    protected void configure( AuthenticationManagerBuilder authenticationManagerBuilder ) throws Exception
//    {
//        authenticationManagerBuilder.inMemoryAuthentication().passwordEncoder( passwordEncoder ).withUser( "sampleUser" ).password( passwordEncoder.encode( "password" ) ).roles( "ADMIN" );
//    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth.userDetailsService( detailsService ).passwordEncoder( passwordEncoder );
    }

//    @Override
//    protected void configure( HttpSecurity http ) throws Exception
//    {
//        http.cors()
//                .and()
//                .httpBasic().authenticationEntryPoint( authenticationEntryPoint )
//                .and().authorizeRequests()
//                .antMatchers( HttpMethod.GET, "/me/**" ).hasRole( "ADMIN" )
//                .antMatchers( HttpMethod.GET, "/users/**", "/videojocs/**" ).hasRole( "USER" )
//                .antMatchers( HttpMethod.POST, "/users/**", "/videojocs/**" ).hasRole( "USER" )
//                .antMatchers( HttpMethod.PUT, "/videojocs/**" ).hasRole( "USER" )
//                .antMatchers( HttpMethod.DELETE, "/videojocs/**" ).hasRole( "ADMIN" )
//                .antMatchers( HttpMethod.POST, "/videojocs/**" ).hasAnyRole( "USER", "ADMIN" )
//                .anyRequest().authenticated();
//        // .and()
//        // .csrf().disable();
//    }
//    /**
//     * Disables security.
//     */
//    @Override
//    public void configure( WebSecurity web )
//    {
//        web.ignoring().anyRequest();
//    }

    // SUPER SECRET COOL USER & PASSWORD LIST OVER HERE
    // 1111          -    1111
    // sampleUser    -    password
    // username      -    password
}