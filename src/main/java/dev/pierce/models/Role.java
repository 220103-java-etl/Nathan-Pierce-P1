package dev.pierce.models;

public enum Role {

    EMPLOYEE {
        @Override
        public String toString(){
            return "Employee";
        }
    },

    FINANCE_MANAGER {
        @Override
        public String toString(){
            return "Finance Manager";
        }
    }
}
