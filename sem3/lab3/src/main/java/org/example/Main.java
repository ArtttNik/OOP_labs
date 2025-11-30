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
            Select the method parameters:
            1. segregate(mammals, erinaceidaes, felidaes, carnivoras)
            2. segregate(carnivoras, chordates, pallasCats, felidaes)
            3. segregate(erinaceidaes, eulipotyphlas, carnivoras, carnivoras)
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
                default -> System.out.println(a + "does not match Manul, Lynx or Hedgehog");
            }
        }
    }

    public static void main(String[] args) {

        List<Mammal> mammals = new ArrayList<>();
        mammals.add(new CommonHedgehog("hiss", 2, true));
        mammals.add(new Manul("meow", 3, false));
        mammals.add(new Lynx("roar", 4, false));
        mammals.add(new CommonHedgehog("puff", 1, true));

        List<Predator> carnivoras = new ArrayList<>();
        carnivoras.add(new Manul("purr", 5, false));
        carnivoras.add(new Lynx("growl", 6, false));

        List<Hedgehog> erinaceidaes = new ArrayList<>();
        List<Feline> felidaes = new ArrayList<>();
        List<Predator> carnivoras2 = new ArrayList<>();

        List<Chordate> chordates = new ArrayList<>();
        List<Manul> pallasCats = new ArrayList<>();
        List<Feline> felidaes2 = new ArrayList<>();

        List<Hedgehog> erinaceidaesSrc = new ArrayList<>();
        erinaceidaesSrc.add(new CommonHedgehog("sniff", 7, true));

        List<Insectivore> eulipotyphlas = new ArrayList<>();
        List<Predator> carnivoras3 = new ArrayList<>();
        List<Predator> carnivoras4 = new ArrayList<>();

        while (true) {
            System.out.println(METHOD_SELECTION_PROMPT);
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    segregate(mammals, erinaceidaes, felidaes, carnivoras2);
                    erinaceidaes.forEach(Animal::voice);
                    felidaes.forEach(Animal::voice);
                    carnivoras2.forEach(Animal::voice);
                }
                case "2" -> {
                    segregate(carnivoras, chordates, pallasCats, felidaes2);
                    chordates.forEach(Animal::voice);
                    pallasCats.forEach(Animal::voice);
                    felidaes2.forEach(Animal::voice);
                }
                case "3" -> {
                    segregate(erinaceidaesSrc, eulipotyphlas, carnivoras3, carnivoras4);
                    eulipotyphlas.forEach(Animal::voice);
                    carnivoras3.forEach(Animal::voice);
                    carnivoras4.forEach(Animal::voice);
                }
                default -> {
                    System.out.println("The end");
                    return;
                }
            }
        }
    }
}
