package com.ad.admain.controller.pay;

import com.ad.admain.controller.pay.to.BillInfo;
import com.ad.admain.controller.pay.to.Order;
import com.wezhyn.project.BaseService;

import java.util.Optional;

/**
 * @author wezhyn
 * @since 12.01.2019
 */
public interface OrderInfoService extends BaseService<BillInfo, Integer> {


    /**
     * 创建订单信息
     *
     * @param order order
     * @return orderInfo
     */
    Optional<BillInfo> createOrder(Order order);
}