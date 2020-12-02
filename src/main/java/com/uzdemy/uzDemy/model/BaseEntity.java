package com.uzdemy.uzDemy.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreatedDate
    @CreationTimestamp
    @Column(name = "created")
    private Date created;


    @LastModifiedDate
    @CreationTimestamp
    @Column(name = "updated")
    private Date updated;

//    @PrePersist
//    public void prePersist() {
//        created = new Date();
//        updated = new Date();
//    }

}
