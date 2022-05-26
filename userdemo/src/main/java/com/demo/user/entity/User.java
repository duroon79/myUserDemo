/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.entity;





import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


/**
 * @ClassName User
 * @Description 用户实体类
 * @Author SQLUO
 * @Date 2022/5/20
 * Version 1.0
 **/
@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class User{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_name")
    private String userName;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private String email;
    @Column(name="is_deleted")
    private String isDeleted;
    @Column(name="create_date")
    private Date createDate;
    @Column(name="create_by")
    private Long createdBy;
    @Column(name="update_date")
    private Date updatedDate;
    @Column(name="update_by")
    private Long updatedBy;

}
