package com.relaxcoder.noticesapi.service.impl;


import com.relaxcoder.noticesapi.dtos.NoticeDto;
import com.relaxcoder.noticesapi.dtos.NoticeResponse;
import com.relaxcoder.noticesapi.entity.Notice;
import com.relaxcoder.noticesapi.repository.NoticeRepository;
import com.relaxcoder.noticesapi.service.NoticeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private ModelMapper modelMapper;

    public NoticeServiceImpl(NoticeRepository noticeRepository, ModelMapper modelMapper) {
        this.noticeRepository = noticeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NoticeDto createNotice(NoticeDto noticeDto) {
        Notice newNotice = noticeRepository.save(mapToEntity(noticeDto));
        return mapToDto(newNotice);
    }

    @Override
    public NoticeResponse getAllNotices(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Notice> noticePage = noticeRepository.findAll(pageable);

        List<NoticeDto> content = noticePage.getContent().stream().map(this::mapToDto).toList();

        NoticeResponse noticeResponse = NoticeResponse.builder()
                .notices(content)
                .pageNo(noticePage.getNumber())
                .pageSize(noticePage.getSize())
                .totalElements(noticePage.getTotalElements())
                .totalPages(noticePage.getTotalPages())
                .last(noticePage.isLast())
                .build();

        return noticeResponse;
    }

    private NoticeDto mapToDto(Notice notice){
        return modelMapper.map(notice, NoticeDto.class);
    }

    private Notice mapToEntity(NoticeDto noticeDto){
        return modelMapper.map(noticeDto, Notice.class);
    }
}
