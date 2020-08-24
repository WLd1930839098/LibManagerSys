package com.bins.service.impl;

import com.bins.bean.Record;
import com.bins.dao.RecordDao;
import com.bins.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;

    @Override
    public Page<Record> findAll(Pageable pageable) {
        return recordDao.findAll(pageable);
    }
}
