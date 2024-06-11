import java.util.ArrayList;

import entities.Agent;
import entities.Edge;
import entities.Matrix;
import entities.Resource;

public class Controller2 {
    
    public static void main(String[] args) {
        double minimumUtility = 10000000;
        Matrix bestAllocation = null;
        
    
        //Make network allocate
        double[] allutilities = new double[1000];
    
        //On each allocation I need to restart the network
        for(int i = 0; i < 1000; i ++)
        {
            //create Agents
        Agent a1 = new Agent(120,10);
        Agent a2 = new Agent(120,20);
        Agent a3 = new Agent(120,5);
        
    
        //create Resources
    
        Resource r1 = new Resource(1,5);
        Resource r2 = new Resource(1,20);
        Resource r3 = new Resource(1,15);
        Resource r4 = new Resource(1,20);
        Resource r5 = new Resource(1,15);
        //createEdges
    
        Edge e1 = new Edge(a1,r1);
        Edge e2 = new Edge(a1,r3);
        Edge e3 = new Edge(a2,r3);
        Edge e4 = new Edge(a2,r5);
        Edge e5 = new Edge(a3,r5);
       
    
        //createNetwork
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(a1);agents.add(a2);agents.add(a3);
        ArrayList<Resource> resources = new ArrayList<Resource>();
        resources.add(r1);resources.add(r2);resources.add(r3);resources.add(r4);
        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(e1);edges.add(e2);edges.add(e3);edges.add(e4);edges.add(e5);
    
            Network system = new Network(edges,agents,resources,1);
            system.startAllocation();
    
            allutilities[i] = system.totalUtilityAtTheAllocation();
            
            if(system.totalUtilityAtTheAllocation()< minimumUtility)
            {
                minimumUtility = system.totalUtilityAtTheAllocation();
                bestAllocation = system.allocationMatrix;
            }
        }
        
        plot p = new plot(allutilities);
        p.setVisible(true);
    
        System.out.println("Best allocation was utility:  " + minimumUtility);
        bestAllocation.matrixPrint();
    
    }

    /*Ideal is
     * 50500
     * 0010010
     * 00005
     */
}
