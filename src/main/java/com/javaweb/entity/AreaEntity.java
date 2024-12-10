package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name="rentarea")
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "value")
    private Integer value;
    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity building;

    public BuildingEntity getBuilding() {
        return building;
    }
    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
}