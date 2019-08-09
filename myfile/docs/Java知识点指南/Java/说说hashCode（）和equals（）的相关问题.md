###说说hashCode（）和equals（）的相关问题

本章的内容主要想解决一下几个问题：

+ [equals（） 和 == 的作用是什么？](#1. equals（） 和 == 的作用)
+ [equals（） 和 == 的区别是什么？](#2. equals（） 和 == 的区别)
+ [hashCode（） 的作用是什么？](#3. hashCode（） 的作用)
+ [hashCode（） 和 equals（） 之间有什么联系？](#4. hashCode（） 和 equals（） 之间的联系)

### 1. equals（） 和 == 的作用

+ == 是用来判断两个对象是否为==同一个对象==，通过判断两个对象的地址来区分它们是否相等。

+ equals（）是用来判断两个对象==是否相等==的，equals（）定义在Object.java中，所有类都继承了该方法。

从Object.java中的equals（）的源码可以看出，equals（）与 == 并无区别。

```java
public boolean equals(Object obj) {
	return (this == obj);
}
```

 所以，通常我们都是会重写equals（）方法：两个对象的内容相等，则返回true，反之false。举例：

+ 没有重写equals（）方法时：

```java
package com.tor;

import org.junit.Test;

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
}

    @Test
    public void test(){
        Student student1 = new Student("Tzx", 18);
        Student student2 = new Student("Tzx", 18);
        System.out.printf("student1.equals(student2) : %s ",             student1.equals(student2));
    }
}

```

运行结果：

```
student1.equals(student2) : false
```

结果分析：我们通过student1.equals(student2)来比较“两个对象是否相等时”，其实是调用Object.java的equals（）方法，是通过 == 比较的。从student1和student2的创建可以看出来，虽然两个对象的内容相等，但却是两个不同的对象。所以返回false。

+ 重写equals（）方法

```java
package com.tor;

import org.junit.Test;

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
    }

    @Test
    public void test(){
        Student student1 = new Student("Tzx", 18);
        Student student2 = new Student("Tzx", 18);
        System.out.printf("student1.equals(student2) : %s ", student1.equals(student2));
    }
}

```

运行结果：

```java
student1.equals(student2) : true 
```

结果分析：我们重写了equals（）方法，只要name和age相等就返回true。所以返回true。

### 2. equals（） 和 == 的区别

上面已经说明过了，从设计上来说 == 是用来判断是否为同一个对象，equals（）是用来判断两个对象是否相等。如果我们没有重写equals（）方法，那么二者其实是等价的。但一般我们会重写equals（）方法：只要两个对象的内容一样，那么就返回true。

### 3. hashCode（） 的作用

+ hashCode() 的作用是**获取哈希码**，也称为散列码；它实际上是返回一个int整数。这个**哈希码的作用**是确定该对象在哈希表中的索引位置。

+ hashCode() 定义在JDK的Object.java中，这就意味着Java中的任何类都包含有hashCode() 函数。
+ 虽然，每个Java类都包含hashCode() 函数。但是，仅仅当创建并某个“类的散列表”(关于“散列表”见下面说明)时，该类的hashCode() 才有用(作用是：确定该类的每一个对象在散列表中的位置；其它情况下(例如，创建类的单个对象，或者创建类的对象数组等等)，类的hashCode() 没有作用。
+ 上面的散列表，指的是：Java集合中本质是散列表的类，如HashMap，Hashtable，HashSet。
+ 也就是说：**hashCode() 在散列表中才有用，在其它情况下没用。**在散列表中hashCode() 的作用是获取对象的散列码，进而确定该对象在散列表中的位置。

```java
我们都知道，散列表存储的是键值对(key-value)，它的特点是：能根据“键”快速的检索出对应的“值”。这其中就利用到了散列码！
散列表的本质是通过数组实现的。当我们要获取散列表中的某个“值”时，实际上是要获取数组中的某个位置的元素。而数组的位置，就是通过“键”来获取的；更进一步说，数组的位置，是通过“键”对应的散列码计算得到的。
```

下面，我们以HashSet为例，来深入说明hashCode()的作用。
    假设，HashSet中已经有1000个元素。当插入第1001个元素时，需要怎么处理？因为HashSet是Set集合，它不允许有重复元素。“将第1001个元素逐个的和前面1000个元素进行比较”？显然，这个效率是相当低下的。散列表很好的解决了这个问题，它根据元素的散列码计算出元素在散列表中的位置，然后将元素插入该位置即可。对于相同的元素，自然是只保存了一个。
    由此可知，若两个元素相等，它们的散列码一定相等；但反过来确不一定。在散列表中，
    1、如果两个对象相等，那么它们的hashCode()值一定要相同；
    2、如果两个对象hashCode()相等，它们并不一定相等。
    注意：这是在散列表中的情况。在非散列表中一定如此！
对“hashCode()的作用”就谈这么多。

### 4. hashCode（） 和 equals（） 之间的联系

我们从“类的用途”来将“hashCode() 和 equals()的关系”分2种情况来说明。

+ **第一种 不会创建“类对应的散列表”**

```html
 这里所说的“不会创建类对应的散列表”是说：我们不会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中，用到该类。例如，不会创建该类的HashSet集合。
 在这种情况下，该类的“hashCode() 和 equals() ”没有半毛钱关系的！
 这种情况下，equals() 用来比较该类的两个对象是否相等。而hashCode() 则根本没有任何作用，所以，不用理会hashCode()。
```
下面，我们通过示例查看类的 **两个对象相等** 以及 **不等** 时hashCode()的取值。

```java
package com.tor;

import org.junit.Test;

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
    }

    @Test
    public void test(){
        Student student1 = new Student("Tzx", 18);
        Student student2 = new Student("Tzx", 18);
        Student student3 = new Student("Tzx", 16);
        System.out.printf("student1.equals(student2) : %s ,student1.hashCode(): %d ,student2.hashCode(): %d \n",
                student1.equals(student2),student1.hashCode(),student2.hashCode());
        System.out.printf("student1.equals(student3) : %s ,student1.hashCode(): %d ,student3.hashCode(): %d",
                student1.equals(student3),student1.hashCode(),student3.hashCode());
    }
}

```

运行结果：

```java
student1.equals(student2) : true ,student1.hashCode(): 615634843,
student2.hashCode(): 1758386724 
student1.equals(student3) : false ,student1.hashCode(): 615634843 ,
student3.hashCode(): 673068808
```

结果分析：就算我们重写了equals（）方法，两个对象相等，但他们的hashCode（）也不一定相等。

+ **第二种 会创建“类对应的散列表”**

这里所说的“会创建类对应的散列表”是说：我们会在**HashSet**, **Hashtable**, **HashMap**等等这些本质是散列表的数据结构中，用到该类。例如，会创建该类的HashSet集合。
        在这种情况下，该类的“hashCode() 和 equals() ”是有关系的：
        1)、如果两个对象相等，那么它们的hashCode()值一定相同。
              这里的相等是指，通过equals()比较两个对象时返回true。
        2)、如果两个对象hashCode()相等，它们并不一定相等。
               因为在散列表中，hashCode()相等，即两个键值对的哈希值相等。然而哈希值相等，并不一定能得出键值对相等。补充说一句：“两个不同的键值对，哈希值相等”，这就是哈希冲突。
        此外，在这种情况下。若要判断两个对象是否相等，除了要覆盖equals()之外，也要覆盖hashCode()函数。否则，equals()无效。
例如，创建Person类的HashSet集合，必须同时覆盖Person类的equals() 和 hashCode()方法。如果单单只是覆盖equals()方法。我们会发现，equals()方法没有达到我们想要的效果。

```java
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

```

运行结果：

```java
student1.equals(student2) : true ,student1.hashCode(): 615634843 ,student2.hashCode(): 1758386724 
student1.equals(student3) : false ,student1.hashCode(): 615634843 ,student3.hashCode(): 673068808 
HashSet: [{name:Tzx,age:18}, {name:Tzx,age:18}, {name:Tzx,age:16}] 
```

结果分析：我们重写了equals（）方法，但奇怪的是，HashSet中任然有重复的元素：student1和student2。这是因为虽然这两个对象相等，但是他们的hashCode（）值不一样。所以HashSet在添加它们时，认为它们不相等。

重写hashCode（）方法

```java
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

```

运行结果：

```java
student1.equals(student2) : true ,student1.hashCode(): -1096419059 ,student2.hashCode(): -1096419059 
student1.equals(student3) : false ,student1.hashCode(): -1096419059 ,student3.hashCode(): -1096419121 
HashSet: [{name:Tzx,age:18}, {name:Tzx,age:16}] 
```

结果分析：我们重写了hashCode（）方法之后，hashSet中就之后两个对象了，因为student1和student2的hashCode（）值一样，所以HashSet认为它们相等，就不会存第二次了。