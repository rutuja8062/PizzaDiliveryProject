package com.example.pizzaDelivery.Service.Implementation;

import com.example.pizzaDelivery.Dto.LoginDto;
import com.example.pizzaDelivery.Dto.UserDto;
import com.example.pizzaDelivery.Entity.User;
import com.example.pizzaDelivery.Repository.UserRepository;
import com.example.pizzaDelivery.Response.LoginResponse;
import com.example.pizzaDelivery.Response.SigninResponse;
import com.example.pizzaDelivery.Service.Token;
import com.example.pizzaDelivery.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
public class UserImplementation implements UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Token token;


    /*@Override
    public String addUser(UserDto userDTO,HttpServletResponse servletResponse)
            throws ServletException, IOException {

            User oldUser = userRepository.findByEmail(userDTO.getEmail());
            if(oldUser==null) {

                String userName = userDTO.getUserName();
                String password = userDTO.getPassword();
                String email = userDTO.getEmail();

                if (userName != null && !userName.isBlank() && password != null && !password.isBlank()
                        && email != null && !email.isBlank()) {

                        User pizzaUser = new User(
                                userDTO.getUserId(),
                                userDTO.getUserName(),
                                userDTO.getEmail(),
                                this.passwordEncoder.encode(userDTO.getPassword())
                        );

                        userRepository.save(pizzaUser);
                        return pizzaUser.getUserName();
                }
                else{
                    return "please provide username,email and password";
                }
            }else{
                return "Failed to add user. Email already exits";
            }
    }*/

    @Override
    public SigninResponse addUser(UserDto userDTO,HttpServletResponse servletResponse)
            throws ServletException, IOException {
        try {
            User oldUser = userRepository.findByEmail(userDTO.getEmail());
            if (oldUser == null) {
                String userName = userDTO.getUserName();
                String password = userDTO.getPassword();
                String email = userDTO.getEmail();

                if (userName != null && !userName.isBlank() && password != null && !password.isBlank() && email != null
                        && !email.isBlank()) {
                    User pizzaUser = new User(
                            userDTO.getUserId(),
                            userName,
                            email,
                            this.passwordEncoder.encode(password)
                    );

                    userRepository.save(pizzaUser);
                    //returnResponseEntity.ok("User added successfully. " +" Name : "+ */userDTO.getUserName();
                    return new SigninResponse(userDTO.getUserName() + " user added successfully",true);

                } else {
                    return new SigninResponse("Please provide username, email, and password.",false);
                    /*return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Please provide username, email, and password.");*/
                }
            } else {
                return new SigninResponse(" Email already exists.", false);
                //return ResponseEntity.badRequest().body("Failed to add user. Email already exists.");
            }

        } catch (Exception ex) {
            return new SigninResponse("Sign-in Failed. " + ex.getMessage(), false);
        }
            /*catch (Exception ex){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to add user. Please check the input data.");
           }*/
    }


    @Override
    public LoginResponse loginUser(LoginDto loginDTO) {

        User user1 = userRepository.findByEmail(loginDTO.getEmail());

        try{
            if (user1 != null) {
                String password = loginDTO.getPassword();
                String encodedPassword = user1.getPassword();
                Boolean isPasswdRight = passwordEncoder.matches(password, encodedPassword);
                if (isPasswdRight) {
                    Optional<User> user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                    if (user.isPresent()) {
                        return new LoginResponse("Login Success", true, token.generateToken(user1));
                    } else {
                        return new LoginResponse("Login Failed", false, "No token generated");
                    }
                } else {
                    return new LoginResponse("password Not Match", false, "No token generated");
                }
            } else {
                return new LoginResponse("Email not exits", false, "No token generated");
            }

        }
        catch (IllegalArgumentException ex) {
        return new LoginResponse("Login Failed. " + ex.getMessage(), false, "No token generated");
        }
    }

}
