package com.liaody.ssl.dao;


import com.liaody.ssl.entity.User;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanhaha
 */
@Repository
public interface UserMapper {

    /**
     * 通过id查找用户
     * @param id 用户id
     * @return 用户实体
     */
    User selectUser(@Param("id") int id);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> selectAllUsers();

    /**
     * 插入用户
     * @param user user实体
     * @return 影响记录数
     */
    int insert(User user);

    /**
     * 插入或者更新用户
     * @param user user实体
     * @return 影响记录数
     */
    int insertOrUpdate(User user);

    /**
     * 批量插入
     * @param users user实体列表
     * @return 影响记录数
     */
    int batchInsert(List<User> users);

}
