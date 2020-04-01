package com.sheng.farm.mall.provider.model;

import com.sheng.farm.mall.provider.support.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author takesi
 * @date 2020-04-01
 */
@Data
@Entity
@Table(name = "tb_user")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

    private static final long serialVersionUID = 8649234797588554891L;

    @Column(name = "username", length = 45, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 45, nullable = false)
    private String password;

    @Column(name = "email", length = 45, nullable = false, unique = true)
    private String email;

    @Column(name = "avatar", length = 100)
    private String avatar;

    @Column(name = "sex", nullable = false)
    private Short sex;

    @Column(name = "birthday")
    private Date birthday;

}
