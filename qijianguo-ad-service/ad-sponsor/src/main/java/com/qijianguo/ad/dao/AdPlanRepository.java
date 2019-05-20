package com.qijianguo.ad.dao;

import com.qijianguo.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    AdPlan findByIdIsAndUserIdIs(Long id, Long userId);

    List<AdPlan> findByIdInAndUserIdIs(List<Long> ids, Long userId);

    AdPlan findByUserIdIsAndPlanNameIs(Long userId, String planName);

    List<AdPlan> findByPlanStatusIs(Integer planStatus);
}
