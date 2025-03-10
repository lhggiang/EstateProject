package com.javaweb.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -863164858986274318L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createddate",updatable = false)
    //@CreatedDate
    private Date createdDate;

    @Column(name = "createdby", updatable = false)
    //@CreatedBy
    private String createdBy;

    @Column(name = "modifieddate")
    //@LastModifiedDate
    private Date modifiedDate;

    @Column(name = "modifiedby")
    //@LastModifiedBy
    private String modifiedBy;
    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @PreUpdate
    protected void onUpdate() {
        modifiedDate = new Date();
        modifiedBy = SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
