package com.qijianguo.ad.dao;

import com.qijianguo.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {

    AdUnit findByPlanIdIsAndUnitNameIs(Long planId, String unitName);

}
