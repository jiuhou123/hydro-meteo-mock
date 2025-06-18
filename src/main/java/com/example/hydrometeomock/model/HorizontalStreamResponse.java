package com.example.hydrometeomock.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@Schema(description = "水平流响应实体")
public class HorizontalStreamResponse {
    @Schema(description = "数据维度")
    private int rank;
    @Schema(description = "维度信息列表")
    private List<Dimension> dimensions;
    @Schema(description = "每个维度的shape")
    private List<Integer> shape;
    @Schema(description = "数据对象，包含s(速度)和d(方向)两个四维数组")
    private Map<String, Object> data;

    @Data
    @Schema(description = "维度信息")
    public static class Dimension {
        @Schema(description = "维度名称")
        private String dimName;
        @Schema(description = "维度取值")
        private List<Object> values;
        @Schema(description = "单位")
        private String unitsStr;
    }
}