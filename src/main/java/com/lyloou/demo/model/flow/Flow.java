package com.lyloou.demo.model.flow;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author lyloou
 */
@Data
@Builder
public class Flow {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String day;

    private String item;
}