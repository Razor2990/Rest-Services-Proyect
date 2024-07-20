package com.proyect.services.entitys;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.proyect.services.validate.PhoneNumberFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String uid;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "third_name")
    private String thirdName;
    private String username;
    private String email;
    private String avatar;
    private String gender;
    @PhoneNumberFormat
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "social_insurance_number")
    private String socialInsuranceNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Employment employment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private CreditCard creditCard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Subscription subscription;
    
}
