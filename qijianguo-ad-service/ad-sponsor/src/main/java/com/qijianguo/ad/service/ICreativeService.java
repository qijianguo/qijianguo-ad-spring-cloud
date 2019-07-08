package com.qijianguo.ad.service;

import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.vo.CreativeRequest;
import com.qijianguo.ad.vo.CreativeResponse;

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
