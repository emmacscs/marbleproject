import entities.*;
import java.util.*;

public class Controller3 {

    public static void main(String[] args) {
        double minimumUtility = 1000000000;
        Matrix bestAllocation = null;
        Random rnd = new Random();
        int l = 100;

        // Make network allocate
        double[] allutilities = new double[1000];

        ArrayList<Agent> agents = new ArrayList<Agent>();
        ArrayList<Resource> resources = new ArrayList<Resource>();
        ArrayList<Edge> edges = new ArrayList<Edge>();

        // Agents
        for (int i = 0; i < l; i++) {
            int makeAgent = rnd.nextInt(2); // Random number between 0 and 1

            if (makeAgent == 1) {
                continue; // Skip this iteration
            } else {
                double budget = rnd.nextDouble() + 100000; // Random budget between 0 and 1000
                int alpha = rnd.nextInt(501); // Random alpha between 0 and 500
                Agent temp = new Agent(budget, alpha);
                agents.add(temp);
            }
        }

        // Resources
        for (int j = 0; j < l; j++) {
            int make = rnd.nextInt(2); // Random number between 0 and 1

            if (make == 1) {
                continue; // Skip this iteration
            } else {
                double price = rnd.nextDouble() * 100; // Random price between 0 and 100
                int beta = rnd.nextInt(501); // Random beta between 0 and 500
                Resource temp = new Resource(price, beta);
                resources.add(temp);
            }
        }

        // Edges
        for (int k = 0; k < l; k++) {
            int make = rnd.nextInt(2); // Random number between 0 and 1

            if (make == 1) {
                continue; // Skip this iteration
            } else {
                int indexA = rnd.nextInt(agents.size());
                int indexR = rnd.nextInt(resources.size());
                Edge temp = new Edge(agents.get(indexA), resources.get(indexR));
                edges.add(temp);
            }
        }

        Network system = new Network(edges, agents, resources, 0);
        GraphicaBig graphicaFrame = new GraphicaBig(system, 0);
        graphicaFrame.setVisible(true);

        for (int i = 0; i < 1000; i++) {
            system.startAllocation();

            allutilities[i] = system.totalUtilityAtTheAllocation();

            if (system.totalUtilityAtTheAllocation() < minimumUtility) {
                minimumUtility = system.totalUtilityAtTheAllocation();
                bestAllocation = system.allocationMatrix;
            }
        }

        plot1 p = new plot1(allutilities);
        p.setVisible(true);

        System.out.println("Best allocation was utility:  " + minimumUtility);
        bestAllocation.matrixPrint();
    }

}
