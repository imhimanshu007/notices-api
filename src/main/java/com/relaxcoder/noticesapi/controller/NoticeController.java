package com.relaxcoder.noticesapi.controller;


import com.relaxcoder.noticesapi.dtos.NoticeDto;
import com.relaxcoder.noticesapi.dtos.NoticeResponse;
import com.relaxcoder.noticesapi.service.NoticeService;
import com.relaxcoder.noticesapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("createNotice")
    public ResponseEntity<NoticeDto> createNotice(@Valid @RequestBody NoticeDto noticeDto){
        return new ResponseEntity<NoticeDto>(noticeService.createNotice(noticeDto), HttpStatus.CREATED);
    }


    @GetMapping("getNotices")
    public NoticeResponse getAllNotices(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    ){
        return noticeService.getAllNotices(pageNo, pageSize, sortBy, sortDir);
    }

}
