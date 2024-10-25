/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * La clase Graph representa un grafo que puede contener nodos (GraphNode) y conexiones (aristas) 
 * entre ellos. Permite realizar operaciones como agregar nodos, agregar aristas y realizar 
 * búsquedas en profundidad (DFS) y en amplitud (BFS) con límites de radio.
 * 
 * @author valen
 */
public class Graph {
    private LinkedList nodes;
    private int nodeCount;
    
    /**
     * Constructor que inicializa un nuevo grafo vacío.
     */
    public Graph() {
        this.nodes = new LinkedList();
        this.nodeCount = 0;
    }
    
    /**
     * Obtiene la lista de nodos en el grafo.
     * 
     * @return la lista de nodes
     */
    public LinkedList getNodes() {
        return nodes;
    }
    
    /**
     * Obtiene el número de nodos en el grafo.
     * 
     * @return el número de nodos.
     */
    public int getNodeCount() {
        return nodeCount;
    }
    
    /**
     * Agrega un nuevo nodo al grafo con el nombre especificado.
     * 
     * @param name el nombre del nuevo nodo.
     * @return el nodo agregado.
     */
    public GraphNode addNode(String name){
        GraphNode newNode = new GraphNode(name);
        nodes.insertar(newNode);
        nodeCount++;
        return newNode;
    }
    
    /**
     * Busca un nodo en el grafo por su nombre.
     * 
     * @param name el nombre del nodo a buscar.
     * @return el nodo encontrado o null si no existe.
     */
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
    
    /**
     * Agrega una conexión (arista) entre dos nodos.
     * 
     * @param from el nodo de origen.
     * @param to el nodo de destino.
     */
    public void addEdge(GraphNode from, GraphNode to){
        from.addConnection(to);
        to.addConnection(from);
    }
    
     /**
     * Imprime el grafo y sus conexiones.
     */
    public void printGraph(){
        for(int i=0; i<nodeCount; i++){
            nodes.get(i).printConnections();
        }
    }
    
    /**
     * Verifica si un nodo ha sido visitado.
     * 
     * @param visited la lista de nodos visitados.
     * @param node el nodo a verificar.
     * @return true si el nodo ha sido visitado, false de lo contrario.
     */
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
    
    /**
     * Realiza una búsqueda en amplitud (BFS) desde el nodo de inicio.
     * 
     * @param start el nodo desde el cual comenzar la búsqueda.
     */
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
    
    /**
     * Realiza una búsqueda en profundidad (DFS) desde el nodo de inicio.
     * 
     * @param start el nodo desde el cual comenzar la búsqueda.
     */
    public void dfs (GraphNode start){
        LinkedList visited = new LinkedList();
        dfsHelper(start, visited);
    }
    
    
    /**
     * Método auxiliar para la búsqueda en profundidad (DFS).
     * 
     * @param start el nodo desde el cual comenzar la búsqueda.
     * @param visited la lista de nodos visitados.
     */
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
    
    /**
     * Verifica si una cola está vacía.
     * 
     * @param queue la cola a verificar.
     * @return true si la cola está vacía, false de lo contrario.
     */
    private boolean isEmpty(LinkedList queue){
        return queue.head == null;
    }
    
    /**
     * Obtiene el tamaño de una cola.
     * 
     * @param queue la cola cuya longitud se va a obtener.
     * @return el tamaño de la cola.
     */
    private int getQueueSize(LinkedList queue){
        Node current = queue.head;
        int size = 0;
        while(current != null){
            current = current.next;
            size++;
        }
        return size;
    }
    
    /**
     * Recalcula los nodos accesibles dentro del radio especificado.
     * 
     * @param radio el radio dentro del cual buscar nodos accesibles.
     * @param accessibleNodes la lista de nodos accesibles.
     */
    public void recalcularAccessedNodes(int radio, LinkedList accessibleNodes){
        Node current = getNodes().head;
        while(current!=null){
            current.node.storesThatAccessThisNode.eliminarTodos();
            if(current.node.hasStore==true){
                bfsConRadio(current.node, radio, accessibleNodes);
            }
            current = current.next;
        }
    }
    
    /**
     * Realiza una búsqueda en amplitud (BFS) con un límite de radio desde el nodo de inicio.
     * 
     * @param start el nodo desde el cual comenzar la búsqueda.
     * @param radio el radio dentro del cual buscar nodos accesibles.
     * @param accessibleNodes la lista de nodos accesibles.
     */
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
    
    /**
     * Realiza una búsqueda en profundidad (DFS) con un límite de radio desde el nodo de inicio.
     * 
     * @param start el nodo desde el cual comenzar la búsqueda.
     * @param radio el radio dentro del cual buscar nodos accesibles.
     * @param accessibleNodes la lista de nodos accesibles.
     */
    public void dfsConRadio(GraphNode start, int radio, LinkedList accessibleNodes) {
        LinkedList visited = new LinkedList(); // Lista de nodos visitados
        dfsHelperConRadio(start, visited, 0, radio, accessibleNodes); // Llamar al método recursivo
    }
    
    /**
     * Método auxiliar para la búsqueda en profundidad (DFS) con límite de radio.
     * 
     * @param node el nodo actual en la búsqueda.
     * @param visited la lista de nodos visitados.
     * @param currentDepth la profundidad actual en la búsqueda.
     * @param radio el radio dentro del cual buscar nodos accesibles.
     * @param accessibleNodes la lista de nodos accesibles.
     */
    private void dfsHelperConRadio(GraphNode node, LinkedList visited, int currentDepth, int radio, LinkedList accessibleNodes){
        if(currentDepth>radio){
            return;
        }else{
            if(!isVisited(visited, node)){
                visited.insertar(node);
                
                if (!isNodeInAccessibleNodes(accessibleNodes, node)) {
                    accessibleNodes.insertar(node); // Agregar a la lista accesible
                    System.out.println("Nodo insertado: "+node.name);
                }
                //System.out.print(node.name + " ");

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
                            System.out.println("Nodo peatonal: "+node.name);
                        }
                    }
                    crossing = crossing.next;
                }

            }
        }
    }
    
    /**
    * Realiza una búsqueda en profundidad (DFS) desde un nodo de inicio y devuelve una lista de nodos
    * accesibles dentro de un radio especificado para ese mismo nodo de inicio.
    *
    * @param start El nodo de inicio desde el cual se realiza la búsqueda.
    * @param radio El radio máximo dentro del cual se buscan nodos accesibles.
    * @return Una lista de nodos accesibles desde el nodo de inicio.
    */
    public LinkedList dfsUnRadio(GraphNode start, int radio) {
        LinkedList accessibleNodes = new LinkedList(); // Lista de nodos accesibles
        LinkedList visited = new LinkedList(); // Lista de nodos visitados
        dfsHelperConRadio(start, visited, 0, radio, accessibleNodes); // Llamar al método recursivo
        return accessibleNodes; // Devolver la lista de nodos accesibles
    }
    
    /**
    * Método recursivo auxiliar para realizar una búsqueda en profundidad (DFS) desde un nodo dado,
    * explorando nodos dentro de un radio especificado.
    *
    * @param node El nodo actual en la búsqueda.
    * @param visited Lista de nodos que han sido visitados.
    * @param currentDepth La profundidad actual de la búsqueda.
    * @param radio El radio máximo dentro del cual se buscan nodos accesibles.
    * @param accessibleNodes Lista de nodos accesibles encontrados hasta el momento.
    */
    public void dfsHelperUnRadio(GraphNode node, LinkedList visited, int currentDepth, int radio, LinkedList accessibleNodes) {
        if (currentDepth > radio) {
            return;
        } else {
            if (!isVisited(visited, node)) {
                visited.insertar(node);

                // Agregar el nodo accesible
                if (!isNodeInAccessibleNodes(accessibleNodes, node)) {
                    accessibleNodes.insertar(node); // Agregar a la lista accesible
                    System.out.println("Nodo insertado: " + node.name);
                }

                // Recorrer conexiones
                Node connection = node.connections.head;
                while (connection != null) {
                    if (!isVisited(visited, connection.node)) {
                        dfsHelperConRadio(connection.node, visited, currentDepth + 1, radio, accessibleNodes);
                    }
                    connection = connection.next;
                }

                // Recorrer cruces peatonales
                Node crossing = node.pedestrianCrossings.head;
                while (crossing != null) {
                    if (!isVisited(visited, crossing.node)) {
                        visited.insertar(crossing.node);
                        if (!isNodeInAccessibleNodes(accessibleNodes, crossing.node)) {
                            accessibleNodes.insertar(crossing.node); // Agregar a la lista accesible
                            System.out.println("Nodo peatonal: " + crossing.node.name);
                        }
                    }
                    crossing = crossing.next;
                }
            }
        }
    }
    
    
    /**
    * Cuenta la cantidad de nuevos nodos accesibles que no están en la lista de nodos accesibles actual.
    *
    * @param tempAccessedNodes Lista temporal de nodos accedidos.
    * @param accessibleNodes Lista de nodos accesibles actuales.
    * @return La cantidad de nuevos nodos accesibles encontrados.
    */
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
    
    /**
    * Obtiene una lista de nodos que no son accesibles desde la lista de nodos accesibles proporcionada.
    *
    * @param accessibleNodes Lista de nodos que son accesibles.
    * @return Una lista de nodos que no son accesibles.
    */
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
   
    /**
     * Sugerir nodos a visitar basándose en la lista de nodos accesibles.
     * 
     * @param radio la cobertura
     * @param accessibleNodes la lista de nodos accesibles.
     * @return la lista de nodos a sugerir.
     */
    public LinkedList suggestNodesToVisit(int radio, LinkedList accessibleNodes){
        LinkedList suggestedNodes = new LinkedList();
        LinkedList unvisitedNodes = new LinkedList();
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
                suggestedNodes.insertar(bestNodeToVisit);
                System.out.println("Sugerencia: Visitar nodo " + bestNodeToVisit.name);
                dfsConRadio(bestNodeToVisit, radio, accessibleNodes);
            }
        }while(unvisitedNodes.head != null);
        return suggestedNodes;
    }
    /**
    * Agrega un cruce peatonal bidireccional entre dos nodos en el grafo.
    * Esto significa que el cruce se agrega de manera que ambos nodos puedan 
    * acceder entre sí a través del cruce peatonal.
    *
    * @param node1 El primer nodo al que se le agrega el cruce peatonal.
    * @param node2 El segundo nodo al que se le agrega el cruce peatonal.
    */
    public void addPedestrianCrossing(GraphNode node1, GraphNode node2){
        node1.addPedestrianCrossing(node2);
        node2.addPedestrianCrossing(node1);
    }
    
    /**
     * Verifica si un nodo ya está en la lista de nodos accesibles.
     * 
     * @param accessibleNodes la lista de nodos accesibles.
     * @param node el nodo a verificar.
     * @return true si el nodo está en la lista, false de lo contrario.
     */
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
   
   /**
    * Realiza una búsqueda en anchura (BFS) desde un nodo de inicio, explorando nodos dentro de un
    * radio especificado. 
    *
    * @param start El nodo de inicio desde el cual se realiza la búsqueda.
    * @param radio El radio máximo dentro del cual se buscan nodos accesibles.
    */
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
                
                if (!isVisited(visited, current)) { //PROBLEMA
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
    
    /**
    * Realiza una búsqueda en anchura (BFS) desde un nodo de inicio y devuelve una lista de nodos
    * alcanzables dentro de un radio especificado. Este método no marca el nodo de inicio como que tiene una tienda.
    *
    * @param start El nodo de inicio desde el cual se realiza la búsqueda.
    * @param radio El radio máximo dentro del cual se buscan nodos alcanzables.
    * @return Una lista de nodos alcanzables desde el nodo de inicio.
    */
    public LinkedList bfsConRadio(GraphNode start, int radio) {
        LinkedList queue = new LinkedList();
        LinkedList reachableNodes = new LinkedList(); // Lista para almacenar nodos alcanzables

        queue.insertar(start);

        int currentLevel = 0;
        while (!isEmpty(queue) && currentLevel <= radio) {
            int levelSize = getQueueSize(queue);

            for (int i = 0; i < levelSize; i++) {
                GraphNode current = queue.head.node;

                // Agregar el nodo actual a la lista de nodos alcanzables
                reachableNodes.insertar(current);
                System.out.print(current.name + " "); // Mostrar el nodo accesible (opcional)

                // Quitar el nodo de la cola
                queue.head = queue.head.next;

                // Agregar conexiones a la cola
                Node connection = current.connections.head;
                while (connection != null) {
                    if (!isVisited(reachableNodes, connection.node)) { // Verifica si el nodo ya fue agregado
                        queue.insertar(connection.node);
                    }
                    connection = connection.next;
                }

                // Agregar cruces peatonales a la lista de nodos alcanzables
                Node crossing = current.pedestrianCrossings.head;
                while (crossing != null) {
                    if (!isVisited(reachableNodes, crossing.node)) {
                        reachableNodes.insertar(crossing.node);
                    }
                    crossing = crossing.next;
                }
            }
            currentLevel++;
        }

        return reachableNodes; // Retorna la lista de nodos alcanzables
    }

   
   /**
    * Obtiene una lista de nodos accesibles que tienen acceso a alguna tienda.
    *
    * @return Una lista de nodos que tienen al menos una tienda accesible.
    */
    public LinkedList getAccessibleNodes(){
        Node current = this.nodes.head;
        LinkedList nodosAccesibles = new LinkedList();
        while(current != null){
            Node current2 = current.node.storesThatAccessThisNode.head;
            while(current2!=null){ //hay alguna sucursal que tiene acceso a esta parada
                //System.out.println("aqui estoy: "+current2.node.name);
                if(nodosAccesibles.findNodeByName(current.node.name)==null){
                     System.out.println("aqui estoy: "+current.node.name);
                     nodosAccesibles.insertar(current.node);
                }
                current2 = current2.next;

                //System.out.println("Accedido: "+current.node.name);
            }
            current = current.next;
        } 
        return nodosAccesibles;
    }
    
    /**
    * Elimina una tienda asociada a un nodo específico y actualiza las referencias en otros nodos.
    *
    * @param node El nodo del cual se desea eliminar la tienda.
    */
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
    
    /**
    * Elimina un nodo de una lista enlazada dada, basado en su nombre.
    *
    * @param head La cabeza de la lista de nodos.
    * @param name El nombre del nodo que se desea eliminar.
    * @return La nueva cabeza de la lista actualizada.
    */
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
    
    
    /**
    * Elimina todos los nodos y conexiones en el grafo, limpiando completamente la estructura.
    */
    public void eliminarTodo() {
        Node current = nodes.head;  // Recorrer la lista de nodos
        while (current != null) {
            GraphNode graphNode = current.node;
            
            // Limpiar todas las conexiones del nodo actual
            graphNode.connections.eliminarTodos(); // Eliminar todas las conexiones
            graphNode.pedestrianCrossings.eliminarTodos(); // Eliminar todos los cruces peatonales
            graphNode.storesThatAccessThisNode.eliminarTodos(); // Eliminar todas las tiendas que acceden a este nodo
            
            current = current.next; // Pasar al siguiente nodo
        }
        // Limpiar la lista de nodos
        nodes.eliminarTodos();
        nodeCount = 0;
    }
    
   
}

