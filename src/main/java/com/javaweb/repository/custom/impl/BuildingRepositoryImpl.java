package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
@PropertySource("classpath:application.properties")
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public void querySqlJoin(BuildingSearchBuilder builder, StringBuilder sql) {
        Long staffId = builder.getStaffId();
        if(staffId!=null) {
            sql.append(" inner join assignmentbuilding on b.id = assignmentbuilding.buildingid ");
        }
    }

    public void querySimple(BuildingSearchBuilder builder, StringBuilder where) {
        //java reflection
        try {
            Field[] field = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item: field) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId")
                        && !fieldName.startsWith("rentPrice") && !fieldName.equals("typeCode")
                        && !fieldName.startsWith("rentArea")) {
                    Object value = item.get(builder);
                    if(value!=null && value!="") {
                        if(item.getType().getName().equals("java.lang.String")){
                            where.append(" and b." + fieldName + " like '%" + value +  "%' ");
                        }
                        else if(item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" and b." + fieldName +  " = " + value);
                        }
                        else if(item.getType().getName().equals("java.lang.Long")) {
                            where.append(" and b." + fieldName +  " = " + value);
                        }
                    }

                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public void querySpecial(BuildingSearchBuilder builder, StringBuilder where) {
        Long rentPriceFrom= builder.getRentPriceFrom();
        Long rentPriceTo = builder.getRentPriceTo();
        Long rentAreaFrom = builder.getRentAreaFrom();
        Long rentAreaTo = builder.getRentAreaTo();
        Long staffId = builder.getStaffId();
        if(rentPriceFrom!=null) {
            where.append(" and b.rentprice >= " + rentPriceFrom);
        }
        if(rentPriceTo!= null) {
            where.append(" and b.rentprice <= " + rentPriceTo);
        }
        if((rentAreaFrom!= null) || (rentAreaFrom!=null)) {
            where.append(" and exists (select * from rentarea r where b.id = r.buildingid");
            if(rentAreaFrom != null) {
                where.append(" and r.value >= " + rentAreaFrom);
            }
            if(rentAreaTo != null) {
                where.append(" and r.value <= " + rentAreaTo);
            }
            where.append(") ");
        }
        if(staffId != null) {
            where.append(" and assignmentbuilding.staffid = " + staffId);
        }
        //Java 8
        List<String> typeCode = builder.getTypeCode();
        if(typeCode != null && typeCode.size()!=0) {
            where.append(" and (");
            String query = typeCode.stream().map(item -> " b.type like '%" + item + "%'").collect(Collectors.joining(" or "));
            where.append(query);
            where.append(") ");
        }
    }
    @Override
    public List<BuildingEntity> findBuildings(BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder(
                "Select b.id, b.name, b.street, b.ward, b.district, b.structure, b.numberofbasement, " +
                        "b.floorarea, b.direction, b.level, b.rentprice, b.rentpricedescription, " +
                        "b.servicefee,b.carfee,b.motofee, b.overtimefee, b.waterfee, b.electricityfee, " +
                        "b.deposit, b.payment, b.renttime, b.decorationtime, b.brokeragefee, b.type, " +
                        "b.note, b.linkofbuilding, b.map, b.avatar, b.createddate, b.modifieddate, " +
                        "b.createdby, b.modifiedby, b.managername, b.managerphone " +
                        "FROM estateadvance2.building AS b ");
        querySqlJoin(builder,sql);
        sql.append(SystemConstant.ONE_EQUAL_ONE);
        querySimple(builder,sql);
        querySpecial(builder,sql);
        sql.append(" group by b.id ");
        Query query= entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return ((Query) query).getResultList();
    }

    @Override
    @Transactional
    public void deleteBuilding(BuildingEntity buildingEntity) {
        entityManager.remove(buildingEntity);
    }

    @Override
    public List<BuildingEntity> getAllBuildings(Pageable pageable) {
        StringBuilder sql = new StringBuilder(buildQueryFilter())
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }
    private String buildQueryFilter() {
        String sql = "SELECT * FROM building b";
        return sql;
    }
}

