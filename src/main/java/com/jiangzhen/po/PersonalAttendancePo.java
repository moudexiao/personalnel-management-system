package com.jiangzhen.po;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalAttendancePo {

    private Long id;

    private Long personalId;

    private String personalName;

    private String departmentName;

    private String positionName;

    private Date attendanceTime;

    private Date punchIn;

    private Date clockOut;
}
