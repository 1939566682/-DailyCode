package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author Yang QingBo
 * {@code @date} 2026-03-30 18:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Air {
    private Integer id;
    private Integer districtId;
    private Date monitorTime;
    private Integer pm10;
    private Integer pm25;
    private String monitoringStation;
    private Date monitoringTime;
}
