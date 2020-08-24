package com.bins.controller;

import com.bins.bean.Record;
import com.bins.dao.RecordDao;
import com.bins.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping
    public String allRecords(@PageableDefault(size = 5, sort = {"time"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<Record> page = recordService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/records";
    }

    @RequestMapping("borrowRecord")
    public String allBorrowRecords(@PageableDefault(size = 5, sort = {"time"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<Record> page = recordService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/records-borrow";
    }

    @RequestMapping("returnRecord")
    public String allReturnRecords(@PageableDefault(size = 5, sort = {"time"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<Record> page = recordService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/records-return";
    }

    @RequestMapping("search")
    public String search(@PageableDefault(size = 5, sort = {"time"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model,
                         String userName, String bookName) {
        Page<Record> page=null;
        if(!userName.equals("")&&!bookName.equals("")){
            page = recordService.findAllByUserByBook(userName, bookName, pageable);
        }
        if(userName.equals("")||bookName.equals("")){
            page = recordService.findAll(pageable);
        }
        if(!userName.equals("")){
            page = recordService.searchByUserName(userName, pageable);
        }
        if(!bookName.equals("")){
            page = recordService.searchByBookName(bookName, pageable);
        }
        model.addAttribute("page", page);
        return "admin/records::recordList";
    }

}
