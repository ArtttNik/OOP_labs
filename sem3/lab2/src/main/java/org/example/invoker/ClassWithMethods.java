package org.example.invoker;

import org.example.annotation.Repeat;
import org.example.classes.F1Team;
import org.example.classes.Status;
import org.example.classes.User;

import java.util.Arrays;

public class ClassWithMethods {

    private final String text;
    private final int num;

    public ClassWithMethods() {
        this.text = "default";
        this.num = 100;
    }

    public ClassWithMethods(String text, int num) {
        this.text = text;
        this.num = num;
    }


    @Repeat(1)
    public void publicVoid() {
        System.err.println("!!!PUBLIC!!!");
    }

    protected void protectedNoAnnotation() {
        System.err.println("!!!NO ANNOTATION!!!");
    }

    @Repeat()
    protected void Repeat0() {
        System.err.println("!!!REPEAT 0!!!");
    }



    @Repeat(2)
    private void protectedVoid() {
        System.out.println("\nprotectedVoid: text = " + text);
    }

    @Repeat(2)
    protected void protectedPrims(int a, float b, boolean c) {
        System.out.println("\nprotectedPrims: a = " + a + ", b = " + b + ", c = " + c);
    }

    @Repeat(2)
    private void protectedStringString(String a, String b) {
        System.out.println("\nprivateStringString: " + a + " | " + b);
    }

    @Repeat(1)
    protected void protectedUser(User data) {
        System.out.println("\nprotectedUser: " + data);
    }

    @Repeat(1)
    protected void protectedCombined(String season, F1Team team, int races, User user) {
        System.out.println("\nprotectedStringF1TeamIntUser: Season " + season + ", races: " + races
                + ", team: " + team + ", user: " + user);
    }

    @Repeat(1)
    protected void protectedArrEnum(User[] users, Status status) {
        System.out.println("protectedArrEnum:" + "Users: " + Arrays.toString(users) + "Status: " + status);
    }

}