/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Una implementación de lista simplemente enlazada personalizada para almacenar nodos de un grafo.
 * Cada nodo de la lista está representado por un objeto {@link Node} que almacena un {@link GraphNode}.
 * La lista permite la inserción, eliminación y recuperación de nodos.
 * 
 * @author valen
 */
public class LinkedList {
    Node head;
    
    /**
     * Crea una lista enlazada vacía donde la cabeza (primer elemento) es nulo.
     */
    public LinkedList() {
        this.head = null;
    }
    
    /**
     * Inserta un nuevo {@link GraphNode} al final de la lista vinculada.
     * 
     * @param node el {@link GraphNode} que se agregará a la lista
     */
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
    
    /**
     * Imprime los nombres de todos los nodos en la lista vinculada.
     * El nombre de cada nodo se imprime en orden de aparición en la lista.
     */
    public void imprimirLista(){
        Node current = head;
        while(current!=null){
            System.out.print(current.node.name + " ");
            current = current.next;
        }
    }
    
    /**
     * Recupera el {@link GraphNode} en el índice especificado en la lista enlazada.
     * 
     * @param index la posición del nodo a recuperar (comenzando en 0)
     * @return el {@link GraphNode} en el índice especificado
     * @throws IndexOutOfBoundsException si el índice es negativo o excede los límites de la lista
     */
    public GraphNode get(int index){
        if (index < 0) {
            throw new IndexOutOfBoundsException("El índice no puede ser negativo.");
        }   
        Node current = head;
        int i = 0;
        while(i<index && current.next != null){
            current = current.next;
            i++;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites de la lista.");
        }
        return current.node;
    }
    
    
    /**
     * Elimina una tienda(supermercado)del {@link GraphNode} especificado y actualiza la lista de tiendas
     * que tienen acceso a otros nodos. 
     * 
     * @param node el {@link GraphNode} que representa la tienda a eliminar
     * @param head el primer nodo de la lista enlazada para recorrer y actualizar
     */
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
    
    /**
     * Elimina el nodo con el nombre especificado de la lista vinculada.
     * 
     * @param head el primer elemento de la lista
     * @param name el nombre del nodo a eliminar
     * @return el primer elemento actualizado de la lista después de la eliminación
     */
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
    
    /**
     * Elimina todos los nodos de la lista enlazada eliminando continuamente el nodo cabeza.
     */
    public void eliminarTodos() {
        while (head != null) {
            eliminarPrimero();  // Eliminar el primer elemento hasta que la lista esté vacía
        }
    }
    
    /**
     * Elimina el primer nodo de la lista enlazada.
     * Si la lista está vacía imprime un mensaje indicándolo.
     */
    public void eliminarPrimero() {
        if (head != null) {
            head = head.next;  // Desplazar la cabeza al siguiente nodo
        } else {
            System.out.println("La lista ya está vacía.");
        }
    }
    
    /**
     * Encuentra y devuelve un {@link GraphNode} por su nombre.
     * 
     * @param name el nombre del nodo a buscar
     * @return el {@link GraphNode} si se encuentra, o nulo si el nodo no existe
     */
    public GraphNode findNodeByName(String name) {
        Node current = head;
        while (current != null) {
            if (current.node.name.equals(name)) {
                return current.node;
            }
            current = current.next;
        }
        return null; // Nodo no encontrado
    }
}

