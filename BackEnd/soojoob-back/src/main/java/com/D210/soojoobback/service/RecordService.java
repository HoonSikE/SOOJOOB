package com.D210.soojoobback.service;

import com.D210.soojoobback.entity.Record;
import com.D210.soojoobback.repository.RecordRepository;
import com.D210.soojoobback.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public Record detailsRecordrInfo(UserDetailsImpl userDetails) {
        Long recordId = userDetails.getUser().getId();
        Record record = recordRepository.findById(recordId);

        return record;
    }
}