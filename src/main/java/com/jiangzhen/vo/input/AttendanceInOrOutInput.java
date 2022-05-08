package com.jiangzhen.vo.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AttendanceInOrOutInput {
    @NotNull(message = "时间必填")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date attendanceTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date punchIn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date clockOut;
}
