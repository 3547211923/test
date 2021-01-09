package com.Josephus.chat.Register;



class Student {
    String Stnum;//学号
    String name;//姓名
    int ach1;//java成绩
    int ach2;//数据结构成绩

    public Student() { }

    //设计一个能初始化学号，姓名等特征的构造方法；另一个构造方法能初始化所有学生特征；
    public Student(String aStnum, String aname, int aach1, int aach2) {
        Stnum = aStnum;
        name = aname;
        ach1 = aach1;
        ach2 = aach2;
    }

    //    （3）设计一个学生上课的方法，该方法有3个参数，一个参数表示上课时间，另一个参数表示课程名称，第三个参数表示上课地点。
    public int getSum() {
        //    (1)计算某个学生的总分和平均分;
        return ach1 + ach2;
    }

    public int getAver() {
        return (ach1 + ach2) / 2;
    }

    public void AttendClass(String time, String course, String place) {
        System.out.println(name+"同学" + time + ",在" + place + "学习"+course);
    }
}

public class Test1 {
//            1.创建包括所有特征的2个学生对象；
//            2.  输出这2个学生的总分和平均分；
//            3. 第一个学生在2017年9月29日，在3223学习Java课程；第二个学生在2017年9月29日，在1402学习数据结构。
    public static void main(String[] args) {

        Student s1 = new Student();
        Student s2 = new Student();
        s1.Stnum="123";s1.name="a";s1.ach1=88;s1.ach2=78;
        s2.Stnum="456";s2.name="b";s2.ach1=78;s2.ach2=78;
        System.out.println("学生"+ s1.name+"的总分为:"+s1.getSum()+"  平均分为："+s1.getAver());
        System.out.println("学生"+ s2.name+"的总分为:"+s2.getSum()+"  平均分为："+s2.getAver());
        s1.AttendClass("2017年9月29日","Java课程","3223");
        s2.AttendClass("2017年9月29日","数据结构","1402");
}
}