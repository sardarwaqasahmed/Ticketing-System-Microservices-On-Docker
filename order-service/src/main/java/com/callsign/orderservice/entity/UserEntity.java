package com.callsign.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
/**
 Author: waqas ahmed
 Date  : APR 14, 2022
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(
        name = "system_user",
        uniqueConstraints = @UniqueConstraint(name = "uc_userName", columnNames = {"user_name"})
)
public class UserEntity implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "user_name",
            nullable = false)
    @NotNull(message = "user name can not be null!")
    private String userName;

    @Column(name = "password",
            nullable = false)
    @NotNull(message = "password can not be null!")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return userName;
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

