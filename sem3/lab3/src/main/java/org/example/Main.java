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
    public static void segregate(Collection<? extends Animal> src,
                                 Collection<? super Hedgehog> col1,
                                 Collection<? super Manul> col2,
                                 Collection<? super Lynx> col3) {
        for (Animal a : src) {
            if (a instanceof Hedgehog) {
                col1.add((Hedgehog) a);
            } else if (a instanceof Manul) {
                col2.add((Manul) a);
            } else if (a instanceof Lynx) {
                col3.add((Lynx) a);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("======");
        List<Mammal> mammals = new ArrayList<>();
        mammals.add(new CommonHedgehog("hiss", 2, true));
        mammals.add(new Manul("meow", 3, false));
        mammals.add(new Lynx("roar", 4, false));
        mammals.add(new CommonHedgehog("puff", 1, true));
        System.out.println("Before: Mammals size: " + mammals.size());

        List<Hedgehog> hedgehogs = new ArrayList<>();
        List<Feline> felinesForManuls = new ArrayList<>();
        List<Predator> predatorsForLynx = new ArrayList<>();

        segregate(mammals, hedgehogs, felinesForManuls, predatorsForLynx);

        System.out.println("After: Mammals size: " + mammals.size());
        System.out.println("Hedgehogs size: " + hedgehogs.size());
        System.out.println("Felines (manuls) size: " + felinesForManuls.size());
        System.out.println("Predators (lynx) size: " + predatorsForLynx.size());

        System.out.println("Voices after segregation:");
        for (Hedgehog h : hedgehogs) {
            h.voice();
        }
        for (Feline f : felinesForManuls) {
            f.voice();
        }
        for (Predator p : predatorsForLynx) {
            p.voice();
        }
        System.out.println();

        System.out.println("======");
        List<Predator> predators = new ArrayList<>();
        predators.add(new Manul("purr", 5, false));
        predators.add(new Lynx("growl", 6, false));
        System.out.println("Before: Predators size: " + predators.size());

        List<Chordate> chordates = new ArrayList<>();
        List<Manul> manuls = new ArrayList<>();
        List<Feline> felines = new ArrayList<>();

        segregate(predators, chordates, manuls, felines);

        System.out.println("After: Predators size: " + predators.size());
        System.out.println("Chordates (hedgehogs) size: " + chordates.size());
        System.out.println("Manuls size: " + manuls.size());
        System.out.println("Felines (lynx) size: " + felines.size());

        System.out.println("Voices after segregation:");
        for (Chordate c : chordates) {
            c.voice();
        }
        for (Manul m : manuls) {
            m.voice();
        }
        for (Feline f : felines) {
            f.voice();
        }
        System.out.println();

        System.out.println("======");
        List<Hedgehog> hedgehogsSrc = new ArrayList<>();
        hedgehogsSrc.add(new CommonHedgehog("sniff", 7, true));
        System.out.println("Before: Hedgehogs size: " + hedgehogsSrc.size());

        List<Insectivore> insectivores = new ArrayList<>();
        List<Predator> predsForManuls = new ArrayList<>();
        List<Predator> predsForLynx = new ArrayList<>();

        segregate(hedgehogsSrc, insectivores, predsForManuls, predsForLynx);

        System.out.println("After: Hedgehogs size: " + hedgehogsSrc.size());
        System.out.println("Insectivores (hedgehogs) size: " + insectivores.size());
        System.out.println("Predators (manuls) size: " + predsForManuls.size());
        System.out.println("Predators (lynx) size: " + predsForLynx.size());

        System.out.println("Voices after segregation:");
        for (Insectivore i : insectivores) {
            i.voice();
        }
        for (Predator p : predsForManuls) {
            p.voice();
        }
        for (Predator p : predsForLynx) {
            p.voice();
        }
    }
}