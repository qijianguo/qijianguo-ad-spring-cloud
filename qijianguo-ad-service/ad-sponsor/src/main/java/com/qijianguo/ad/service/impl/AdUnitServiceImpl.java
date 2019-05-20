package com.qijianguo.ad.service.impl;

import com.qijianguo.ad.constant.Contants;
import com.qijianguo.ad.dao.AdPlanRepository;
import com.qijianguo.ad.dao.AdUnitRepository;
import com.qijianguo.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.qijianguo.ad.dao.unit_condition.AdUnitItRepository;
import com.qijianguo.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.qijianguo.ad.entity.AdPlan;
import com.qijianguo.ad.entity.AdUnit;
import com.qijianguo.ad.entity.unit_condition.AdUnitDistrict;
import com.qijianguo.ad.entity.unit_condition.AdUnitIt;
import com.qijianguo.ad.entity.unit_condition.AdUnitKeyword;
import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.service.IAdUnitService;
import com.qijianguo.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class AdUnitServiceImpl implements IAdUnitService{

    @Autowired
    private AdPlanRepository adPlanRepository;

    @Autowired
    private AdUnitRepository adUnitRepository;

    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;

    @Autowired
    private AdUnitItRepository adUnitItRepository;

    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<AdPlan> adPlan = adPlanRepository.findById(request.getPlanId());
        if (!adPlan.isPresent()) {
            throw new AdException(Contants.ErrorMsg.CANNOT_FIND_RECORD);
        }

        AdUnit adUnit = adUnitRepository.findByPlanIdIsAndUnitNameIs(request.getPlanId(), request.getUnitName());
        if (adUnit != null) {
            throw new AdException(Contants.ErrorMsg.SAME_RECORD_ERROR);
        }

        adUnit = adUnitRepository.save(new AdUnit(request.getPlanId(), request.getUnitName(), request.getPositionType(),
                request.getBudget()));

        return new AdUnitResponse(adUnit.getId(), adUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword :: getUnitId)
                .collect(Collectors.toList());
        if (isRelatedUnitExist(unitIds)) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitKeyword> keywords = Collections.emptyList();
        request.getUnitKeywords().forEach(unitKeyword -> keywords.add(new AdUnitKeyword(unitKeyword.getUnitId(), unitKeyword.getKeyword())));
        List<Long> ids = adUnitKeywordRepository.saveAll(keywords).stream().map(AdUnitKeyword::getId).collect(Collectors.toList());
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt :: getUnitId)
                .collect(Collectors.toList());
        if (isRelatedUnitExist(unitIds)) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitIt> adUnitIts = Collections.emptyList();
        request.getUnitIts().forEach(unitKeyword -> adUnitIts.add(new AdUnitIt(unitKeyword.getUnitId(), unitKeyword.getItTag())));
        List<Long> ids = adUnitItRepository.saveAll(adUnitIts).stream().map(AdUnitIt::getId).collect(Collectors.toList());
        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict :: getUnitId)
                .collect(Collectors.toList());
        if (isRelatedUnitExist(unitIds)) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitDistrict> adUnitDistricts = Collections.emptyList();
        request.getUnitDistricts().forEach(unitDistrict -> adUnitDistricts.add(new AdUnitDistrict(unitDistrict.getUnitId(), unitDistrict.getProvince(), unitDistrict.getCity())));
        List<Long> ids = adUnitDistrictRepository.saveAll(adUnitDistricts).stream().map(AdUnitDistrict::getId).collect(Collectors.toList());
        return new AdUnitDistrictResponse(ids);
    }

    private boolean isRelatedUnitExist(List<Long> unitIds) {
        return CollectionUtils.isEmpty(unitIds) || adUnitRepository.findAllById(unitIds).size() != new HashSet<>(unitIds).size();
    }
}
