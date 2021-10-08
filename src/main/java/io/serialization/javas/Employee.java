package io.serialization.javas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Employee {
    private final String name;
    private final Boolean married;
    private final int salary;
    private final String[] skills;
    private final Department department;

    public Employee(String name, Boolean married, int salary, String[] skills, Department department) {
        this.name = name;
        this.married = married;
        this.salary = salary;
        this.skills = skills;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", married=" + married
                + ", salary=" + salary
                + ", skills=" + Arrays.toString(skills)
                + ", department=" + department
                + '}';
    }

    public static void main(String[] args) {
        Department department = new Department("IT", 56);
        String[] skills = new String[]{"Communication", "Teamwork", "Problem-solving", "Stress management"};
        Employee employee = new Employee("Ivan", true, 3600, skills, department);
        System.out.println(employee);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(employee));

        final String employ =
                "{"
                        + "\"name\":\"Ivan\","
                        + "\"married\":true,"
                        + "\"salary\":3600,"
                        + "\"skills\":"
                        + "[\"Communication\",\"Teamwork\",\"Problem-solving\",\"Stress management\"],"
                        + "\"department\":"
                        + "{"
                        + "\"title\":\"IT\","
                        + "\"numberOfEmployees\":56"
                        + "},"
                        + "}";

        final Employee employee1 = gson.fromJson(employ, Employee.class);
        System.out.println(employee1);
    }
}

class Department {
    private final String title;
    private final int numberOfEmployees;

    public Department(String title, int numberOfEmployees) {
        this.title = title;
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public String toString() {
        return "Department{"
                + "title='" + title + '\''
                + ", numberOfEmployees=" + numberOfEmployees
                + '}';
    }
}