package com.qijianguo.ad.entity;

import com.qijianguo.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 推广单元
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_unit")
public class AdUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long planId;

    @Basic
    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Basic
    @Column(name = "unit_status", nullable = false)
    private Integer unitStatus;

    /**
     * 广告位标识：类型（开屏/贴片/中贴。。）
     */
    @Basic
    @Column(name = "position_type", nullable = false)
    private Integer positionType;

    /**
     * 预算
     */
    @Basic
    @Column(name = "budget" , nullable = false)
    private Long budget;

    @Basic
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Basic
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    public AdUnit(Long planId, String unitName, Integer positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.positionType = positionType;
        this.budget = budget;
        Date now = new Date();
        this.createDate = now;
        this.createDate = now;

    }


}
