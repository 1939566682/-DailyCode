package org.example.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 20:28
 */

@Data
public class AirAddDTO {

    @NotNull(message = "区域ID不能为空")
    private Integer districtId;

    @NotNull(message = "监测时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date monitorTime;

    @NotNull(message = "PM10不能为空")
    @Min(value = 0, message = "PM10不能小于0")
    @Max(value = 1000, message = "PM10不能超过1000")
    private Integer pm10;

    @NotNull(message = "PM2.5不能为空")
    @Min(value = 0, message = "PM2.5不能小于0")
    @Max(value = 1000, message = "PM2.5不能超过1000")
    private Integer pm25;

    @NotBlank(message = "监测站名称不能为空")
    private String monitoringStation;
}