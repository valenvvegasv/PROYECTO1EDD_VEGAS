/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author valen
 */
public class Graph {
    private LinkedList nodes;
    private int nodeCount;

    public Graph() {
        this.nodes = new LinkedList();
        this.nodeCount = 0;
    }
    
    /**
     * @return the nodes
     */
    public LinkedList getNodes() {
        return nodes;
    }
    
    /**
     * @return the nodeCount
     */
    public int getNodeCount() {
        return nodeCount;
    }
    
    public GraphNode addNode(String name){
        GraphNode newNode = new GraphNode(name);
        nodes.insertar(newNode);
        nodeCount++;
        return newNode;
    }
    
    public GraphNode findNodeByName(String name) {
        Node current = getNodes().head;
        while (current != null) {
            if (current.node.name.equals(name)) {
                return current.node;
            }
            current = current.next;
        }
        return null; 
    }
    public void addEdge(GraphNode from, GraphNode to){
        from.addConnection(to);
        to.addConnection(from);
    }
    
    public void printGraph(){
        for(int i=0; i<nodeCount; i++){
            nodes.get(i).printConnections();
        }
    }
    
    private boolean isVisited(LinkedList visited, GraphNode node){
        Node current = visited.head;
        while(current !=null){
            if(current.node == node){
                return true;
            }else{
                current = current.next;
            }
        }
        return false;
    }
    
    public void bfs(GraphNode start){
        LinkedList queue = new LinkedList();
        LinkedList visited = new LinkedList();
        
        queue.insertar(start);
        while(queue.head != null){
            GraphNode current = queue.head.node;
            visited.insertar(current);
            queue.head = queue.head.next;
            System.out.print(current.name + " ");
            
            Node connection = current.connections.head;
            while(connection!=null){
                if(!isVisited(visited, connection.node)){
                    queue.insertar(connection.node);
                }
                connection = connection.next;
            }
        }
    }
    
    public void dfs (GraphNode start){
        LinkedList visited = new LinkedList();
        dfsHelper(start, visited);
    }
    
    private void dfsHelper(GraphNode start, LinkedList visited){
        visited.insertar(start);
        System.out.print(start.name + " ");
        
        Node connection = start.connections.head;
        while(connection!=null){
            if(!isVisited(visited, connection.node)){
                dfsHelper(connection.node, visited);
            }
            connection = connection.next;
        }
    }
    
    private boolean isEmpty(LinkedList queue){
        return queue.head == null;
    }
    
    private int getQueueSize(LinkedList queue){
        Node current = queue.head;
        int size = 0;
        while(current != null){
            current = current.next;
            size++;
        }
        return size;
    }
    
    public void bfsConRadio(GraphNode start, int radio, LinkedList accessibleNodes){
        LinkedList queue = new LinkedList();
        LinkedList visited = new LinkedList();
        queue.insertar(start);
        
        int currentLevel = 0;
        while(!isEmpty(queue) && currentLevel<=radio){
            int LevelSize = getQueueSize(queue);
            
            for(int i=0; i<LevelSize; i++){
                GraphNode current = queue.head.node;
                if (!isVisited(visited, current)) {
                    visited.insertar(current); // Marcarlo como visitado
                    if (!isNodeInAccessibleNodes(accessibleNodes, current)) {
                        accessibleNodes.insertar(current); // Agregar a la lista accesible
                    }
                    // Agregar a nodos accesibles
                    System.out.print(current.name + " ");
                }
                queue.head = queue.head.next; //quito el nodo de la cola
                
                Node connection = current.connections.head;
                while(connection != null){
                    if(!isVisited(visited, connection.node)){
                        queue.insertar(connection.node);
                    }
                    connection = connection.next;
                }
                
                Node crossing = current.pedestrianCrossings.head;
                while(crossing != null){
                    //System.out.println("Cruce: " + crossing.node.name);
                    if(!isVisited(visited, crossing.node)){
                        visited.insertar(crossing.node); 
                        if (!isNodeInAccessibleNodes(accessibleNodes, crossing.node)) {
                            accessibleNodes.insertar(crossing.node); // Agregar a la lista accesible
                        }
                    }
                    crossing = crossing.next;
                }
            }
            currentLevel++;
        }
    }
    
    public void dfsConRadio(GraphNode start, int radio, LinkedList accessibleNodes) {
        LinkedList visited = new LinkedList(); // Lista de nodos visitados
        dfsHelperConRadio(start, visited, 0, radio, accessibleNodes); // Llamar al método recursivo
    }
    
    private void dfsHelperConRadio(GraphNode node, LinkedList visited, int currentDepth, int radio, LinkedList accessibleNodes){
        if(currentDepth>radio){
            return;
        }else{
            if(!isVisited(visited, node)){
                visited.insertar(node);
                
                if (!isNodeInAccessibleNodes(accessibleNodes, node)) {
                    accessibleNodes.insertar(node); // Agregar a la lista accesible
                }
                System.out.print(node.name + " ");

                Node connection = node.connections.head;
                while(connection!=null){
                    if(!isVisited(visited, connection.node)){
                        dfsHelperConRadio(connection.node, visited, currentDepth+1, radio, accessibleNodes);
                    }
                    connection = connection.next;
                }

                Node crossing = node.pedestrianCrossings.head;
                while(crossing != null){
                    //System.out.println("Cruce: " + crossing.node.name);
                    if(!isVisited(visited, crossing.node)){
                        visited.insertar(crossing.node); 
                        if (!isNodeInAccessibleNodes(accessibleNodes, crossing.node)) {
                            accessibleNodes.insertar(crossing.node); // Agregar a la lista accesible
                        }
                    }
                    crossing = crossing.next;
                }

            }
        }
    }
    
    public void printNonAccessibleNodes(LinkedList accessibleNodes){
        Node current = nodes.head;
        while(current != null){
            if(!isVisited(accessibleNodes, current.node)){
                System.out.print(current.node.name + " ");
            }
            current = current.next;
        }
        
    }
    
    public int getCountOfNewAccessibleNodes(LinkedList tempAccessedNodes, LinkedList accessibleNodes){
        int size = 0;
        Node current = tempAccessedNodes.head;
        while(current != null){
            if(!isVisited(accessibleNodes, current.node)){
                size++;
            }
            current = current.next;
        }
        return size;
    }
    
    public LinkedList getNonAccessible(LinkedList accessibleNodes){
        LinkedList unvisitedNodes = new LinkedList();
        Node current = nodes.head;
        while(current!=null){
            if(!isVisited(accessibleNodes, current.node)){
                unvisitedNodes.insertar(current.node);
            }
            current = current.next;
        }
        return unvisitedNodes;
    }
    
   public void suggestNodesToVisit(int radio, LinkedList accessibleNodes){
       LinkedList unvisitedNodes;
       do{
           unvisitedNodes = getNonAccessible(accessibleNodes);
           if(unvisitedNodes.head == null){
               break;
           }
           GraphNode bestNodeToVisit = null;
           int maxCount = 0;
           Node current = unvisitedNodes.head;
           while(current != null){
               LinkedList tempAccessedNodes = new LinkedList();
               dfsConRadio(current.node, radio, tempAccessedNodes);
               int count = getCountOfNewAccessibleNodes(tempAccessedNodes, accessibleNodes);
               if(count>maxCount){
                   maxCount = count;
                   bestNodeToVisit = current.node;
               }
               current = current.next; 
           }
           if(bestNodeToVisit != null){
               System.out.println("Sugerencia: Visitar nodo " + bestNodeToVisit.name);
               dfsConRadio(bestNodeToVisit, radio, accessibleNodes);
           }
       }while(unvisitedNodes.head != null);
   }
   
   public void addPedestrianCrossing(GraphNode node1, GraphNode node2){
       node1.addPedestrianCrossing(node2);
       node2.addPedestrianCrossing(node1);
   }
   
   private boolean isNodeInAccessibleNodes(LinkedList accessibleNodes, GraphNode node){
       Node current = accessibleNodes.head;
       while(current!=null){
           if(current.node == node){
               return true;
           }
           current = current.next;
       }
       return false;
   }
   //MODIFICACION PARA LA ELIMINACION DE UNA SUCURSAL
   public void bfsConRadio2 (GraphNode start, int radio){
        LinkedList queue = new LinkedList();
        LinkedList visited = new LinkedList();
        start.hasStore = true;
        queue.insertar(start);
        
        int currentLevel = 0;
        while(!isEmpty(queue) && currentLevel<=radio){
            int LevelSize = getQueueSize(queue);
            
            for(int i=0; i<LevelSize; i++){
                GraphNode current = queue.head.node;
                if (!isVisited(current.storesThatAccessThisNode, current)){
                        current.storesThatAccessThisNode.insertar(start);
                }
                
                if (!isVisited(visited, current)) {
                    visited.insertar(current); // Marcarlo como visitado
                    // Agregar a nodos accesibles
                    System.out.print(current.name + " ");
                }
                queue.head = queue.head.next; //quito el nodo de la cola
                
                Node connection = current.connections.head;
                while(connection != null){ //lo add a la cola solo si no lo he visitado
                    if(!isVisited(visited, connection.node)){
                        queue.insertar(connection.node);
                    }
                    connection = connection.next;
                }
                
                Node crossing = current.pedestrianCrossings.head;
                while(crossing != null){
                    if (!isVisited(crossing.node.storesThatAccessThisNode, crossing.node)){
                        crossing.node.storesThatAccessThisNode.insertar(start);
                    }
                    //System.out.println("Cruce: " + crossing.node.name);
                    crossing = crossing.next;
                }
            }
            currentLevel++;
        }
    }
   
   public LinkedList getAccessibleNodes(){
       Node current = this.nodes.head;
       LinkedList nodosAccesibles = new LinkedList();
       while(current != null){
           Node current2 = current.node.storesThatAccessThisNode.head;
           if(current2!=null){ //hay alguna sucursal que tiene acceso a esta parada
               nodosAccesibles.insertar(current.node);
               System.out.println("Accedido: "+current.node.name);
           }
           current = current.next;
       } 
       return nodosAccesibles;
   }
   
    public void deleteStore(GraphNode node) {
        node.hasStore = false;
        Node current = nodes.head;
        while (current != null) {
            Node current2 = current.node.storesThatAccessThisNode.head;
            while (current2 != null) {
                if (current2.node.name.equals(node.name)) {
                    current.node.storesThatAccessThisNode.head = delete(current.node.storesThatAccessThisNode.head, node.name); // Actualiza la cabeza
                }
                current2 = current2.next;
            }
            current = current.next;
        }
    }
    
    public Node delete(Node head, String name) {
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
   
   
}

