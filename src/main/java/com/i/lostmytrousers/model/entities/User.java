package com.i.lostmytrousers.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class User implements UserDetails
{
    @Id
    @GeneratedValue
    private Long userId;
    @Column( unique = true )
    private String username;
    private String password;
    private String role;
    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Trouser> trousers = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        roles.add( new SimpleGrantedAuthority( "ROLE_USER" ) );
        return roles;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}