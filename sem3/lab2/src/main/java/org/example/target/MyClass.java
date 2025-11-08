package org.example.target;

import org.example.annotation.Repeat;

public class MyClass {

    @Repeat(times = 2)
    public void publicMethod1() {
        System.out.println("Вызван publicMethod1");
    }

    @Repeat(times = 1)
    public void publicMethod2(String msg) {
        System.out.println("Вызван publicMethod2 с msg = " + msg);
    }

    @Repeat(times = 3)
    protected void protectedMethod1() {
        System.out.println("Вызван protectedMethod1");
    }

    @Repeat(times = 2)
    protected void protectedMethod2(String msg) {
        System.out.println("Вызван protectedMethod2 с msg = " + msg);
    }

    @Repeat(times = 1)
    protected void protectedMethod3(int a, int b) {
        System.out.println("Вызван protectedMethod3 с a=" + a + ", b=" + b);
    }

    @Repeat(times = 2)
    private void privateMethod1() {
        System.out.println("Вызван privateMethod1");
    }

    @Repeat(times = 3)
    private void privateMethod2(String msg) {
        System.out.println("Вызван privateMethod2 с msg = " + msg);
    }

    @Repeat(times = 1)
    private void privateMethod3(int a, int b) {
        System.out.println("Вызван privateMethod3 с a=" + a + ", b=" + b);
    }
}
