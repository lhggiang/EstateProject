package com.javaweb.repository.custom.impl;

import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public void querySqlJoin(CustomerSearchRequest request, StringBuilder sql) {
        Long staffId = request.getStaffId();
        if(staffId!=null) {
            sql.append(" inner join assignmentcustomer on c.id = assignmentcustomer.customerid ");
        }
    }

    public void queryWhere(CustomerSearchRequest request, StringBuilder where) {
        String name = request.getFullName();
        if(StringUtils.check(name)){
            where.append(" and c.fullname like '%" + name + "%'");
        }
        String phoneNumber = request.getPhoneNumber();
        if(StringUtils.check(phoneNumber)){
            where.append(" and c.phone like '%" + phoneNumber + "%'");
        }
        String email = request.getEmail();
        if(StringUtils.check(email)){
            where.append(" and c.email like '%" + email + "%'");
        }
        Long staffId = request.getStaffId();
        if(staffId != null) {
            where.append(" and assignmentcustomer.staffid = " + staffId);
        }
    }
    @Override
    public List<CustomerEntity> findCustomers(CustomerSearchRequest request) {
        StringBuilder sql = new StringBuilder(
                "Select c.id, c.fullname, c.phone, c.email, c.companyname, c.demand, c.status, " +
                        "c.createddate, c.createdby, c.modifieddate, c.modifiedby, c.is_active" +
                        " FROM estateadvance.customer AS c ");
        querySqlJoin(request,sql);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        sql.append(" and c.is_active = 1 ");
        queryWhere(request,sql);
        sql.append(" group by c.id ");
        Query query= entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return ((Query) query).getResultList();
    }
}
