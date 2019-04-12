package com.sera.wellness.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Article {
    private Long id;
    private String title;
    private String text;
    private String[] resources;
    private User user;
}
