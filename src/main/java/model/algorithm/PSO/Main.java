package model.algorithm.PSO;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        if (args.length == 1 && args[0].equals("-p")) {
            menu(true);
        } else {
            System.out.print("Use the parameter '-p' to change the inertia, ");
            System.out.println("cognitive and social components.");
            System.out.println("Otherwise the default values will be: ");
            System.out.println("Inertia:             " + Swarm.DEFAULT_INERTIA);
            System.out.println("Cognitive Component: " + Swarm.DEFAULT_COGNITIVE);
            System.out.println("Social Component:    " + Swarm.DEFAULT_SOCIAL);
            menu(false);
        }
    }

    private static void menu (boolean flag) {
        Swarm swarm;
        Particle.FunctionType function;
        int particles, epochs;
        double inertia, cognitive, social;

        function = Particle.FunctionType.FunctionA;
        particles = 5;
        //getUserInt("Particles: ");
        epochs = 100;
        //getUserInt("Epochs:    ");

        if (flag) {
            inertia = getUserDouble("Inertia:   ");
            cognitive = getUserDouble("Cognitive: ");
            social = getUserDouble("Social:    ");
            swarm = new Swarm(function, particles, epochs, inertia, cognitive, social);
        } else {
            swarm = new Swarm(function, particles, epochs);

        }
        swarm.run();
    }

    private static int getUserInt (String msg) {
        int input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);

            if (sc.hasNextInt()) {
                input = sc.nextInt();

                if (input <= 0) {
                    System.out.println("Number must be positive.");
                } else {
                    break;
                }

            } else {
                System.out.println("Invalid input.");
            }
        }
        return input;
    }

    private static double getUserDouble (String msg) {
        double input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);

            if (sc.hasNextDouble()) {
                input = sc.nextDouble();

                if (input <= 0) {
                    System.out.println("Number must be positive.");
                } else {
                    break;
                }

            } else {
                System.out.println("Invalid input.");
            }
        }
        return input;
    }

}
