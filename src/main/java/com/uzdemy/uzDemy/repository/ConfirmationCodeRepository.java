package com.uzdemy.uzDemy.repository;

import com.uzdemy.uzDemy.model.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {
    ConfirmationCode findByConfirmationCode(String code);
}
