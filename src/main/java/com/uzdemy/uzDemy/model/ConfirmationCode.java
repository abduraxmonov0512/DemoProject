package com.uzdemy.uzDemy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "confirmation_codes")
@Data
public class ConfirmationCode extends BaseEntity{

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ConfirmationCode(User user){
        this.user = user;
        confirmationCode = UUID.randomUUID().toString();
    }

    public ConfirmationCode(){}
}
