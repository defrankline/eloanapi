package com.kachinga.eloanapi.security;

import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsernameOrEmailOrMobile(username,username,username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found for the username: " + username));

        return UserDetailsImpl.build(user);
    }

}
