package com.lyloou.flow.mapper;

import com.lyloou.flow.model.flow.Flow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author lyloou
 * @date 2019/12/31
 */
public interface FlowMapper {
    /**
     * 获取 flow
     *
     * @param userId 用户ID
     * @param day    日期
     * @return flow实例
     */
    @Select("select * from flow where user_id=#{userId} and day=#{day}")
    Flow getFlow(@Param("userId") Long userId, @Param("day") String day);

    /**
     * 获取 flow 列表
     *
     * @param userId 用户ID
     * @param limit  限制数量
     * @param offset 偏移
     * @return flow实例列表
     */
    @Select("select * from flow where user_id=#{userId} order by day desc limit #{limit} offset #{offset}")
    List<Flow> listFlow(@Param("userId") Long userId, @Param("limit") int limit, @Param("offset") int offset);

    /**
     * 同步；不存在，则插入；存在，则更新；
     *
     * @param flow flow实例
     * @return 影响的行数
     */
    @Update("insert into flow (user_id,day,item,weather,memo,is_disabled,is_archived) " +
            " values (#{userId},#{day},#{item},#{weather},#{memo},#{isDisabled},#{isArchived}) on duplicate key update" +
            " item=values(item),weather=values(weather),memo=values(memo),is_disabled=values(is_disabled),is_archived=values(is_archived)")
    int syncFlow(Flow flow);


    /**
     * 批量同步；不存在，则插入；存在，则更新；
     *
     * @param flows flow列表
     * @return 影响的行数
     */
    @Update("<script>" +
            "insert into flow (user_id,day,item,weather,memo,is_disabled,is_archived) values " +
            "<foreach collection='flows' item='item' separator=','> " +
            "(#{item.userId},#{item.day},#{item.item},#{item.weather},#{item.memo},#{item.isDisabled},#{item.isArchived}) " +
            "</foreach> " +
            "on duplicate key update " +
            "item=values(item),weather=values(weather),memo=values(memo),is_disabled=values(is_disabled),is_archived=values(is_archived)" +
            "</script>")
    int batchSyncFlow(@Param("flows") List<Flow> flows);

}
