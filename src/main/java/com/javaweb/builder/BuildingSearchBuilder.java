package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;
public class BuildingSearchBuilder {
    private String name;
    private Long floorArea;
    private String ward;
    private String street;
    private String district;
    private Long numberOfBasement;
    private List<String> typeCode;
    private String managerName;
    private String managerPhone;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private Long rentAreaFrom;
    private Long rentAreaTo;
    private Long staffId;
    private Long level;
    private String direction;
    private BuildingSearchBuilder(BuildingBuilder builder) {
        this.name = builder.name;
        this.floorArea = builder.floorArea;
        this.ward = builder.ward;
        this.street = builder.street;
        this.district = builder.district;
        this.numberOfBasement = builder.numberOfBasement;
        this.typeCode = builder.typeCode;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.rentAreaFrom = builder.rentAreaFrom;
        this.rentAreaTo = builder.rentAreaTo;
        this.staffId = builder.staffId;
        this.level = builder.level;
        this.direction = builder.direction;
    }
    public String getName() {
        return name;
    }

    public Long getFloorArea() {
        return floorArea;
    }


    public String getWard() {
        return ward;
    }


    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }


    public Long getNumberOfBasement() {
        return numberOfBasement;
    }


    public List<String> getTypeCode() {
        return typeCode;
    }


    public String getManagerName() {
        return managerName;
    }


    public String getmanagerPhone() {
        return managerPhone;
    }


    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }


    public Long getRentPriceTo() {
        return rentPriceTo;
    }


    public Long getRentAreaFrom() {
        return rentAreaFrom;
    }


    public Long getRentAreaTo() {
        return rentAreaTo;
    }


    public Long getStaffId() {
        return staffId;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public Long getLevel() {
        return level;
    }

    public String getDirection() {
        return direction;
    }

    public static class BuildingBuilder{
        private String name;
        private Long floorArea;
        private String ward;
        private String street;
        private String district;
        private Long numberOfBasement;
        private List<String> typeCode = new ArrayList<>();
        private String managerName;
        private String managerPhone;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private Long rentAreaFrom;
        private Long rentAreaTo;
        private Long staffId;
        private Long level;
        private String direction;
        public BuildingBuilder setName (String name) {
            this.name = name;
            return this;
        }
        public BuildingBuilder setFloorArea (Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }
        public BuildingBuilder setWard (String ward) {
            this.ward = ward;
            return this;
        }
        public BuildingBuilder setStreet (String street) {
            this.street = street;
            return this;
        }
        public BuildingBuilder setDistrict (String district) {
            this.district = district;
            return this;
        }
        public BuildingBuilder setNumberOfBasement (Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }
        public BuildingBuilder setTypeCode (List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }
        public BuildingBuilder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }
        public BuildingBuilder setManagerPhone (String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }
        public BuildingBuilder setRentPriceFrom (Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }
        public BuildingBuilder setRentPriceTo (Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }
        public BuildingBuilder setRentAreaFrom (Long rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }
        public BuildingBuilder setRentAreaTo (Long rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }
        public BuildingBuilder setStaffId (Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public BuildingBuilder setLevel(Long level) {
            this.level = level;
            return this;
        }

        public BuildingBuilder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }

    }
}
