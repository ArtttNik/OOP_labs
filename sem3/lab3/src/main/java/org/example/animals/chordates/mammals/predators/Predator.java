package org.example.animals.chordates.mammals.predators;

import org.example.animals.chordates.mammals.Mammal;

public abstract class Predator extends Mammal {
    public Predator(String sound, int age, boolean canBeEatenByPredators) {
        super(sound, age, canBeEatenByPredators);
    }
}