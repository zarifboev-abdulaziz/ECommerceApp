package uz.pdp.olchauzcloneapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.olchauzcloneapp.entity.template.AbsEntity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {
    private String fullName;
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private LocalDate dateOfBirth;

    @ManyToOne
    private Gender gender;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    private boolean enabled ;




    //UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> permissions = new HashSet<>();
        for (Role role : this.roles) {
            role.getPermissions().forEach(permission -> permissions.add(permission.getName()));
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        permissions.forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission)));
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.email;
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

    public User(String fullName, String email, String password, Set<Role> roles) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
