package com.Josephus.chat.client;

class Person{
    String name;
    void reading(String book) {
        System.out.println(name+"正在阅读"+book);

    }void display(){
        System.out.println("姓名："+name);}
}


class Rectangle{
    double length;
    double width;
    public double area() {
        return length*length;
    }
    public double perimeter() {
        return 2*(length+width);
    }
    public String getRectangle() {
        return "长方形的长："+length+",宽是："+width;
    }
}
public class SimpleObjectDemo{
    public static void main(String[] args) {
        Person me=new Person();
        me.name="孔子";
        me.reading("论语");
                me.display();
        Rectangle r=new Rectangle();
        r.length=10;
        r.width=20.5;
        System.out.println(r.getRectangle());
        System.out.println("面积是："+r.area());
        System.out.println("周长是："+r.perimeter());
    }
}
