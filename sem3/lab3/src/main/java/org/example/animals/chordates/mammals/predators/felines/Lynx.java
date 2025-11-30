package org.example.animals.chordates.mammals.predators.felines;

public class Lynx extends Feline {
    public Lynx(String sound, int age, boolean canBeEatenByPredators) {
        super(sound, age, canBeEatenByPredators);
    }

    @Override
    public void voice() {
        System.out.println("Is is correct to spell \"Linux " + getSound() + '"');
    }
}