package msa.prj.userservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import msa.prj.userservice.entity.UserEntity;
import msa.prj.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    @ResponseBody
    public int join(UserEntity userEntity){
        System.out.println("join 탐 : ");
        System.out.println(userEntity.toString());
        return userService.joinUser(userEntity);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(UserEntity userEntity) {
        System.out.println("here");
        System.out.println(userEntity.toString());
        try {
            String token = userService.login(userEntity);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }


    @GetMapping("/test")
    public String test(){
        System.out.println("here");
        //System.out.println(request.getHeader("Origin"));
        return "이게 왜 안되지..";
    }

}
