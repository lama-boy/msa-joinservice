package msa.prj.userservice.service;

import lombok.RequiredArgsConstructor;
import msa.prj.userservice.entity.UserEntity;
import msa.prj.userservice.jwt.JwtUtil;
import msa.prj.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtUtil jwtUtil;

    public int joinUser(UserEntity userEntity){
        userEntity.setUser_pw(bCryptPasswordEncoder.encode(userEntity.getUser_pw()));
        int userIdExist = userRepository.userIdVerification(userEntity.getUser_id());
        System.out.println(userEntity.toString());
        if(userIdExist < 1){
            userRepository.save(userEntity);
            return 1;
        }else{
            return 0;
        }
    }

    //        UsernamePasswordAuthenticationToken authRequest =
    //        new UsernamePasswordAuthenticationToken(userEntity.getUser_id(), userEntity.getUser_pw(), Collections.emptyList());
    public String login(UserEntity userEntity) {
        System.out.println("userService login method");
        System.out.println(userEntity.getUser_id());
        UserEntity nUserEntity = userRepository.findByUserId(userEntity.getUser_id());
        System.out.println("login service userRepository 뒤");
        if (nUserEntity == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found");
        }
        boolean isMatch = bCryptPasswordEncoder.matches(userEntity.getUser_pw(), nUserEntity.getUser_pw());
        System.out.println("uesr match status : " + isMatch);
        return isMatch ? jwtUtil.createJwt(nUserEntity, 7) : "failed";
    }

}
