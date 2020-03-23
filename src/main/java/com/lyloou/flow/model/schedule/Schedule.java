package com.lyloou.flow.model.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lyloou
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Long id;

    private Long userId;

    private Date gmtCreate;

    private Date gmtModified;

    private String title;

    private String content;

    private boolean isDisabled;
}