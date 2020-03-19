package com.lyloou.flow.model.flow;

import lombok.Builder;
import lombok.Data;


/**
 * @author lyloou
 */
@Data
@Builder
public class FlowReq {
    private String day;
    private Long userId;
    private String item;
    private String weather;
    private String memo;
    private boolean isArchived;
    private boolean isDisabled;
}