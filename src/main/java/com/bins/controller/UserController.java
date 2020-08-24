package com.bins.controller;

import com.bins.bean.User;
import com.bins.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "redirect:/user/login";
    }

    @RequestMapping
    public String allUsers(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<User> page = userService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/users";
    }

    @RequestMapping("admins")
    public String admins(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<User> page = userService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/users::userList";
    }

    @RequestMapping("users")
    public String users(@PageableDefault(size = 5, sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        Page<User> page = userService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/users::userList";
    }

    @RequestMapping("toInput/{id}")
    public String toInput(@PathVariable Long id, Model model){

        if(id==-1){//新增
            model.addAttribute("user",new User());
        }else{
            User user = userService.findById(id);
            model.addAttribute("user",user);
        }
        return "admin/users-input";
    }

    @RequestMapping("add")
    public String add(User user){
        userService.add(user);
        return "redirect:/admin/user";
    }

}
