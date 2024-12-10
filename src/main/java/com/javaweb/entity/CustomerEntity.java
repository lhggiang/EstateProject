package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="fullname")
    private String fullName;
    @Column(name="phone")
    private String phoneNumber;
    @Column(name="email")
    private String email;
    @Column(name="companyname")
    private String companyName;
    @Column(name="demand")
    private String demand;
    @Column(name="status")
    private String status;

    @Column(name="is_active")
    private Integer isActive;

    @OneToMany (mappedBy = "customer", fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    List<TransactionEntity> transaction = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> assignmentCustomers = new ArrayList<>();

    public List<UserEntity> getAssignmentCustomers() {
        return assignmentCustomers;
    }

    public void setAssignmentCustomers(List<UserEntity> assignmentCustomers) {
        this.assignmentCustomers = assignmentCustomers;
    }

    public List<TransactionEntity> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionEntity> transaction) {
        this.transaction = transaction;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
