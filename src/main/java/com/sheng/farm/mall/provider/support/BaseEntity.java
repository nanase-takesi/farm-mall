package com.sheng.farm.mall.provider.support;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author takesi
 * @date 2020-04-01
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -6665016271769605072L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "create_time", nullable = false, updatable = false)
    private Date createTime;

    @Column(name = "update_time", insertable = false)
    private Date updateTime;

}
