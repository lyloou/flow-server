package com.lyloou.flow.model.flow;

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
public class Event {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String day;

    private String content;
}