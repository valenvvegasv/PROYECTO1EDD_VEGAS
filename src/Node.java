/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Representa un nodo en una lista enlazada que contiene una referencia a un {@link GraphNode}.
 * Esta clase se utiliza para crear una estructura de lista enlazada para administrar los nodos del grafo.
 *
 * @author valen
 */
public class Node {
    /** 
     * El objeto {@link GraphNode} que representa este nodo, 
     * que contiene información sobre un nodo del grafo específico (por ejemplo, una parada de metro).
     */
    public GraphNode node; //nombre de la parada
    
    /** 
     * El siguiente nodo en la lista enlazada.
     * Este campo se utiliza para vincular al nodo siguiente en la lista.
     */
    Node next;

    /**
     * Construye un nuevo Nodo con el {@link GraphNode} especificado.
     * 
     * @param node el {@link GraphNode} que se almacenará en este nodo
     */
    public Node(GraphNode node) {
        this.node = node;
        this.next = null;
    }
}
