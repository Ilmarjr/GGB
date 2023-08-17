package br.edu.unijorge.GGB.entitys;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Table(name = "USERS")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME", unique = true)
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired;
    @Column(name = "ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked;
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired;
    @Column(name = "ENABLED")
    private Boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_PERMISSION", joinColumns = {@JoinColumn(name="ID_USER")},inverseJoinColumns = {@JoinColumn(name = "ID_PERMISSION")})
    private List<Permission> permissions;

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        for(Permission permission : permissions){
            roles.add(permission.getDescription());
        }
        return roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword(){return password;}

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(accountNonExpired, user.accountNonExpired) && Objects.equals(accountNonLocked, user.accountNonLocked) && Objects.equals(credentialsNonExpired, user.credentialsNonExpired) && Objects.equals(enabled, user.enabled) && Objects.equals(permissions, user.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, permissions);
    }
}
