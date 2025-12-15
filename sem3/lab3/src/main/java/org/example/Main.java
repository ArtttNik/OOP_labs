package org.example;

import org.example.animals.Animal;
import org.example.animals.chordates.Chordate;
import org.example.animals.chordates.mammals.Mammal;
import org.example.animals.chordates.mammals.insectivores.Insectivore;
import org.example.animals.chordates.mammals.insectivores.hedgehog.CommonHedgehog;
import org.example.animals.chordates.mammals.insectivores.hedgehog.Hedgehog;
import org.example.animals.chordates.mammals.predators.Predator;
import org.example.animals.chordates.mammals.predators.felines.Feline;
import org.example.animals.chordates.mammals.predators.felines.Lynx;
import org.example.animals.chordates.mammals.predators.felines.Manul;

import java.util.*;

class Main {

    public static final String METHOD_SELECTION_PROMPT = """
            \nSelect the method parameters:
            1. segregate(mammalsSrc, hedgehogs, felines, predators)
            2. segregate(predatorsSrc, chordates, manuls, felines)
            3. segregate(hedgehogsSrc, insectivores, predators, predators)
            ELSE. Exit""";

    private static final Scanner scanner = new Scanner(System.in);

    public static void segregate(Collection<? extends Animal> src,
                                 Collection<? super Hedgehog> col1,
                                 Collection<? super Manul> col2,
                                 Collection<? super Lynx> col3)
    throws NullPointerException {

        if (src == null)
            throw new NullPointerException("src is null");
        if (col1 == null)
            throw new NullPointerException("col1 is null");
        if (col2 == null)
            throw new NullPointerException("col2 is null");
        if (col3 == null)
            throw new NullPointerException("col3 is null");

        for (Animal a : new ArrayList<>(src)) {
            if (a == null) {
                throw new NullPointerException("src contains null element");
            }

            switch (a) {
                case Hedgehog h -> col1.add(h);
                case Manul m -> col2.add(m);
                case Lynx l -> col3.add(l);
                default -> System.out.println(a + " does not match Manul, Lynx or Hedgehog");
            }
        }
    }

    public static void main(String[] args) {

        List<Mammal> mammalsSrc = new ArrayList<>();
        mammalsSrc.add(new CommonHedgehog("hiss", 2, true));
        mammalsSrc.add(new Manul("meow", 3, false));
        mammalsSrc.add(new Lynx("roar", 4, false));
        mammalsSrc.add(new CommonHedgehog("puff", 1, true));

        List<Predator> predatorsSrc = new ArrayList<>();
        predatorsSrc.add(new Manul("purr", 5, false));
        predatorsSrc.add(new Lynx("growl", 6, false));

        List<Hedgehog> hedgehogsSrc = new ArrayList<>();
        hedgehogsSrc.add(new CommonHedgehog("sniff", 7, true));
        hedgehogsSrc.add(new CommonHedgehog("phyir", 21, true));

        List<Hedgehog> hedgehogs = new ArrayList<>();
        List<Manul> manuls = new ArrayList<>();
        List<Chordate> chordates = new ArrayList<>();
        List<Feline> felines = new ArrayList<>();
        List<Predator> predators = new ArrayList<>();
        List<Insectivore> insectivores = new ArrayList<>();

        while (true) {
            System.out.println(METHOD_SELECTION_PROMPT);
            String option = scanner.nextLine();

            insectivores.clear();
            predators.clear();
            manuls.clear();
            chordates.clear();
            hedgehogs.clear();
            felines.clear();

            try {
                switch (option) {
                    case "1" -> {
                        segregate(mammalsSrc, hedgehogs, manuls, predators);
                        hedgehogs.forEach(Animal::voice);
                        manuls.forEach(Animal::voice);
                        predators.forEach(Animal::voice);
                    }
                    case "2" -> {
                        segregate(predatorsSrc, chordates, manuls, felines);
                        chordates.forEach(Animal::voice);
                        manuls.forEach(Animal::voice);
                        felines.forEach(Animal::voice);
                    }
                    case "3" -> {
                        segregate(hedgehogsSrc, insectivores, predators, predators);
                        insectivores.forEach(Animal::voice);
                        predators.forEach(Animal::voice);
                    }
                    default -> {
                        System.out.println("The end");
                        return;
                    }
                }
            } catch (NullPointerException e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        }
    }
}
