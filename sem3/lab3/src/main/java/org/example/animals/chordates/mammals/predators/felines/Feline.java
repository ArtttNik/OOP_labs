package org.example.animals.chordates.mammals.predators.felines;

import org.example.animals.chordates.mammals.predators.Predator;

public abstract class Feline extends Predator {
    public Feline(String sound, int age, boolean canBeEatenByPredators) {
        super(sound, age, canBeEatenByPredators);
    }
}