package com.ad.admain.controller;

import com.ad.admain.controller.impl.ImgBed;
import com.ad.admain.controller.impl.ImgBedType;
import com.wezhyn.project.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author wezhyn
 * @date 2019/09/29
 * <p>
 * Copyright (c) 2018-2019 All Rights Reserved.
 */
public interface ImgBedService extends BaseService<ImgBed, Integer> {


    /**
     * 获取图床list
     *
     * @param imgBedType type
     * @param pageable   分页
     * @return list
     */
    Page<ImgBed> getImgBedListByType(ImgBedType imgBedType, Pageable pageable);


}
