package com.lyloou.flow.mapper;

import com.lyloou.flow.model.schedule.Schedule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author lyloou
 * @date 2019/12/31
 */
public interface ScheduleMapper {

    /**
     * 获取 schedule 列表
     *
     * @return schedule 列表
     */
    @Select("select * from schedule where user_id=#{userId} and is_disabled=false order by id desc limit #{limit} offset #{offset}")
    List<Schedule> listSchedule(@Param("userId") Long userId, @Param("limit") int limit, @Param("offset") int offset);

    /**
     * 批量同步；不存在，则插入；存在，则更新；
     *
     * @param schedules 列表
     * @return 影响的行数
     */
    @Update("<script>" +
            "insert into schedule (id,user_id,title,content,a,b,c,d) values " +
            "<foreach collection='schedules' item='item' separator=','> " +
            "(#{item.id},#{item.userId},#{item.title},#{item.content},#{item.a},#{item.b},#{item.c},#{item.d}) " +
            "</foreach> " +
            "on duplicate key update " +
            "title=values(title),content=values(content),a=values(a),b=values(b),c=values(c),d=values(d)" +
            "</script>")
    int batchSyncSchedule(@Param("schedules") List<Schedule> schedules);

    /**
     * 批量删除
     *
     * @param ids ID列表
     * @return 影响的行数
     */
    @Update("<script>" +
            "update schedule set is_disabled=1 where id in ( " +
            "<foreach collection='ids' item='id' separator=',' > " +
            "#{id} " +
            "</foreach> " +
            ")" +
            "</script>")
    int batchDeleteSchedule(@Param("ids") List<Long> ids);
}
