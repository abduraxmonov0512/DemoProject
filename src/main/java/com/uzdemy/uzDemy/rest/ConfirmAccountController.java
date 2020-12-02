package com.uzdemy.uzDemy.rest;


import com.uzdemy.uzDemy.model.ConfirmationCode;
import com.uzdemy.uzDemy.model.Status;
import com.uzdemy.uzDemy.model.User;
import com.uzdemy.uzDemy.repository.ConfirmationCodeRepository;
import com.uzdemy.uzDemy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.uzdemy.uzDemy.model.Status.ACTIVE;

@RestController
@RequestMapping("/api/auth/confirm-account")
public class ConfirmAccountController {

    private UserRepository userRepository;
    private ConfirmationCodeRepository confirmationCodeRepository;

    @Autowired
    public ConfirmAccountController(UserRepository userRepository, ConfirmationCodeRepository confirmationCodeRepository) {
        this.userRepository = userRepository;
        this.confirmationCodeRepository = confirmationCodeRepository;
    }

    @PutMapping
    public ResponseEntity<String> confirmAccount (@RequestParam("code") String code){

        ConfirmationCode confirmationCode = confirmationCodeRepository.findByConfirmationCode(code);
        if(confirmationCode != null){
            User user = userRepository.findByEmail(confirmationCode.getUser().getEmail());
            user.setStatus(ACTIVE);
            userRepository.save(user);
            return ResponseEntity.ok("User activated!");
        }else {
            return ResponseEntity.status(400).body("User not found!");
        }

    }
}
