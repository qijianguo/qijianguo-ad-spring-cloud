package com.qijianguo.ad.service.impl;

import com.qijianguo.ad.constant.CommonStatus;
import com.qijianguo.ad.constant.Contants;
import com.qijianguo.ad.dao.AdPlanRepository;
import com.qijianguo.ad.dao.AdUserRepository;
import com.qijianguo.ad.entity.AdPlan;
import com.qijianguo.ad.entity.AdUser;
import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.service.IAdPlanService;
import com.qijianguo.ad.util.CommonUtils;
import com.qijianguo.ad.vo.AdPlanGetRequest;
import com.qijianguo.ad.vo.AdPlanRequest;
import com.qijianguo.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdPlanRepository adPlanRepository;
    private final AdUserRepository adUserRepository;

    @Autowired
    public AdPlanServiceImpl(AdPlanRepository adPlanRepository, AdUserRepository adUserRepository) {
        this.adPlanRepository = adPlanRepository;
        this.adUserRepository = adUserRepository;
    }

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Contants.ErrorMsg.CANNOT_FIND_RECORD);
        }

        AdPlan oldAdPlan = adPlanRepository.findByUserIdIsAndPlanNameIs(request.getUserId(), request.getPlanName());
        if (oldAdPlan != null) {
            throw new AdException(Contants.ErrorMsg.SAME_RECORD_ERROR);
        }

        AdPlan adPlan = adPlanRepository.save(new AdPlan(request.getUserId(), request.getPlanName(),
                CommonUtils.parseStringDate(request.getStartDate()),
                CommonUtils.parseStringDate(request.getEndDate()))
        );

        return new AdPlanResponse(adPlan.getId(), adPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        return adPlanRepository.findByIdInAndUserIdIs(request.getAdPlanIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan oldPlan = adPlanRepository.findByIdIsAndUserIdIs(request.getId(), request.getUserId());
        if (oldPlan == null) {
            throw new AdException(Contants.ErrorMsg.CANNOT_FIND_RECORD);
        }
        if (request.getPlanName() != null) {
            oldPlan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            oldPlan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }
        if (request.getEndDate() != null) {
            oldPlan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }
        oldPlan.setUpdateDate(new Date());
        oldPlan = adPlanRepository.save(oldPlan);
        return new AdPlanResponse(oldPlan.getId(), oldPlan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan oldPlan = adPlanRepository.findByIdIsAndUserIdIs(request.getId(), request.getUserId());
        if (oldPlan == null) {
            throw new AdException(Contants.ErrorMsg.CANNOT_FIND_RECORD);
        }
        oldPlan.setPlanStatus(CommonStatus.INVALID.getStatus());
        oldPlan.setUpdateDate(new Date());
        adPlanRepository.save(oldPlan);
    }
}
