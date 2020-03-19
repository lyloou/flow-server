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
@AllArgsConstructor
@NoArgsConstructor
public class Flow {
    private Long id;

    private Long userId;

    private Date gmtCreate;

    private Date gmtModified;

    private String day;

    private String item;

    private String weather;

    private String memo;

    private Boolean isArchived;

    private Boolean isDisabled;

}