package org.example.animals;

public abstract class Animal {
    protected String sound;
    protected int age;
    protected boolean canBeEatenByPredators;

    public Animal(String sound, int age, boolean canBeEatenByPredators) {
        this.sound = sound;
        this.age = age;
        this.canBeEatenByPredators = canBeEatenByPredators;
    }

    public String getSound() {
        return sound;
    }

    public int getAge() {
        return age;
    }

    public boolean getEatality() {
        return canBeEatenByPredators;
    }

    public abstract void voice();
}
