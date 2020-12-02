package com.uzdemy.uzDemy.rest;


import com.uzdemy.uzDemy.model.ConfirmationCode;
import com.uzdemy.uzDemy.model.User;
import com.uzdemy.uzDemy.repository.ConfirmationCodeRepository;
import com.uzdemy.uzDemy.repository.UserRepository;
import com.uzdemy.uzDemy.service.UserService;
import com.uzdemy.uzDemy.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth/register")
public class RegisterAccountController {

    private UserService userService;
    private ConfirmationCodeRepository confirmationCodeRepository;
    private EmailSenderService emailSenderService;

    @Autowired
    public RegisterAccountController(UserService userService,
                                     ConfirmationCodeRepository confirmationCodeRepository,
                                     EmailSenderService emailSenderService) {
        this.userService = userService;
        this.confirmationCodeRepository = confirmationCodeRepository;
        this.emailSenderService = emailSenderService;
    }
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user){
        System.out.println("I am here");
        System.out.println(user);
        User userExist = userService.findByEmail(user.getEmail());
        if(userExist != null){
            return ResponseEntity.status(400).body("User already exist!");
        }else {
            userService.register(user);
            ConfirmationCode confirmationCode = new ConfirmationCode(user);
            confirmationCodeRepository.save(confirmationCode);
            emailSenderService.sendEmail(user, confirmationCode);
        }

        return ResponseEntity.ok("user registered!");
    }
}
