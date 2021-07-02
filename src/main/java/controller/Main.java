package controller;

import algorithm.GeneticAlgorithm;
import algorithm.HillClimbingAlgorithm;
import algorithm.PSOAlgorithm;
import model.BackPack;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        AlgorithmController a = new Visualization();

        if(s.hasNext("Genetic")){
            a.visualize(new GeneticAlgorithm());
        }
        else if(s.hasNext("HillClimbing")){
            a.visualize(new HillClimbingAlgorithm());
        }
        else if(s.hasNext("PSO")){
            a.visualize(new PSOAlgorithm(new BackPack()));
        }
    }

}
