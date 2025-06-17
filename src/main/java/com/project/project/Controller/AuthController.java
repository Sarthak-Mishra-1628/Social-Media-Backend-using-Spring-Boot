package com.project.project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.project.project.Config.*;
import com.project.project.Entity.User;
import com.project.project.Repository.UserRepo;
import com.project.project.Response.AuthResponse;
import com.project.project.Service.CustomUserServiceDetails;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserServiceDetails cusd;



    @PostMapping("/signup")
    public AuthResponse create(@RequestBody User user) throws Exception {

        User isExist = userRepo.findByEmail(user.getEmail());
        if(isExist!=null) throw new Exception("email already used by another account");

        User newUser = new User();

        newUser.setEmail(user.getEmail());

        newUser.setName(user.getName());

        newUser.setPass(passwordEncoder.encode(user.getPass()));

        User savedUser = userRepo.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail() , savedUser.getPass());
        String token = JWTProvider.generateToken(authentication);

        return new AuthResponse(token , "Success");
    }



    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        
        Authentication authentication = authenticate(loginRequest.getEmail() , loginRequest.getPass());
        
        String token = JWTProvider.generateToken(authentication);

        return new AuthResponse(token , "Success");
    }
        

        
    private Authentication authenticate(String email, String pass) {
        UserDetails userDetails = cusd.loadUserByUsername(email);

        if(userDetails==null) throw new BadCredentialsException("Invalid Username");

        if(!passwordEncoder.matches(pass , userDetails.getPassword())) throw new BadCredentialsException("Invalid Passsword");
        
        return new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
    }
}