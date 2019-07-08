package com.qijianguo.ad.service.impl;

import com.qijianguo.ad.constant.Contants;
import com.qijianguo.ad.dao.AdPlanRepository;
import com.qijianguo.ad.dao.AdUnitRepository;
import com.qijianguo.ad.dao.CreativeRepository;
import com.qijianguo.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.qijianguo.ad.dao.unit_condition.AdUnitItRepository;
import com.qijianguo.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.qijianguo.ad.dao.unit_condition.CreativeUnitRepository;
import com.qijianguo.ad.entity.AdPlan;
import com.qijianguo.ad.entity.AdUnit;
import com.qijianguo.ad.entity.unit_condition.AdUnitDistrict;
import com.qijianguo.ad.entity.unit_condition.AdUnitIt;
import com.qijianguo.ad.entity.unit_condition.AdUnitKeyword;
import com.qijianguo.ad.entity.unit_condition.CreativeUnit;
import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.service.IAdUnitService;
import com.qijianguo.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdUnitServiceImpl implements IAdUnitService{

    private final AdPlanRepository adPlanRepository;

    private final AdUnitRepository adUnitRepository;

    private final AdUnitKeywordRepository adUnitKeywordRepository;

    private final AdUnitItRepository adUnitItRepository;

    private final AdUnitDistrictRepository adUnitDistrictRepository;

    private final CreativeRepository creativeRepository;

    private final CreativeUnitRepository creativeUnitRepository;

    @Autowired
    public AdUnitServiceImpl(AdPlanRepository adPlanRepository, AdUnitRepository adUnitRepository, AdUnitKeywordRepository adUnitKeywordRepository, AdUnitItRepository adUnitItRepository, AdUnitDistrictRepository adUnitDistrictRepository, CreativeRepository creativeRepository, CreativeUnitRepository creativeUnitRepository) {
        this.adPlanRepository = adPlanRepository;
        this.adUnitRepository = adUnitRepository;
        this.adUnitKeywordRepository = adUnitKeywordRepository;
        this.adUnitItRepository = adUnitItRepository;
        this.adUnitDistrictRepository = adUnitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
    }

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

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {

        // 校验unit是否存在
        List<Long> unitIds = request.getCreativeUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem :: getUnitId)
                .collect(Collectors.toList());
        if (isRelatedUnitExist(unitIds)) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 校验creative是否存在
        List<Long> creativeIds = request.getCreativeUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem :: getCreativeId)
                .collect(Collectors.toList());
        if (isRelatedCreativeExist(creativeIds)) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<CreativeUnit> creativeUnits = Collections.emptyList();
        request.getCreativeUnitItems().forEach(item -> creativeUnits.add(new CreativeUnit(item.getCreativeId(), item.getUnitId())));
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream().map(CreativeUnit::getId).collect(Collectors.toList());

        return new CreativeUnitResponse(ids);
    }

    private boolean isRelatedUnitExist(List<Long> unitIds) {
        return CollectionUtils.isEmpty(unitIds) || adUnitRepository.findAllById(unitIds).size() != new HashSet<>(unitIds).size();
    }

    private boolean isRelatedCreativeExist(List<Long> unitIds) {
        return CollectionUtils.isEmpty(unitIds) || creativeRepository.findAllById(unitIds).size() != new HashSet<>(unitIds).size();
    }
}
