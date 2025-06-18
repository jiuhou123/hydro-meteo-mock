package com.example.hydrometeomock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.hydrometeomock.model.AddressResponse;
import javax.servlet.http.HttpServletRequest;
import com.example.hydrometeomock.model.TideData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.ArrayList;
import com.example.hydrometeomock.model.CommonRequest;
import com.example.hydrometeomock.model.GridDataResponse;
import com.example.hydrometeomock.model.HorizontalStreamResponse;

@RestController
@Tag(name = "基础接口", description = "基础接口示例")
public class BaseController {
    // 基础控制器类

    // @GetMapping("/health")
    // @Operation(summary = "健康检查", description = "用于检查服务是否正常运行")
    // public String health() {
    // return "Service is running!";
    // }

    @GetMapping("/inner/clientAddressing/interfaceContract")
    @Operation(summary = "寻址接口", description = "根据s.weather-base.gy参数返回本机IP和端口信息")
    public AddressResponse address(@RequestParam("s.weather-base.gy") String serviceName, HttpServletRequest request) {
        AddressResponse resp = new AddressResponse();
        // 第一个真实数据
        AddressResponse.DataItem dataItem1 = new AddressResponse.DataItem();
        try {
            String ip = java.net.InetAddress.getLocalHost().getHostAddress();
            dataItem1.setIp(ip);
        } catch (Exception e) {
            dataItem1.setIp("");
        }
        AddressResponse.PortInfo portInfo1 = new AddressResponse.PortInfo();
        portInfo1.setPort(11096);
        AddressResponse.PortInfo portInfo1Fake = new AddressResponse.PortInfo();
        portInfo1Fake.setPort(12345);
        dataItem1.setPortInfoList(java.util.Arrays.asList(portInfo1, portInfo1Fake));

        // 第二个假的数据
        AddressResponse.DataItem dataItem2 = new AddressResponse.DataItem();
        dataItem2.setIp("127.0.0.1");
        AddressResponse.PortInfo portInfo2 = new AddressResponse.PortInfo();
        portInfo2.setPort(8888);
        AddressResponse.PortInfo portInfo2Fake = new AddressResponse.PortInfo();
        portInfo2Fake.setPort(9999);
        dataItem2.setPortInfoList(java.util.Arrays.asList(portInfo2, portInfo2Fake));

        resp.setDatas(java.util.Arrays.asList(dataItem1, dataItem2));
        resp.setRespCode(0);
        resp.setRespMsg("寻址成功.");
        return resp;
    }

    @PostMapping("/weather/sea/tide")
    @Operation(summary = "潮汐数据", description = "根据请求参数返回潮汐数据列表")
    public List<TideData> getTideData(@RequestBody CommonRequest request) {
        List<TideData> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            TideData data = new TideData();
            data.setId("id" + i);
            data.setProvince("台湾");
            data.setCity("马关" + i);
            data.setBase(150 + i);
            data.setHeight(250 + i * 2);
            data.setLon(12.0 + i);
            data.setLat(120 + i);
            data.setDataTime("2025-03-31 " + String.format("%02d:00:00", 4 + i));
            list.add(data);
        }
        return list;
    }

    @PostMapping("/weather/sea/waveDirection")
    @Operation(summary = "获取波向数据", description = "返回波向网格数据，响应结构为通用网格数据格式")
    public GridDataResponse getWaveDirection(@RequestBody CommonRequest request) {
        GridDataResponse resp = new GridDataResponse();
        resp.setStartLon(110);
        resp.setEndLon(120);
        resp.setStartLat(22);
        resp.setEndLat(24);
        resp.setLonGrid(0.199);
        resp.setLatGrid(0.2);
        resp.setLonTotal(11);
        resp.setLatTotal(9);
        resp.setRank(3);
        // 维度信息
        GridDataResponse.Dimension dim = new GridDataResponse.Dimension();
        dim.setDimName("time");
        dim.setValues(java.util.Collections.singletonList("2023-03-01 00:00"));
        dim.setUnitsStr("");
        resp.setDimensions(java.util.Collections.singletonList(dim));
        resp.setShape(java.util.Arrays.asList(1, 9, 11));
        // 构造三维数据 [1, 9, 11]，波向角度值120~250
        java.util.List<java.util.List<java.util.List<Double>>> data = new java.util.ArrayList<>();
        for (int t = 0; t < 1; t++) {
            java.util.List<java.util.List<Double>> latList = new java.util.ArrayList<>();
            for (int lat = 0; lat < 9; lat++) {
                java.util.List<Double> lonList = new java.util.ArrayList<>();
                for (int lon = 0; lon < 11; lon++) {
                    // 生成120~250之间的角度值
                    double value = 120 + (lat * 11 + lon) * (130.0 / 98); // 98=9*11-1
                    lonList.add(Math.round(value * 10.0) / 10.0); // 保留1位小数
                }
                latList.add(lonList);
            }
            data.add(latList);
        }
        resp.setData((java.util.List) data);
        return resp;
    }

    @PostMapping("/weather/sea/waveHeight")
    @Operation(summary = "获取有效波高数据", description = "返回有效波高网格数据，响应结构为通用网格数据格式")
    public GridDataResponse getWaveHeight(@RequestBody CommonRequest request) {
        GridDataResponse resp = new GridDataResponse();
        resp.setStartLon(110);
        resp.setEndLon(120);
        resp.setStartLat(22);
        resp.setEndLat(24);
        resp.setLonGrid(0.199);
        resp.setLatGrid(0.2);
        resp.setLonTotal(11);
        resp.setLatTotal(9);
        resp.setRank(3);
        // 维度信息
        GridDataResponse.Dimension dim = new GridDataResponse.Dimension();
        dim.setDimName("time");
        dim.setValues(java.util.Collections.singletonList("2023-03-01 00:00"));
        dim.setUnitsStr("");
        resp.setDimensions(java.util.Collections.singletonList(dim));
        resp.setShape(java.util.Arrays.asList(1, 9, 11));
        // 构造三维数据 [1, 9, 11]，波高值与波向不同
        java.util.List<java.util.List<java.util.List<Double>>> data = new java.util.ArrayList<>();
        for (int t = 0; t < 1; t++) {
            java.util.List<java.util.List<Double>> latList = new java.util.ArrayList<>();
            for (int lat = 0; lat < 9; lat++) {
                java.util.List<Double> lonList = new java.util.ArrayList<>();
                for (int lon = 0; lon < 11; lon++) {
                    lonList.add(2.0 + lat * 0.02 + lon * 0.02); // 固定波高值
                }
                latList.add(lonList);
            }
            data.add(latList);
        }
        resp.setData((java.util.List) data);
        return resp;
    }

    @PostMapping("/weather/sea/horizontalStream")
    @Operation(summary = "获取水平流数据", description = "返回水平流网格数据，data包含s(速度)和d(方向)两个四维数组")
    public GridDataResponse getHorizontalStream(@RequestBody CommonRequest request) {
        GridDataResponse resp = new GridDataResponse();
        resp.setStartLon(110);
        resp.setEndLon(120);
        resp.setStartLat(22);
        resp.setEndLat(24);
        resp.setLonGrid(0.199);
        resp.setLatGrid(0.2);
        resp.setLonTotal(26);
        resp.setLatTotal(23);
        resp.setRank(4);
        // 维度信息
        GridDataResponse.Dimension dimTime = new GridDataResponse.Dimension();
        dimTime.setDimName("time");
        dimTime.setValues(java.util.Collections.singletonList("2023-03-01 00:00"));
        dimTime.setUnitsStr("");
        GridDataResponse.Dimension dimDepth = new GridDataResponse.Dimension();
        dimDepth.setDimName("depth");
        java.util.List<Integer> depthValues = new java.util.ArrayList<>();
        for (int i = 0; i < 50; i++) {
            depthValues.add(i * 2); // 0, 2, 4, ..., 98
        }
        java.util.List<Object> depthObjValues = new java.util.ArrayList<>(depthValues);
        dimDepth.setValues(depthObjValues);
        dimDepth.setUnitsStr("m");
        resp.setDimensions(java.util.Arrays.asList(dimTime, dimDepth));
        resp.setShape(java.util.Arrays.asList(1, 50, 23, 26));
        // 构造s和d两个四维数组，数据量大，这里只填充部分示例
        int tLen = 1, dLen = 50, latLen = 23, lonLen = 26;
        double[][][][] s = new double[tLen][dLen][latLen][lonLen];
        double[][][][] d = new double[tLen][dLen][latLen][lonLen];
        for (int t = 0; t < tLen; t++) {
            for (int dep = 0; dep < dLen; dep++) {
                for (int la = 0; la < latLen; la++) {
                    for (int lo = 0; lo < lonLen; lo++) {
                        s[t][dep][la][lo] = 0.5 + (dep % 5) * 0.1 + (la % 3) * 0.05 + (lo % 4) * 0.02;
                        d[t][dep][la][lo] = 90 + (dep % 10) * 2 + (la % 5) * 1.5 + (lo % 6) * 3;
                    }
                }
            }
        }
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("s", s);
        data.put("d", d);
        resp.setData(data);
        return resp;
    }

    @PostMapping("/weather/sea/verticalStream")
    @Operation(summary = "获取垂直流数据", description = "返回垂直流网格数据，结构与水平流一致")
    public GridDataResponse getVerticalStream(@RequestBody CommonRequest request) {
        // 复用水平流的实现逻辑
        GridDataResponse resp = new GridDataResponse();
        resp.setStartLon(110);
        resp.setEndLon(120);
        resp.setStartLat(22);
        resp.setEndLat(24);
        resp.setLonGrid(0.199);
        resp.setLatGrid(0.2);
        resp.setLonTotal(26);
        resp.setLatTotal(23);
        resp.setRank(4);
        // 维度信息
        GridDataResponse.Dimension dimTime = new GridDataResponse.Dimension();
        dimTime.setDimName("time");
        dimTime.setValues(java.util.Collections.singletonList("2023-03-01 00:00"));
        dimTime.setUnitsStr("");
        GridDataResponse.Dimension dimDepth = new GridDataResponse.Dimension();
        dimDepth.setDimName("depth");
        java.util.List<Integer> depthValues = new java.util.ArrayList<>();
        for (int i = 0; i < 50; i++) {
            depthValues.add(i * 2); // 0, 2, 4, ..., 98
        }
        java.util.List<Object> depthObjValues = new java.util.ArrayList<>(depthValues);
        dimDepth.setValues(depthObjValues);
        dimDepth.setUnitsStr("m");
        resp.setDimensions(java.util.Arrays.asList(dimTime, dimDepth));
        resp.setShape(java.util.Arrays.asList(1, 50, 23, 26));
        // 构造s和d两个四维数组，数据量大，这里只填充部分示例
        int tLen = 1, dLen = 50, latLen = 23, lonLen = 26;
        double[][][][] s = new double[tLen][dLen][latLen][lonLen];
        double[][][][] d = new double[tLen][dLen][latLen][lonLen];
        for (int t = 0; t < tLen; t++) {
            for (int dep = 0; dep < dLen; dep++) {
                for (int la = 0; la < latLen; la++) {
                    for (int lo = 0; lo < lonLen; lo++) {
                        s[t][dep][la][lo] = 0.5 + (dep % 5) * 0.1 + (la % 3) * 0.05 + (lo % 4) * 0.02;
                        d[t][dep][la][lo] = 90 + (dep % 10) * 2 + (la % 5) * 1.5 + (lo % 6) * 3;
                    }
                }
            }
        }
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("s", s);
        data.put("d", d);
        resp.setData(data);
        return resp;
    }

    @PostMapping("/weather/weather/surfaceWind")
    @Operation(summary = "获取地表风速/风向", description = "返回地表风速/风向网格数据，data包含s(风速)和d(风向)两个三维数组")
    public GridDataResponse getSurfaceWind(@RequestBody CommonRequest request) {
        GridDataResponse resp = new GridDataResponse();
        resp.setStartLon(110);
        resp.setEndLon(120);
        resp.setStartLat(22);
        resp.setEndLat(24);
        resp.setLonGrid(0.6);
        resp.setLatGrid(0.15);
        resp.setLonTotal(17);
        resp.setLatTotal(15);
        resp.setRank(3);
        // 维度信息
        GridDataResponse.Dimension dimTime = new GridDataResponse.Dimension();
        dimTime.setDimName("time");
        dimTime.setValues(java.util.Collections.singletonList("2023-03-01 00:00"));
        dimTime.setUnitsStr("");
        resp.setDimensions(java.util.Collections.singletonList(dimTime));
        resp.setShape(java.util.Arrays.asList(1, 15, 17));
        // 构造s和d两个三维数组
        int tLen = 1, latLen = 15, lonLen = 17;
        double[][][] s = new double[tLen][latLen][lonLen];
        double[][][] d = new double[tLen][latLen][lonLen];
        for (int t = 0; t < tLen; t++) {
            for (int la = 0; la < latLen; la++) {
                for (int lo = 0; lo < lonLen; lo++) {
                    s[t][la][lo] = 2.0 + (la % 5) * 0.3 + (lo % 4) * 0.2; // 风速2~4m/s
                    d[t][la][lo] = 180 + (la % 8) * 10 + (lo % 6) * 5; // 风向180~270度
                }
            }
        }
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("s", s);
        data.put("d", d);
        resp.setData(data);
        return resp;
    }

    @PostMapping("/weather/weather/levelWind")
    @Operation(summary = "获取带高度层的风速/风向", description = "返回带高度层的风速/风向网格数据，data包含s(风速)和d(风向)两个四维数组")
    public GridDataResponse getLevelWind(@RequestBody CommonRequest request) {
        GridDataResponse resp = new GridDataResponse();
        resp.setStartLon(110);
        resp.setEndLon(115.2);
        resp.setStartLat(22);
        resp.setEndLat(22.9);
        resp.setLonGrid(0.6);
        resp.setLatGrid(0.15);
        resp.setLonTotal(9);
        resp.setLatTotal(7);
        resp.setRank(4);
        // 维度信息
        GridDataResponse.Dimension dimTime = new GridDataResponse.Dimension();
        dimTime.setDimName("time");
        dimTime.setValues(java.util.Collections.singletonList("2023-03-01 00:00"));
        dimTime.setUnitsStr("");
        GridDataResponse.Dimension dimIsobaric = new GridDataResponse.Dimension();
        dimIsobaric.setDimName("isobaric");
        java.util.List<Integer> isobaricValues = new java.util.ArrayList<>();
        for (int i = 1; i <= 19; i++) {
            isobaricValues.add(i * 10); // 10, 20, ..., 190
        }
        dimIsobaric.setValues(new java.util.ArrayList<>(isobaricValues));
        dimIsobaric.setUnitsStr("hpa");
        resp.setDimensions(java.util.Arrays.asList(dimTime, dimIsobaric));
        resp.setShape(java.util.Arrays.asList(1, 19, 7, 9));
        // 构造s和d两个四维数组
        int tLen = 1, isoLen = 19, latLen = 7, lonLen = 9;
        double[][][][] s = new double[tLen][isoLen][latLen][lonLen];
        double[][][][] d = new double[tLen][isoLen][latLen][lonLen];
        for (int t = 0; t < tLen; t++) {
            for (int iso = 0; iso < isoLen; iso++) {
                for (int la = 0; la < latLen; la++) {
                    for (int lo = 0; lo < lonLen; lo++) {
                        s[t][iso][la][lo] = 3.0 + (iso % 5) * 0.2 + (la % 3) * 0.1 + (lo % 4) * 0.05; // 风速
                        d[t][iso][la][lo] = 200 + (iso % 8) * 8 + (la % 5) * 2 + (lo % 6) * 3; // 风向
                    }
                }
            }
        }
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("s", s);
        data.put("d", d);
        resp.setData(data);
        return resp;
    }
}