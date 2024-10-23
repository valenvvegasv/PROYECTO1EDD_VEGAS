/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author valen
 */
public class Node {
    public GraphNode node; //nombre de la parada
    Node next;

    public Node(GraphNode node) {
        this.node = node;
        this.next = null;
    }
}
