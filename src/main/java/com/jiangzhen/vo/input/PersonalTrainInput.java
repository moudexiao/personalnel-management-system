package com.jiangzhen.vo.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PersonalTrainInput {
    @NotNull(message = "用户必填")
    private Long personalId;
    @NotNull(message = "时间必填")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date beginDate;
    @NotNull(message = "时间必填")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date endDate;
    private String trainContent;
    private BigDecimal trainScore;
    private BigDecimal trainCost;
    private String remake;
}
