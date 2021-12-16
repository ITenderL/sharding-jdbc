package com.itender.sharding.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: ITender
 * @CreateTime: 2021-12-16 11:10
 */
@Mapper
@Component
public interface OrderDao {
    /**
     * 插入订单
     *
     * @param price
     * @param userId
     * @param status
     */
    @Insert("insert into t_order(price,user_id,status)values(#{price},#{userId},#{status})")
    void insertOrder(@Param("price") BigDecimal price, @Param("userId") Long userId, @Param("status") String status);

    /**
     * 根据id列表查询订单
     *
     * @param orderIds
     */
    @Select("<script>" +
            "select" +
            " * " +
            " from t_order t " +
            " where t.order_id in " +
            " <foreach collection='orderIds' open='(' separator=',' close=')' item='id'>" +
            " #{id} " +
            " </foreach>" +
            "</script>")
    List<Map> selectOrderbyIds(@Param("orderIds") List<Long> orderIds);
}
