package com.relaxcoder.noticesapi.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoticeDto {
    private Long id;

    @NotEmpty
    @Size(min = 8, message = "Title Should Have At Least 8 Characters")
    private String title;

    @NotEmpty
    @Size(min = 16, message = "Description Should Have At Least 8 Characters")
    private String description;

    @NotEmpty
    private String content;

}
