package com.qijianguo.ad.service;

import com.qijianguo.ad.entity.AdPlan;
import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.vo.AdPlanGetRequest;
import com.qijianguo.ad.vo.AdPlanRequest;
import com.qijianguo.ad.vo.AdPlanResponse;

import java.util.List;

public interface IAdPlanService {

    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    List<AdPlan> getAdplanByIds(AdPlanGetRequest request) throws AdException;

    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
