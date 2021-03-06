package algorithm;

//import view.View;

import model.*;
import algorithm.PSO.Swarm;
import view.View;

import java.util.ArrayList;

public class PSOAlgorithm extends HeuristicAlgorithm {

    private ArrayList<Individual> population;
    private ArrayList<String> abc;
    private Element[] elementsList;

    public PSOAlgorithm(BackPack in) {
        this.population = getPopVariable().getPopulation();
        this.elementsList = PoolElements.getElements();
        abc = PSOAlgorithm.run(in);
    }

    @Override
    public String getName() {
        return "Particle Swarm Optimization";
    }

    public Individual doOtherSteps() {
        int tmp = this.getGenerationLevel();
        if (tmp > abc.size() - 1)
            tmp = abc.size() - 1;
        BackPack bp2 = new BackPack();
        for (int i = 0; i < abc.get(tmp).length(); i++) {
            if (abc.get(tmp).charAt(i) == '1') {
                bp2.updateElement(i, elementsList[i].getWeight(), elementsList[i].getImageFile());
            } else {
                bp2.updateElement(i, 0, "");
            }
        }
        return bp2;
    }

    @Override
    public Individual createIndividual() {
        return new Particle();
    }

    @Override
    public View getViewFile() {
        return View.PSO;
    }

    public static ArrayList<String> run(BackPack bp1) {

        Element[] elementsList = bp1.getElements();

        ArrayList<Float> weight = new ArrayList<Float>();

        //double testSum = 0;
        for (int i = 0; i < Element.MAX_ELEMENTS / 2; i++) {
            weight.add((float) (elementsList[i].getWeight()));
            //System.out.println(elementsList[i].getWeight());
        }
        Swarm swarm = new Swarm(10, 1000);
        //System.out.println(bp1.getWeight());
        swarm.runEach(BackPack.MAX_WEIGHT, weight);

        /*for (int i = 0; i < Swarm.finalAns.size(); i++) {
            System.out.println(Swarm.finalAns.get(i) + " ");
        }
        System.out.println(testSum);*/
        return Swarm.finalAns;

    }

    public static Individual getBestIndividual(String abc, Element[] elementsList) {
        BackPack in2 = new BackPack();
        for (int i = 0; i < abc.length(); i++) {
            if (abc.charAt(i) == '1') {
                in2.updateElement(i, elementsList[i].getWeight(), elementsList[i].getImageFile());
            } else {
                in2.updateElement(i, 0, "");
            }
        }
        return in2;
    }

    public static void main(String[] args) {
        BackPack in1 = new BackPack();

        ArrayList<String> abc = run(in1);
        for (int i = 0; i < abc.size(); i++) {
            System.out.println(abc.get(i));
        }

        Individual bestInd = getBestIndividual(abc.get(abc.size() - 1), in1.getElements());
    }

}