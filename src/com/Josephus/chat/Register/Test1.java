package com.Josephus.chat.Register;



class Student {
    String Stnum;//ѧ��
    String name;//����
    int ach1;//java�ɼ�
    int ach2;//���ݽṹ�ɼ�

    public Student() { }

    //���һ���ܳ�ʼ��ѧ�ţ������������Ĺ��췽������һ�����췽���ܳ�ʼ������ѧ��������
    public Student(String aStnum, String aname, int aach1, int aach2) {
        Stnum = aStnum;
        name = aname;
        ach1 = aach1;
        ach2 = aach2;
    }

    //    ��3�����һ��ѧ���Ͽεķ������÷�����3��������һ��������ʾ�Ͽ�ʱ�䣬��һ��������ʾ�γ����ƣ�������������ʾ�Ͽεص㡣
    public int getSum() {
        //    (1)����ĳ��ѧ�����ֺܷ�ƽ����;
        return ach1 + ach2;
    }

    public int getAver() {
        return (ach1 + ach2) / 2;
    }

    public void AttendClass(String time, String course, String place) {
        System.out.println(name+"ͬѧ" + time + ",��" + place + "ѧϰ"+course);
    }
}

public class Test1 {
//            1.������������������2��ѧ������
//            2.  �����2��ѧ�����ֺܷ�ƽ���֣�
//            3. ��һ��ѧ����2017��9��29�գ���3223ѧϰJava�γ̣��ڶ���ѧ����2017��9��29�գ���1402ѧϰ���ݽṹ��
    public static void main(String[] args) {

        Student s1 = new Student();
        Student s2 = new Student();
        s1.Stnum="123";s1.name="a";s1.ach1=88;s1.ach2=78;
        s2.Stnum="456";s2.name="b";s2.ach1=78;s2.ach2=78;
        System.out.println("ѧ��"+ s1.name+"���ܷ�Ϊ:"+s1.getSum()+"  ƽ����Ϊ��"+s1.getAver());
        System.out.println("ѧ��"+ s2.name+"���ܷ�Ϊ:"+s2.getSum()+"  ƽ����Ϊ��"+s2.getAver());
        s1.AttendClass("2017��9��29��","Java�γ�","3223");
        s2.AttendClass("2017��9��29��","���ݽṹ","1402");
}
}