package com.relaxcoder.noticesapi.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ErrorDetailResponse {
    private Date timestamp;
    private String message;
    private String details;

}
