package com.lyloou.flow.model.flow;

import com.google.gson.annotations.SerializedName;
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

    private Date gmtCreate;

    private Date gmtModified;

    private String day;

    private String item;

    @SerializedName("is_archived")
    private Boolean isArchived;

    @SerializedName("is_disabled")
    private Boolean isDisabled;

}