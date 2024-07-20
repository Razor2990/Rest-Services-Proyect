package com.proyect.services.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String city;
	@Column(name = "street_name")
    private String street_name;
    @Column(name = "street_address")
    private String street_address;
    @Column(name = "zip_code")
    private String zip_code;
    private String state;
    private String country;
    
    @Embedded
    private Coordinates coordinates;
    
    @Embeddable
    @Data
    public static class Coordinates {

        private double lat;
        private double lng;

    }
}
