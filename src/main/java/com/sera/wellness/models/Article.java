package com.sera.wellness.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Article")
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String title;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "article")
    private List<Comment> comments;
    @Transient
    private Float averageGrade;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_img")
    private UploadedFile mainImg;
}
