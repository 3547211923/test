package com.Josephus.chat.client;

class Person{
    String name;
    void reading(String book) {
        System.out.println(name+"�����Ķ�"+book);

    }void display(){
        System.out.println("������"+name);}
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
        return "�����εĳ���"+length+",���ǣ�"+width;
    }
}
public class SimpleObjectDemo{
    public static void main(String[] args) {
        Person me=new Person();
        me.name="����";
        me.reading("����");
                me.display();
        Rectangle r=new Rectangle();
        r.length=10;
        r.width=20.5;
        System.out.println(r.getRectangle());
        System.out.println("����ǣ�"+r.area());
        System.out.println("�ܳ��ǣ�"+r.perimeter());
    }
}
