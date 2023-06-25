package com.relaxcoder.noticesapi.dtos;


import lombok.Data;

@Data
public class NoticeDto {
    private Long id;

    private String title;
    private String description;
    private String content;

}
