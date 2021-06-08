package algorithm;

import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GeneticAlgorithm extends HeuristicAlgorithm {

    private ArrayList<BackPack> population = new ArrayList<>();

    public GeneticAlgorithm() {
        for (Individual i : getPopVariable().getPopulation()) {
            population.add((BackPack) i);
        }
    }

    public BackPack doOtherSteps() {
        // sort individual theo fitness
        crossOverStep(this.population);
        mutateStep(this.population);
        // add notify method here
        return (BackPack) getPopVariable().getBestIndividual();
    }

    public static ArrayList<BackPack> crossOverStep(ArrayList<BackPack> population){
        ArrayList<BackPack> pop = population;
        Collections.sort(
                pop, Comparator.comparingDouble(BackPack::fitness)
        );
        for (int i = 0; i < Population.NUM_INDIVIDUAL / 2; i++) {
            crossover(pop.get(i), pop.get(Population.NUM_INDIVIDUAL - i - 1));
        }
        return pop;
    }
    public static void crossover(BackPack g1, BackPack g2) {
        // swap half of elements in g1 with half of elements in g2, number of elements in g1 equals to g2
        Element[] items1 = g1.getElements();
        Element[] items2 = g2.getElements();
        int i;
        double tmpWei;
        String tmpString;
        for (i = 0; i < g1.getNumOfElement(); i++) {
            if (!g1.isContain(items2[i]) && !g2.isContain(items1[i])) {
                tmpWei = items1[i].getWeight();
                tmpString = items1[i].getImageFile();
                g1.updateElement(i, items2[i].getWeight(), items2[i].getImageFile());
                g2.updateElement(i, tmpWei, tmpString);
            }
        }
    }
    public static ArrayList<BackPack> mutateStep(ArrayList<BackPack> population){
        ArrayList<BackPack> pop = population;
        for (int i = 0; i < Population.NUM_INDIVIDUAL / 2; i++)
            mutate(pop.get(i));
        return pop;
    }


    public static void mutate(BackPack bp) {
        // update randomly 1/10 elements in the backpack with the new element not in backpack before
        int ran = (int) (Math.random() * 10);
        Element e = bp.getNewRandomElement();
        bp.updateElement(ran, e.getWeight(), e.getImageFile());
    }

    public ArrayList<BackPack> getPopulations() {
        return population;
    }

    /*public static void main(String[] args) {
        GeneticAlgorithm gA = new GeneticAlgorithm();
        Individual bestInd = gA.solve();
        System.out.println(bestInd + ", " + bestInd.getWeight());
    }*/
}
