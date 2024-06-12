package com.shyam.demo.contorolers;

import com.shyam.demo.config.JwtProvider;
import com.shyam.demo.models.User;
import com.shyam.demo.repository.UserRepository;
import com.shyam.demo.request.LoginRequest;
import com.shyam.demo.response.AuthResponse;
import com.shyam.demo.service.CustomUserService;
import com.shyam.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserService customUserService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception { // @RequestBody is that to send the data in body

        User isExist = userRepository.findByEmailId(user.getEmailId());

        if(isExist!= null){
            throw new Exception("This email already used");
        }

        User newUser= new User();
        newUser.setEmailId(user.getEmailId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassWord(passwordEncoder.encode(user.getPassWord()));

        User savedUser= userRepository.save(newUser);

        Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getEmailId(), savedUser.getPassWord());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res= new AuthResponse(token, " Register Success");

        return res;
    }

    // auth/signin
    @PostMapping("/signin")
    public AuthResponse signin (@RequestBody LoginRequest loginRequest){

        Authentication authentication= authentication(loginRequest.getEmailId(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res= new AuthResponse(token, " Login  Success");

        return res;
    }

    private Authentication authentication(String emailId, String password) {
        UserDetails userDetails= customUserService.loadUserByUsername(emailId);

        if(userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException(" invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}
