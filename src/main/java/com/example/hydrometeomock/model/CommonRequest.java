package com.example.hydrometeomock.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "通用请求实体", example = "{\n  \"startLon\": 120.1,\n  \"endLon\": 121.5,\n  \"startLat\": 23.5,\n  \"endLat\": 25.0,\n  \"level\": 1,\n  \"startTime\": \"2025-03-31 00:00:00\",\n  \"endTime\": \"2025-03-31 23:59:59\"\n}")
@Data
public class CommonRequest {
    @Schema(description = "起始经度")
    private double startLon;

    @Schema(description = "结束经度")
    private double endLon;

    @Schema(description = "起始纬度")
    private double startLat;

    @Schema(description = "结束纬度")
    private double endLat;

    @Schema(description = "级别")
    private int level;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;
}