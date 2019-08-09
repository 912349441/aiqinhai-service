package com.tor;

import org.junit.Test;

import java.util.HashSet;

public class MyHashCodeTest {

    public static class Student{
        private String name;
        private Integer age;
        Student(String name,Integer age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null){
                return false;
            }
            if(this == obj){
                return true;
            }
            if(this.getClass() != obj.getClass()){
                return false;
            }
            Student student = (Student) obj;
            return this.getName().equals(student.getName()) && this.getAge().equals(student.getAge());
        }

        @Override
        public int hashCode() {
            return toString().toUpperCase().hashCode();
        }

        @Override
        public String toString() {
            return "{name:"+name+",age:"+age+"}";
        }
    }

    @Test
    public void test(){
        Student student1 = new Student("Tzx", 18);
        Student student2 = new Student("Tzx", 18);
        Student student3 = new Student("Tzx", 16);
        System.out.printf("student1.equals(student2) : %s ,student1.hashCode(): %d ,student2.hashCode(): %d \n",
                student1.equals(student2),student1.hashCode(),student2.hashCode());
        System.out.printf("student1.equals(student3) : %s ,student1.hashCode(): %d ,student3.hashCode(): %d \n",
                student1.equals(student3),student1.hashCode(),student3.hashCode());

        HashSet<Student> students = new HashSet<>(3);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        System.out.printf("HashSet: %s \n",students);
    }
}
