/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Representa un nodo en un grafo(una parada de metro).
 * Esta clase contiene información sobre las conexiones del nodo, cruces peatonales,
 * y supermercados asociadas a él.
 * 
 * @author valen
 */
public class GraphNode {
    /** El nombre del nodo del grafo (el nombre de la parada de metro). */
    String name;
    
    /** Una lista de conexiones a otros nodos del grafo. */
    LinkedList connections;
    
    /** Una lista de cruces peatonales asociados con este nodo. */
    LinkedList pedestrianCrossings;
    
    /** Una lista de tiendas(supermercados) que acceden a este nodo. */
    LinkedList storesThatAccessThisNode;
    
    /** Indica si este nodo tiene un supermercado asociado. */
    boolean hasStore;
    
    /**
     * Construye un nuevo GraphNode con el nombre especificado.
     *
     * @param name el nombre del nodo del gráfico
     */
    public GraphNode(String name) {
        this.name = name;
        this.connections = new LinkedList();
        this.pedestrianCrossings = new LinkedList();
        this.storesThatAccessThisNode = new LinkedList();
        this.hasStore = false;
    }
    
    /**
     * Agrega una conexión desde este nodo a otro nodo del grafo.
     *
     * @param node el nodo del grafo al que conectarse
     */
    public void addConnection(GraphNode node){
        this.connections.insertar(node);
    }
    
    /**
     * Imprime todas las conexiones de este nodo del grafo a la consola.
     */
    public void printConnections(){
        System.out.print(name + " -> ");
        connections.imprimirLista(); 
         System.out.println();
    }
    
    /**
     * Agrega un cruce peatonal asociado con este nodo del grafo.
     *
     * @param node el nodo del gráfico que representa el paso de peatones
     */
    public void addPedestrianCrossing(GraphNode node){
        pedestrianCrossings.insertar(node);
    }
    
}

