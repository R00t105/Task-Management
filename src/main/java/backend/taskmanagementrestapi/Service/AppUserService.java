package backend.taskmanagementrestapi.Service;

import backend.taskmanagementrestapi.Dto.UserResponse;
import backend.taskmanagementrestapi.Dto.UserSignUp;
import backend.taskmanagementrestapi.Entity.AppUser;
import backend.taskmanagementrestapi.Exception.UserNotFoundException;
import backend.taskmanagementrestapi.Mapper.UserMapper;
import backend.taskmanagementrestapi.Repository.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Transactional
    public UserResponse createUser(UserSignUp userSignUp) {
        AppUser appUser = userMapper.toAppUser(userSignUp);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userMapper.toUserResponse(appUserRepository.save(appUser));
    }

}
