import java.util.ArrayList;

import entities.*;
public class Controller 
{

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

    //Make network allocate
    system.startAllocation();


    //output final allocation and utility

    System.out.println(system.getAllocationMatrix());
}
    
}


/*Example 3. Consider a bipartite graph with X = 4 users and Y = 4 re-
sources. Each user has αx = 30 for all x ∈ X and the resources have βy1 =

βy3 = 30, βy2 = 45 and βy4 = 15. The set of the edges is given by E =

{(x1, y2),(x2, y1),(x2, y3),(x3, y2),(x3, y4),(x4, y3)} (Figure 1). Allocation con-
dition (1) is satisfied. */