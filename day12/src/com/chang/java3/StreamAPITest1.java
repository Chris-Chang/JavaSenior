package com.chang.java3;

import com.chang.java2.Employee;
import com.chang.java2.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的中间操作
 * @Description
 * @Author Chang Zhi
 * @Version
 * @Time 2020-04-06 12:30
 */
public class StreamAPITest1 {
    //1. 筛选与切片
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();
        //filter(Predicate p) --接受Lambda,从流中排除某些元素
        Stream<Employee> stream = list.stream();
        //练习：查询员工表中薪资大于7000的员工信息
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        System.out.println();
        //截断流，使其元素不超过给定数量
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();
        //skip(n) -- 跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流。与limit(n)互补
        list.stream().skip(3).forEach(System.out::println);
        System.out.println();

        //distinct()--筛选，通过流所生成的元素的hashCode()和equals()去除重复元素
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.stream().distinct().forEach(System.out::println);


    }

    //2. 映射
    @Test
    public void test2(){
        // map(function f)--接收一个函数作为参数，将元素转换为其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        //练习1：获取员工姓名长度大于3的员工的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(Employee::getName).filter(name -> name.length() > 3).forEach(System.out::println);
        //练习2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });

        System.out.println();
        //flatMap(Function f)--接收一个函数作为参数，将流中的每一个值都转换为另一个流
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);
    }

    //3. 排序
    @Test
    public void test4(){
        //  sorted()--自然排序
        List<Integer> list = Arrays.asList(13, 99, 2, 33, 6, 5, 0, -33, 1);
        list.stream().sorted().forEach(System.out::println);
        //sorted()定制排序

        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1,e2) ->{
            int ageValue = Integer.compare(e1.getAge(),e2.getAge());
            if(ageValue != 0){
                return ageValue;
            }else{
                return Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);
    }


    public static Stream<Character> fromStringToStream(String str){//aa
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }
}
