package org.example.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateObjectData {

    @JsonProperty("year")
    private final int year;

    @JsonProperty("price")
    private final double price;

    @JsonProperty("CPU model")
    private final String cpuModel;

    @JsonProperty("Hard disk size")
    private final String hardDiskSize;

    private CreateObjectData(Builder builder) {
        this.year = builder.year;
        this.price = builder.price;
        this.cpuModel = builder.cpuModel;
        this.hardDiskSize = builder.hardDiskSize;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int year;
        private double price;
        private String cpuModel;
        private String hardDiskSize;

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder cpuModel(String cpuModel) {
            this.cpuModel = cpuModel;
            return this;
        }

        public Builder hardDiskSize(String hardDiskSize) {
            this.hardDiskSize = hardDiskSize;
            return this;
        }

        public CreateObjectData build() {
            return new CreateObjectData(this);
        }
    }
}
