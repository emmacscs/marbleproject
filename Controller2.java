import java.util.ArrayList;

import entities.Agent;
import entities.Edge;
import entities.Matrix;
import entities.Resource;

public class Controller2
{

    public Network bestNetwork;
    public static void main(String[] args)
    {
        double minimumUtility = 10000000;
        Matrix bestAllocation = null;
        
    
        //Make network allocate
        double[] allutilities = new double[1000];

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
         agents.add(a1);
         agents.add(a2);
         agents.add(a3);

         ArrayList<Resource> resources = new ArrayList<Resource>();
         resources.add(r1);
         resources.add(r2);
         resources.add(r3);
         resources.add(r4);
         resources.add(r5);

         ArrayList<Edge> edges = new ArrayList<Edge>();
         edges.add(e1);
         edges.add(e2);
         edges.add(e3);
         edges.add(e4);
         edges.add(e5);
 
         Network bestNetwork = new Network(edges,agents,resources,1);
    
        //On each allocation I need to restart the network
        for(int i = 0; i < 1000; i ++)
        {
            //create Agents
             a1 = new Agent(120,10);
             a2 = new Agent(120,20);
             a3 = new Agent(120,5);
            
            //create Resources
             r1 = new Resource(1,5);
             r2 = new Resource(1,20);
             r3 = new Resource(2,15);
             r4 = new Resource(1,20);
             r5 = new Resource(1,15);

            //createEdges
             e1 = new Edge(a1,r1);
             e2 = new Edge(a1,r3);
             e3 = new Edge(a2,r3);
             e4 = new Edge(a2,r5);
             e5 = new Edge(a3,r5);
        
            //createNetwork
             agents = new ArrayList<Agent>();
            agents.add(a1);
            agents.add(a2);
            agents.add(a3);

             resources = new ArrayList<Resource>();
            resources.add(r1);
            resources.add(r2);
            resources.add(r3);
            resources.add(r4);
            resources.add(r5);

            edges = new ArrayList<Edge>();
            edges.add(e1);
            edges.add(e2);
            edges.add(e3);
            edges.add(e4);
            edges.add(e5);
    
            Network system = new Network(edges,agents,resources,0);
            system.startAllocation();
    
            allutilities[i] = system.totalUtilityAtTheAllocation();
            
            if(system.totalUtilityAtTheAllocation()< minimumUtility)
            {
                minimumUtility = system.totalUtilityAtTheAllocation();
                bestAllocation = system.allocationMatrix;
                bestNetwork = system;
            }
        }
        
        plot2 p = new plot2(allutilities);
        p.setVisible(true);
    
        System.out.println("Best allocation was utility:  " + minimumUtility);
        bestAllocation.matrixPrint();

        
    Graphica graphicaFrame = new Graphica(bestNetwork, minimumUtility);
    graphicaFrame.setVisible(true);

        
    
    }
    /*Ideal is
     * 5  0  5  0  0
     * 0  0  10 0  10
     * 0  0  0  0  5
     */
}