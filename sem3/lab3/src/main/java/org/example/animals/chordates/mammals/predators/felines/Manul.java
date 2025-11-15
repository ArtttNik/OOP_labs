package org.example.animals.chordates.mammals.predators.felines;

public class Manul extends Feline {
    public Manul(String sound, int age, boolean canBeEatenByPredators) {
        super(sound, age, canBeEatenByPredators);
    }

    @Override
    public void voice() {
        System.out.println("Manulling: \"Meow!\"");
    }
}