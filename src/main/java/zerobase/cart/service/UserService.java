package zerobase.cart.service;

import zerobase.cart.dto.Auth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zerobase.cart.domain.User;
import zerobase.cart.repository.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return (UserDetails) this.userRepository.findByUserId(userId)
                .orElseThrow(()->new UsernameNotFoundException("couldn't find user->"+userId));
    }

    public User register(Auth.SignUp user) throws Exception {

        // 가입시 중복 userId의 유무 체크
        boolean exists = this.userRepository.existsByUserId(user.getUserId());
        if(exists){
            throw new Exception("Already exists USERID!!!");
        }

        User result = this.userRepository.save(user.toEntity());

        return result;
    }

    public User authenticate(Auth.SignIn member){
        String memUserId = member.getUserId();
        String memPwd = member.getPassword();
        log.info("In service user login memUserId 1  -> " + memUserId);
        log.info("In service user login memPwd 1-2 -> " + memPwd);
        User user = this.userRepository.findByUserId(memUserId)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 ID입니다."));

        String getDbPwd = userRepository.findByUserId(memUserId).stream().map(x -> x.getPassword()).collect(Collectors.toList()).get(0).toString();

        log.info("In service getDbPwd 2 -> " + getDbPwd);
        if(!getDbPwd.matches(memPwd)){
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        return user;
    }

    public Optional<User> findById(String userId){
        if(!userRepository.existsByUserId(userId)){
            new RuntimeException("ID를 다시 확인해주세요.");
        }

        Optional<User> user = this.userRepository.findByUserId(userId);
        return user;
    }

}
