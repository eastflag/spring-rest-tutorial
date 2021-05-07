package com.eastflag.fullstack.security;

import com.eastflag.fullstack.domain.UserVO;
import com.eastflag.fullstack.persistence.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthMapper authMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserVO user = authMapper.findOneByEmail(email);
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }

        List<String> roles = authMapper.findRoles(user.getId());
        user.setRoles(roles);

        return UserDetailsImpl.build(user);
    }
}
