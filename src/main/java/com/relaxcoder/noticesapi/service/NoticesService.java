package com.relaxcoder.noticesapi.service;

import com.relaxcoder.noticesapi.dtos.NoticeDto;
import com.relaxcoder.noticesapi.dtos.NoticeResponse;

public interface NoticesService {
    NoticeDto createNotice(NoticeDto noticeDto);
    NoticeResponse getAllNotices(int pageNo, int pageSize, String sortBy, String sortDir);
}
