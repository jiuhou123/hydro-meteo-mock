package com.example.hydrometeomock.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "通用网格数据响应实体")
public class GridDataResponse {
    @Schema(description = "开始经度")
    private double startLon;
    @Schema(description = "结束经度")
    private double endLon;
    @Schema(description = "开始纬度")
    private double startLat;
    @Schema(description = "结束纬度")
    private double endLat;
    @Schema(description = "径向格距")
    private double lonGrid;
    @Schema(description = "纬向格距")
    private double latGrid;
    @Schema(description = "径向网格总数")
    private int lonTotal;
    @Schema(description = "纬向网格总数")
    private int latTotal;
    @Schema(description = "数据维度")
    private int rank;
    @Schema(description = "维度信息列表")
    private List<Dimension> dimensions;
    @Schema(description = "每个维度的shape")
    private List<Integer> shape;
    @Schema(description = "多维数据数组或对象（如s和d）")
    private Object data;

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