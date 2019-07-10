package com.liaody.ssl.controller;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.liaody.ssl.constants.ResultCode;
import com.liaody.ssl.constants.ResultMessage;
import com.liaody.ssl.dao.UserMapper;
import com.liaody.ssl.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 * @author yuanhaha
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/select-user")
    public ResultMessage selectUser(@RequestParam Integer userId){
        if(userId==null || userId<1){
            return ResultCode.BAD_REQUEST_PARAMS.withMessage("用户id不能为空且大于0");
        }
        User user = userMapper.selectUser(userId);
        return ResultCode.SUCCESS.withData(user);
    }
    @GetMapping("/select-all-users")
    public ResultMessage selectAllUsers(@RequestParam Integer pageSize,
                                        @RequestParam Integer currentPage){

        PageHelper.startPage(currentPage,pageSize);
        List<User> users = userMapper.selectAllUsers();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return ResultCode.SUCCESS.withData(userPageInfo);
    }

    @PostMapping("/insert-update-user")
    public ResultMessage insertOrUpdate(@RequestBody User user){

        userMapper.insertOrUpdate(user);
        return ResultCode.SUCCESS.withMessage("插入或更新成功");
    }

    @PostMapping("/batch-insert-user")
    public ResultMessage batchInsertUser(@RequestBody List<User> users){
        userMapper.batchInsert(users);
        return ResultCode.SUCCESS.withMessage("插入成功");
    }
}
