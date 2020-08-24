package com.bins.dao;

import com.bins.bean.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordDao extends JpaRepository<Record,Long> {
}
