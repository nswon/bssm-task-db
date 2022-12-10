package taskdb.taskdb.infrastructure.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import taskdb.taskdb.domain.user.exception.UserNotFoundException;
import taskdb.taskdb.adapter.user.out.persistence.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmailValue(email)
                .map(CustomUserDetails::new)
                .orElseThrow(UserNotFoundException::new);
    }
}
