package com.chang.java3;

import com.chang.java2.Employee;
import com.chang.java2.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream的终止操作
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-06 15:40
 */
public class StreamAPITest2 {
    //1. 匹配与查找
    @Test
    public void test1(){
        //allMatch(Predicate p)--检查是否匹配所有元素。
        // 练习，是否所有的员工年龄都大于18
        List<Employee> employees = EmployeeData.getEmployees();
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

        //anyMatch(Predicate p)--检查是否至少匹配一个元素
        //练习：是否存在员工的工资大于10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);

        //noneMatch(Predicate p)--检查是否没有匹配的元素
        //练习，是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);

        //findFirst--返回第一个元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);

        //findAny--返回任意一个元素
        Optional<Employee> employee1 = employees.parallelStream().findAny();
        System.out.println(employee1);
    }

    @Test
    public void test2(){
        List<Employee> employees = EmployeeData.getEmployees();

        //cout--返回流中元素的个数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);

//        max(Comparator c) --返回流中的最大值
//        练习：返回最高的工资
        Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
        Optional<Double> maxSalary = salaryStream.max(Double::compare);
        System.out.println(maxSalary);

        //min(Comparator c) --返回流中的最小值
        //练习：返回最低员工的工资
        Optional<Employee> employee = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(employee);
        System.out.println();

        //forEach(Consumer c)-- 内部迭代
        employees.stream().forEach(System.out::println);

        employees.forEach(System.out::println);

    }

    //3. 规约
    @Test
    public void test3(){
        //reduce(T identity, BinaryOperator)可以将流中元素反复结合起来，得到一个值，返回T
        //练习1：计算1--10的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, Integer::sum);//0为初始值
        System.out.println(sum);

        //reduce(BinaryOperator) --可以将流中元素反复结合起来，得到一个值。返回Optional<T>
//            练习2：计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Optional<Double> sumMoney = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(sumMoney);

    }

    //4.收集
    @Test
    public void test4(){
        //collect(Collector c) --将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
        //练习1：查找工资大于6000的员工，结果返回为一个List或Set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);

        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }
}
