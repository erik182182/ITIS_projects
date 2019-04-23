package com.sera.wellness.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"createdArticles", "favoriteArticles", "uploadedFile"})
@ToString(exclude = {"createdArticles", "favoriteArticles", "uploadedFile"})
@Entity(name = "User")
@Table(name = "simple_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean sex;
    private String email;
    private String hashPassword;
    private Boolean consentToReceiveEmails;
    private Integer age;
    private Integer weight, growth, purposeWeight;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Article> createdArticles;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite_article",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "article_id") }
    )
    private List<Article> favoriteArticles;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", nullable = true)
    private UploadedFile uploadedFile;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return hashPassword;
    }

    @Override
    public String getUsername() {
        return email;
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
