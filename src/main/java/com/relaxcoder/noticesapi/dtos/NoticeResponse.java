package com.relaxcoder.noticesapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponse {
    private List<NoticeDto> notices;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int  totalPages;
    private boolean last;
}
