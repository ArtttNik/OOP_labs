package org.example.target;

import org.example.annotation.Repeat;
import java.util.Random;

public class MyClass {
    private String text;
    private int num;

    public MyClass() {
        this.text = "Сообщение из конструктора =)";
        this.num = 123456789;
    }

    public MyClass(String text, int num) {
        this.num = num;
        this.text = text;
    }

    @Repeat(value = 1)
    public void publicMethod1() {
        System.out.println("Вызван publicMethod1, а не должен был; =/ с сообщением = " + text + "; числом = " + num);
    }

    @Repeat(value = 2)
    public void publicMethod2(String msg) {
        System.out.println("Вызван publicMethod2, а не должен был; =/ с msg = " + msg);
    }

    @Repeat(value = 1)
    protected void protectedMethod1() {
        System.out.println("Вызван protectedMethod1; с сообщением = " + text + "; и числом = " + num);
    }

    @Repeat(value = 2)
    protected void protectedMethod2(String msg) {
        System.out.println("Вызван protectedMethod2; с msg = " + msg);
    }

    @Repeat(value = 3)
    protected void protectedMethod3(int a, int b) {
        System.out.println("Вызван protectedMethod3; с a=" + a + ", b=" + b);
    }

    @Repeat(value = 1)
    private void privateMethod1() {
        System.out.println("Вызван privateMethod1; с сообщением = " + text + "; и числом = " + num);
    }

    @Repeat(value = 2)
    private void privateMethod2(String msg) {
        System.out.println("Вызван privateMethod2; с msg = " + msg);
    }

    @Repeat(value = 3)
    private void privateMethod3(int a, int b) {
        System.out.println("Вызван privateMethod3; с a=" + a + ", b=" + b);
    }
}
