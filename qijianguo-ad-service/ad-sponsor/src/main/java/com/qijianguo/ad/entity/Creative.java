package com.qijianguo.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 创意：指的是给用户观看的数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creative")
public class Creative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "type", nullable = false)
    private Integer type;

    /**
     * 物料类型： 比如图片是png/jpg/...
     */
    @Basic
    @Column(name = "material_type", nullable = false)
    private Integer materialType;

    @Basic
    @Column(name = "height", nullable = false)
    private Integer height;

    @Basic
    @Column(name = "weight", nullable = false)
    private Integer weight;

    /**
     * 物料大小
     */
    @Basic
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * 持续时长（只有视频不为0）
     */
    @Basic
    @Column(name = "duration", nullable = false)
    private Long duration;

    /**
     * 审核状态
     */
    @Basic
    @Column(name = "auditStatus", nullable = false)
    private Integer auditStatus;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "url", nullable = false)
    private String url;

    @Basic
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Basic
    @Column(name = "update_date", nullable = false)
    private Date updateDate;


}
