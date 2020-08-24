package com.bins.service;

import com.bins.bean.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService {
    Page<Record> findAll(Pageable pageable);
}
