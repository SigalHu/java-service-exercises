package com.sigalhu.jse.springmvc.spittr.core;

import com.sigalhu.jse.springmvc.spittr.Spitter;
import com.sigalhu.jse.springmvc.spittr.data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huxujun
 * @date 2018/10/28
 */
@Service
public class SpitterUserServiceImpl implements UserDetailsService {

    @Autowired
    private SpitterRepository spitterRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Spitter spitter = spitterRepository.findByUsername(username);
        if (spitter != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            //创建权限列表
            authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));

            return new User(spitter.getUsername(), spitter.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
