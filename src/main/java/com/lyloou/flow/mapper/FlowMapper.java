package com.lyloou.flow.mapper;

import com.lyloou.flow.model.flow.Flow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lyloou
 * @date 2019/12/31
 */
public interface FlowMapper {
    /**
     * 获取 flow
     *
     * @param id 主ID
     * @return flow实例
     */
    @Select("select * from flow where id=#{id}")
    Flow getFlow(@Param("id") long id);

    /**
     * 获取 flow 列表
     *
     * @return flow实例列表
     */
    List<Flow> listFlow();
}
