package org.example.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectData {
    @JsonProperty("year")
    private int year;

    @JsonProperty("price")
    private double price;

    @JsonProperty("CPU model")
    private String cpuModel;

    @JsonProperty("Hard disk size")
    private String hardDiskSize;

    public int getYear() {
        return year;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public double getPrice() {
        return price;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }
}
