package com.ad.admain.controller.equipment.impl;

import com.ad.admain.controller.equipment.EquipmentRepository;
import com.ad.admain.controller.equipment.EquipmentService;
import com.ad.admain.controller.equipment.entity.Equipment;
import com.wezhyn.project.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author wezhyn
 * @date 2019/11/04
 * <p>
 * Copyright (c) 2018-2019 All Rights Reserved.
 */
@Service
public class EquipmentServiceImpl extends AbstractBaseService<Equipment, Integer> implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;


    @Override
    public Page<Equipment> getListByUid(Integer uid, Pageable pageable) {
        Equipment equipment=Equipment.createFromUid(uid);
        Example<Equipment> queryCondition=Example.of(equipment);
        return equipmentRepository.findAll(queryCondition, pageable);
    }

    @Override
    public EquipmentRepository getRepository() {
        return equipmentRepository;
    }

}
