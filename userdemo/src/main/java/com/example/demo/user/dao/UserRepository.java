/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.example.demo.user.dao;
import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @InterfaceName UserRepository
 * @Description 对User表进行CURD操作
 * @Author SQLUO
 * @Date 2022/5/20
 * Version 1.0
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 通过用户名查找用户信息
     * @param userName 用户名
     * @return 用户实体对象
     */
    User findByUserName(String userName);

    /**
     * 更新用户信息
     * @param userName 账号名
     * @param email 邮件地址
     * @param name 用户姓名
     * @param age 年龄
     * @param id 主键
     * @return 更新条数
     */
    @Modifying
    @Query(value="update User set userName=?1,email=?2,name=?3,age=?4,updatedDate=?5 where id=?6")
    int updateUser(String userName, String email, String name, int age, Date updateDate, Long id);

    /**
     * 逻辑删除用户
     * @param id 用户id
     * @return 删除条数
     */
    @Modifying
    @Query(value="update User set isDeleted='Y' where id=?1")
    int deleteUser(Long id);

}
