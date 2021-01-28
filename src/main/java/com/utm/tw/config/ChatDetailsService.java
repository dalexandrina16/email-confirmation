package com.utm.tw.config;

import com.utm.tw.dao.UserRepository;
import com.utm.tw.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class ChatDetailsService implements UserDetailsService {

    private final UserRepository chatterRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User chatter = chatterRepository.findByUsername(s);
        if (!Objects.nonNull(chatter)) {
            throw new UsernameNotFoundException("User not found");
        }
        return new Principal(chatter);
    }
}
