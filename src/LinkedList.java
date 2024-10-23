/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author valen
 */
public class LinkedList {
    Node head;

    public LinkedList() {
        this.head = null;
    }
    
    public void insertar(GraphNode node){
        Node newNode = new Node(node);
        if (head == null){
            head = newNode;
        }else{
            Node current = head;
            while(current.next!=null){
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    public void imprimirLista(){
        Node current = head;
        while(current!=null){
            System.out.print(current.node.name + " ");
            current = current.next;
        }
    }
    
    public GraphNode get(int index){
        Node current = head;
        int i = 0;
        while(i<index){
            current = current.next;
            i++;
        }
        return current.node;
    }
    
    
    
    public void eliminarTienda(GraphNode node, Node head) {
        node.hasStore = false;
        Node current = head;
        while (current != null) {
            Node current2 = current.node.storesThatAccessThisNode.head;
            while (current2 != null) {
                if (current2.node.name.equals(node.name)) {
                    current.node.storesThatAccessThisNode.head = eliminar(current.node.storesThatAccessThisNode.head, node.name); // Actualiza la cabeza
                }
                current2 = current2.next;
            }
            current = current.next;
        }
    }
    
    public Node eliminar(Node head, String name) {
        //System.out.println("HOLA");
        if (head == null) {
            return null; // Si la lista está vacía, solo retorna null
        }

        // Si el nodo a eliminar es la cabeza
        if (head.node.name.equals(name)) {
            return head.next; // Devuelve el siguiente nodo como nueva cabeza
        }

        Node current = head;
        Node previous = null;

        // Recorrer la lista hasta encontrar el nodo
        while (current != null && !current.node.name.equals(name)) {
            previous = current; // Guardar el nodo anterior
            current = current.next; // Avanzar al siguiente nodo
        }

        // Si se encontró el nodo, desvincularlo
        if (current != null) {
            previous.next = current.next; // Desvincular el nodo actual
        } else {
            System.out.println("No se encontró el nodo: " + name);
        }

        return head; // Retorna la cabeza actualizada
    }
    
    public void eliminarTodos() {
        while (head != null) {
            eliminarPrimero();  // Eliminar el primer elemento hasta que la lista esté vacía
        }
    }
    
    public void eliminarPrimero() {
        if (head != null) {
            head = head.next;  // Desplazar la cabeza al siguiente nodo
        } else {
            System.out.println("La lista ya está vacía.");
        }
    }
}

