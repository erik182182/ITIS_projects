package com.sera.wellness.forms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleAddForm {
    private String title;
    private String text;
}
