package org.example.annotation;

import org.example.classes.F1Team;
import org.example.classes.User;

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
        System.err.println("publicVoid - should not be called");
    }

    @Repeat(2)
    public void publicWithString(String msg) {
        System.err.println("publicWithString - should not be called: " + msg);
    }

    protected void protectedNoAnnotation() {
        System.err.println("I`m without annotation =)");
    }

    @Repeat()
    protected void protectedVoidRepeat0() {
        System.err.println("protectedVoidRepeat0 - should not be called Repeat(0): text=" + text);
    }



    @Repeat(1)
    protected void protectedVoid() {
        System.out.println("protectedVoid: text=" + text);
    }

    @Repeat(2)
    protected void protectedIntIntBoolean(int a, int b, boolean c) {
        System.out.println("protectedIntIntBoolean: a=" + a + ", b=" + b + ", c=" + c);
    }

    @Repeat(2)
    protected void protectedStringString(String a, String b) {
        System.out.println("protectedStringString: " + a + " | " + b);
    }

    @Repeat(3)
    protected void protectedUser(User data) {
        System.out.println("protectedUser: " + data);
    }

    @Repeat(2)
    protected void protectedF1Team(F1Team team) {
        System.out.println("protectedF1Team: " + team);
    }

    @Repeat(2)
    protected void protectedStringF1TeamInt(String season, F1Team team, int races) {
        System.out.println("protectedStringF1TeamInt: Season " + season + ", races: " + races + ", team: " + team);
    }

    @Repeat(1)
    private void privateVoid() {
        System.out.println("privateVoid: num=" + num);
    }

    @Repeat(2)
    private void privateStringInt(String msg, int x) {
        System.out.println("privateStringInt: msg=" + msg + ", x=" + x);
    }

    @Repeat(1)
    private void privateUser(User data) {
        System.out.println("privateUser: " + data);
    }

    @Repeat(3)
    private void privateF1TeamBoolean(F1Team team, boolean isChampion) {
        System.out.println("privateF1TeamBoolean: " + team + ", champion: " + isChampion);
    }

    @Repeat(1)
    private void privateF1TeamF1Team(F1Team team1, F1Team team2) {
        System.out.println("privateF1TeamF1Team: " + team1 + " vs " + team2);
    }
}