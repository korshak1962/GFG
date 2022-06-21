package com.slava.bfs;

import org.junit.Test;

import java.util.*;

//https://leetcode.com/problems/employee-importance/
public class EmployeeImportance {

    @Test
    public void testImp() {
        Employee em3 = new Employee();
        em3.id = 3;
        em3.importance = 3;
        Employee em2 = new Employee();
        em2.id = 2;
        em2.importance = 3;
        Employee em1 = new Employee();
        em1.id = 1;
        em1.importance = 5;
        em1.subordinates = new LinkedList<>();
        em1.subordinates.add(em3.id);
        em1.subordinates.add(em2.id);

        List<Employee> employees = new ArrayList<>();
        employees.add(em1);
        employees.add(em2);
        employees.add(em3);
        int res = getImportance(employees, 1);
        System.out.println(res);
    }

    Map<Integer, Employee> idToEmployee = new HashMap<>();
    Queue<Integer> employeeQ = new LinkedList<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee emp : employees) {
            idToEmployee.put(emp.id, emp);
        }
        return sumImpQ(idToEmployee.get(id));
    }

    int sumImpQ(Employee boss) {
        employeeQ.add(boss.id);
        int forReturn = 0;
        while (!employeeQ.isEmpty()) {
            Employee employee = idToEmployee.get(employeeQ.poll());
            forReturn += employee.importance;
            if (employee.subordinates != null)
                employeeQ.addAll(employee.subordinates);
        }
        return forReturn;
    }

    int sumImp(Employee boss) {
        int forReturn = boss.importance;
        if (boss.subordinates == null || boss.subordinates.isEmpty()) {
            return forReturn;
        } else for (Integer empID : idToEmployee.get(boss.id).subordinates) {
            forReturn += sumImp(idToEmployee.get(empID));
        }
        return forReturn;
    }


    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

}
