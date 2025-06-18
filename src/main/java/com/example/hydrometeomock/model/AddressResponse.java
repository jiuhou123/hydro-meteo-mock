package com.example.hydrometeomock.model;

import lombok.Data;
import java.util.List;

@Data
public class AddressResponse {
    private List<DataItem> datas;
    private int respCode;
    private String respMsg;

    @Data
    public static class DataItem {
        private String ip;
        private List<PortInfo> portInfoList;
    }

    @Data
    public static class PortInfo {
        private int port;
    }
}