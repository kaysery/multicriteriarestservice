package com.kams.multiplecriteria.repositories.specifications;


public class SearchCriteria {
    private String key;
    private String operation;
    private String predicate;
    private Object value;

    public SearchCriteria(){}

    public SearchCriteria(String key, String operation,Object value,String predicate) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.predicate = predicate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isOrPredicate(){
        return getPredicate().equalsIgnoreCase("or");
    }
}
