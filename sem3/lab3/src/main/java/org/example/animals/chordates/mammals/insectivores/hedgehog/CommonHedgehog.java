package org.example.animals.chordates.mammals.insectivores.hedgehog;

public class CommonHedgehog extends Hedgehog {
    public CommonHedgehog(String sound, int age, boolean canBeEatenByPredators) {
        super(sound, age, canBeEatenByPredators);
    }

    @Override
    public void voice() {
        System.out.println("CommonHedgehoging: \"phyr, phir, phyir " + getSound() + '"');
    }
}
