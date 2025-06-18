package com.example.hydrometeomock.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TideData {
    @Schema(description = "唯一标识")
    private String id;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "基准线")
    private int base;

    @Schema(description = "潮高")
    private int height;

    @Schema(description = "经度")
    private double lon;

    @Schema(description = "纬度")
    private double lat;

    @Schema(description = "数据时间")
    private String dataTime;
}