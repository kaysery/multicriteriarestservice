package com.kams.multiplecriteria.repositories.specifications.employee;

import com.kams.multiplecriteria.entities.Employee;
import com.kams.multiplecriteria.repositories.specifications.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

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

        for (int i = 1; i < nestedQuery.length; i++) {
            predicate = predicate.get(nestedQuery[i]);
        }

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThan(
                    predicate, criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    predicate, criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThan(
                    predicate, criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    predicate, criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (predicate.getJavaType() == String.class) {
                return criteriaBuilder.like(
                        predicate, "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(predicate, criteria.getValue());
            }
        }
        return null;
    }
}
