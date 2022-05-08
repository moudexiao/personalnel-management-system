package com.jiangzhen.vo.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class LeaveInput {

    @NotNull(message = "起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date punchIn;
    @NotNull(message = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date clockOut;

    /**
     * 1:'事假'2:病假' 3:'婚假' 4:'产假'
     */
    private Integer reason;
    /**
     * 0:待审核 1:通过 2: 拒绝
     */
    private Integer status;
}
