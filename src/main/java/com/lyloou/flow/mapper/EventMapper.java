package com.lyloou.flow.mapper;

import com.lyloou.flow.model.flow.Event;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lyloou
 * @date 2019/12/31
 */
public interface EventMapper {

    /**
     * 获取 flow 列表
     *
     * @return flow实例列表
     */
    @Select("select * from event")
    List<Event> listEvent();
}
