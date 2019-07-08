package com.qijianguo.ad.service.impl;

import com.qijianguo.ad.dao.CreativeRepository;
import com.qijianguo.ad.entity.Creative;
import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.service.ICreativeService;
import com.qijianguo.ad.vo.CreativeRequest;
import com.qijianguo.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreativeServiceImpl implements ICreativeService {

    @Autowired
    private CreativeRepository creativeRepository;

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        // 校验..

        //

        Creative creative = creativeRepository.save(request.convertToEntity());

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
