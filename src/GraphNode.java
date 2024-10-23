/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author valen
 */
public class GraphNode {
    String name;
    LinkedList connections;
    LinkedList pedestrianCrossings;
    LinkedList storesThatAccessThisNode;
    boolean hasStore;

    public GraphNode(String name) {
        this.name = name;
        this.connections = new LinkedList();
        this.pedestrianCrossings = new LinkedList();
        this.storesThatAccessThisNode = new LinkedList();
        boolean hasStore = false;
    }
    
    public void addConnection(GraphNode node){
        this.connections.insertar(node);
    }
    
    public void printConnections(){
        System.out.print(name + " -> ");
        connections.imprimirLista(); 
         System.out.println();
    }
    
    public void addPedestrianCrossing(GraphNode node){
        pedestrianCrossings.insertar(node);
    }
    
}

