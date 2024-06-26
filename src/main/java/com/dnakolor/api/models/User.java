package com.dnakolor.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Table(name = "USERS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails, Serializable{
    @Id
    @Column(name = "ID", nullable = true, unique = true)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;
    @Column(name = "ROLE", nullable = false)
    private String role;
    @Column(name = "STATUS", nullable = false)
    private String status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }

    @Override
    public String getUsername() {
        return email;
    }
    public String getPassword() {
        return password;
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

    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = "ROLE_USER";
        this.status = "ACTIVE";
    }
}
