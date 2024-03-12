import entities.Agent;
import entities.Edge;
import entities.Resource;
import java.util.*;

public class Tester 
{

    /*In this class I will test the different methods made for the base of the program */



    public static void main(String[] args) 
    {
        //create Agents
    Agent a1 = new Agent(60,30);
    Agent a2 = new Agent(60,30);
    Agent a3 = new Agent(60,30);
    Agent a4 = new Agent(60,30);

    //create Resources

    Resource r1 = new Resource(1,30);
    Resource r2 = new Resource(1,45);
    Resource r3 = new Resource(1,30);
    Resource r4 = new Resource(1,15);
    //createEdges

    Edge e1 = new Edge(a1,r2);
    Edge e2 = new Edge(a2,r1);
    Edge e3 = new Edge(a2,r3);
    Edge e4 = new Edge(a3,r2);
    Edge e5 = new Edge(a3,r4);
    Edge e6 = new Edge(a4,r3);

    //createNetwork
    ArrayList<Agent> agents = new ArrayList<Agent>();
    agents.add(a1);agents.add(a2);agents.add(a3);agents.add(a4);
    ArrayList<Resource> resources = new ArrayList<Resource>();
    resources.add(r1);resources.add(r2);resources.add(r3);resources.add(r4);
    ArrayList<Edge> edges = new ArrayList<Edge>();
    edges.add(e1);edges.add(e2);edges.add(e3);edges.add(e4);edges.add(e5);edges.add(e6);

    Network system = new Network(edges,agents,resources);

    System.out.println( "Is an agent equal to another (should be false)   " + a1.equals(a2));
    System.out.println( "Is an agent equal to another (should be true)   " + a1.equals(a1));

    System.out.println("Neigbors of a1, should be r2 only" + a1.neighborresources + "actual resources  " + r2);

    System.out.println("ADJACENCY");
    system.adjacencyMatrix.matrixPrint();
    System.out.println("ALLOCATION CURRENT");
    system.allocationMatrix.matrixPrint();
    System.out.println(system.allocationMatrix.sumRow(0));
    System.out.println(a1.alpha);
    System.out.println(system.isAllocation());

    System.out.println("Are a1 and r2 connects? " + system.isItConnected(a1,r2));
   




    }
    
}
