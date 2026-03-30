package org.example.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:59
 */

@Data
@ToString
public class AirVO {
    private Integer id;
    private Integer districtId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date monitorTime;

    private Integer pm10;
    private Integer pm25;

    private String monitoringStation;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastModifyTime;

    private String districtName;
}