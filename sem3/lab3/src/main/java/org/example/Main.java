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

/* 3.
Создать метод segregate вида:
   - segregate(SrcCollection, Collection1, Collection2, Collection3)
Где:
   - SrcCollection – исходная коллекция животных
   - Collection1, Collection2, Collection3 – коллекции, в которые должны быть распределены
     соответственно ежи, манулы и рыси из SrcCollection
Необходимо, чтобы была возможность вызвать метод следующими способами:
   - segregate(Млекопитающие, Ежовые, Кошачьи, Хищные)
   - segregate(Хищные, Хордовые, Манулы, Кошачьи)
   - segregate(Ежовые, Насекомоядные, Хищные, Хищные)
Продемонстрировать работу метода
*/

class Main {

    public static final String METHOD_SELECTION_PROMPT = """
            \nSelect the method parameters:
            1. segregate(mammalsSrc, hedgehogs1, felines1, predators1)
            2. segregate(predatorsSrc, chordates2, manuls2, felines2)
            3. segregate(hedgehogsSrc, insectivores3, predators3, predators4)
            ELSE. Exit""";

    private static final Scanner scanner = new Scanner(System.in);

    public static void segregate(Collection<? extends Animal> src,
                                 Collection<? super Hedgehog> col1,
                                 Collection<? super Manul> col2,
                                 Collection<? super Lynx> col3) {

        Objects.requireNonNull(src);
        Objects.requireNonNull(col1);
        Objects.requireNonNull(col2);
        Objects.requireNonNull(col3);

        for (Animal a : new ArrayList<>(src)) {
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

        List<Hedgehog> hedgehogs1 = new ArrayList<>();
        List<Feline> felines1 = new ArrayList<>();
        List<Predator> predators1 = new ArrayList<>();

        List<Chordate> chordates2 = new ArrayList<>();
        List<Manul> manuls2 = new ArrayList<>();
        List<Feline> felines2 = new ArrayList<>();

        List<Hedgehog> hedgehogsSrc = new ArrayList<>();
        hedgehogsSrc.add(new CommonHedgehog("sniff", 7, true));

        List<Insectivore> insectivores3 = new ArrayList<>();
        List<Predator> predators3 = new ArrayList<>();
        List<Predator> predators4 = new ArrayList<>();

        while (true) {
            System.out.println(METHOD_SELECTION_PROMPT);
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    segregate(mammalsSrc, hedgehogs1, felines1, predators1);
                    hedgehogs1.forEach(Animal::voice);
                    felines1.forEach(Animal::voice);
                    predators1.forEach(Animal::voice);
                }
                case "2" -> {
                    segregate(predatorsSrc, chordates2, manuls2, felines2);
                    chordates2.forEach(Animal::voice);
                    manuls2.forEach(Animal::voice);
                    felines2.forEach(Animal::voice);
                }
                case "3" -> {
                    segregate(hedgehogsSrc, insectivores3, predators3, predators4);
                    insectivores3.forEach(Animal::voice);
                    predators3.forEach(Animal::voice);
                    predators4.forEach(Animal::voice);
                }
                default -> {
                    System.out.println("The end");
                    return;
                }
            }
        }
    }
}
