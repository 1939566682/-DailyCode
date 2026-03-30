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
 * {@code @date} 2026-03-30 21:41
 */

@Data
public class AirUpdateDTO {
    @NotNull(message = "空气记录编号ID不能为空")
    private Integer id;

    private Integer districtId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date monitorTime;

    @Min(value = 0, message = "PM10不能小于0")
    @Max(value = 1000, message = "PM10不能超过1000")
    private Integer pm10;

    @Min(value = 0, message = "PM2.5不能小于0")
    @Max(value = 1000, message = "PM2.5不能超过1000")
    private Integer pm25;

    private String monitoringStation;
}
