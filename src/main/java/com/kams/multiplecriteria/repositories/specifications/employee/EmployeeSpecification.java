package com.kams.multiplecriteria.repositories.specifications.employee;

import com.kams.multiplecriteria.entities.Employee;
import com.kams.multiplecriteria.repositories.specifications.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeSpecification implements Specification<Employee> {

    private SearchCriteria criteria;

    public EmployeeSpecification(SearchCriteria searchCriteria) {
        criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {


        //separating nested properties queries like "enterprise.name"
        String[] nestedQuery = criteria.getKey().split("_");
        Path predicate = root.get(nestedQuery[0]);

        if(predicate.getJavaType() == Date.class){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date newDate = simpleDateFormat.parse((String) criteria.getValue());
                criteria.setValue(newDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i < nestedQuery.length; i++) {
            predicate = predicate.get(nestedQuery[i]);
        }

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            if(predicate.getJavaType() == Date.class){
                return criteriaBuilder.greaterThan(predicate,(Date)criteria.getValue());
            }
            return criteriaBuilder.greaterThan(predicate, criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            if(predicate.getJavaType() == Date.class){
                return criteriaBuilder.greaterThanOrEqualTo(predicate,(Date)criteria.getValue());
            }
            return criteriaBuilder.greaterThanOrEqualTo(predicate, criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            if(predicate.getJavaType() == Date.class){
                return criteriaBuilder.lessThan(predicate,(Date)criteria.getValue());
            }
            return criteriaBuilder.lessThan(predicate, criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            if(predicate.getJavaType() == Date.class){
                return criteriaBuilder.lessThanOrEqualTo(predicate,(Date)criteria.getValue());
            }
            return criteriaBuilder.lessThanOrEqualTo(predicate, criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (predicate.getJavaType() == String.class) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(predicate), "%" + criteria.getValue().toString().toLowerCase() + "%");
            } else {
                return criteriaBuilder.equal(predicate, criteria.getValue());
            }
        }
        return null;
    }
}
