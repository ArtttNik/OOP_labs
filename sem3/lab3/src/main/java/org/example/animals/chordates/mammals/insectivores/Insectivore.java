package org.example.animals.chordates.mammals.insectivores;

import org.example.animals.chordates.mammals.Mammal;

public abstract class Insectivore extends Mammal {
    public Insectivore(String sound, int age, boolean canBeEatenByPredators) {
        super(sound, age, canBeEatenByPredators);
    }
}