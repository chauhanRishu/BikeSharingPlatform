package com.happyJourney.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.happyJourney.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;
    @Column(name = "name")
    private String usersName;
    @Column(name = "aadhaar_Id")
    private String userAadhaarNumber;
    @Column(name = "contact",length = 10)
    private String userMobileNumber;
    @Column(name = "email")
    private String userEmail;
    @Column(name = "password")
    private String userPassword;
    @Column(name = "license")
    private String driverLicense;
    @Column(name = "role")
    private UserRole userRole;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Bike bike;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ride> rides;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
