package org.example.animals.chordates.mammals;

import org.example.animals.chordates.Chordate;

public abstract class Mammal extends Chordate {
    public Mammal(String sound, int age, boolean canBeEatenByPredators) {
        super(sound, age, canBeEatenByPredators);
    }
}
