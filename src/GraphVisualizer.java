import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Esta clase proporciona funcionalidades para visualizar un gráfico usando
 * GraphStream, incluida la creación de nodos, bordes y la actualización
 * dinámica del gráfico. También admite marcar y restablecer nodos según las
 * interacciones del usuario o los recorridos BFS/DFS.
 *
 * @author valen
 */
public class GraphVisualizer {
    private org.graphstream.graph.Graph graph;
    private Viewer viewer;
   
    /**
     * Constructor predeterminado para la clase GraphVisualizer.
     * Este constructor inicializa la instancia del visualizador y el grafo.
     */
    public GraphVisualizer() {
        // Inicialización del visualizador y del gráfico
        graph = new SingleGraph("Graph");
        Viewer viewer1 = viewer;
    }
    /**
     * Construye y visualiza el grafo a partir del objeto Graph proporcionado.
     * El método crea nodos, conexiones y cruces peatonales, y 
     * asigna estilos apropiados a cada uno.
     * 
     * @param grafo el objeto Graph personalizado que contiene los nodos y bordes que se mostrarán.
     */
    public void construirGraph(Graph grafo){
        graph = new SingleGraph("Grafo");
        
        Node current = grafo.getNodes().head;
        
        while(current != null){
            org.graphstream.graph.Node graphNode = graph.addNode(current.node.name);
            graphNode.setAttribute("ui.label", current.node.name);
            //graphNode.setAttribute("ui.style", "text-alignment: under;");
            graphNode.setAttribute("ui.style", "size: 10px; fill-color: #B7E0F9;");
            graphNode.setAttribute("ui.label.size", 0.05);
            graphNode.setAttribute("ui.label.align", "left");
            //graphNode.setAttribute("ui.label.align", "center");
            //System.out.println("Procesandoo: "+current.node.name);
            current = current.next;
        }
        
        current = grafo.getNodes().head;
        while(current!=null){
        Node connection = current.node.connections.head;
            while(connection!=null){
                //System.out.println("Procesandoo2: "+connection.node.name);
                if (graph.getEdge(current.node.name + "-" + connection.node.name) == null &&
                    graph.getEdge(connection.node.name + "-" + current.node.name) == null) {
                    graph.addEdge(current.node.name + "-" + connection.node.name, current.node.name, connection.node.name);  // Añadir arista
                    //graph.getEdge(current.node.name + "-" + connection.node.name).setAttribute("ui.style", "shape: cubic-curve;");
                }
                connection = connection.next;
            }
            current = current.next;
        }
        
        //ESTA PARTE ES PARA CAMBIARLE EL COLOR A LAS ARISTAS QUE CONECTAN A LOS CRUCES PEATONALES
        current = grafo.getNodes().head;
        while (current != null) {
            Node crossing = current.node.pedestrianCrossings.head;
            while (crossing != null) {
                // Verificar si la arista ya existe y cambiar su color
                Edge edge = graph.getEdge(current.node.name + "-" + crossing.node.name);
                if (edge == null) {
                    edge = graph.getEdge(crossing.node.name + "-" + current.node.name);
                }
                if (edge != null) {
                    // Cambiar el color de la arista para los cruces peatonales
                    edge.setAttribute("ui.style", "fill-color: red;");  // Cambia 'red' por el color que prefieras
                    
                }
                crossing = crossing.next;
            }
            current = current.next;
        }
        graph.setAttribute("ui.node.spacing", 7500);
        viewer = graph.display();
    }
    
    /**
     * Muestra el grafo actual con el diseño automático habilitado.
     * Este método debe llamarse después de construir el grafo para visualizarlo.
     */
    public void visualizeGraph(){
        
        graph.setAttribute("ui.node.spacing", 7500);
        graph.display().enableAutoLayout();
    }
    
    /**
     * Marca un nodo como seleccionado cambiando su tamaño y color.
     * 
     * @param grafo el objeto Graph personalizado donde se encuentra el nodo.
     * @param respuesta el nombre del nodo a seleccionar.
     */
    public void nodoSeleccionado(Graph grafo, String respuesta){
        
        new Thread(() -> {
            graph.getNode(respuesta).setAttribute("ui.style", "size: 20px; fill-color: green;");
        }).start();
        
    }
    
    /**
     * Marca un nodo como no seleccionado restableciendo su tamaño y color.
     * 
     * @param grafo el objeto Graph personalizado donde se encuentra el nodo.
     * @param respuesta el nombre del nodo a deseleccionar.
     */
    public void nodoDeseleccionado(Graph grafo, String respuesta){
        
        new Thread(() -> {
            graph.getNode(respuesta).setAttribute("ui.style", "size: 20px; fill-color: blue;");
        }).start();
        
    }
    
    /**
     * Marca los nodos a los que se puede acceder mediante un recorrido BFS/DFS como "visitados"
     * cambiando su estilo en la visualización.
     * 
     * @param grafo el objeto Graph personalizado donde se encuentran los nodos.
     * @param accessibleNodes la lista de nodos accesibles para marcar en el grafo.
     */
    public void markAccessibleNodes(Graph grafo, LinkedList accessibleNodes){
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                Node current = accessibleNodes.head;
                while(current!=null){
                    System.out.println("HOLA: "+current.node.name);
                    graph.getNode(current.node.name).setAttribute("ui.style", "size: 15px; fill-color: purple;");
                    current = current.next;
                }
                resetearColores(grafo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();  
    }
    
    /**
     * Restablece el color y el tamaño de un nodo específico al estado predeterminado.
     * 
     * @param node el nodo cuyo color y tamaño se restablecerán.
     */
    public void resetColor(GraphNode node){
        new Thread(() -> {
            graph.getNode(node.name).setAttribute("ui.style", "size: 10px; fill-color: #B7E0F9;");
        }).start();      
    }
    
    /**
     * Agrega una nueva arista entre dos nodos en la visualización del gráfico.
     * 
     * @param desde el nombre del nodo de inicio.
     * @param hacia el nombre del nodo final.
     */
    public void ingresarConexion(String desde, String hacia){
        new Thread(() -> {
            if (graph.getEdge(desde + "-" + hacia) == null &&
                    graph.getEdge(desde + "-" + hacia) == null) {
                    graph.addEdge(desde + "-" + hacia, desde, hacia);  // Añadir arista
                    //graph.getEdge(current.node.name + "-" + connection.node.name).setAttribute("ui.style", "shape: cubic-curve;");
            }
        }).start(); 
    }
    
    /**
     * Marca un nodo como si tuviera una tienda, cambiando su color y tamaño.
     * 
     * @param node el nodo que se marcará como si tuviera una tienda.
     */
    public void tieneSucursal(GraphNode node){
        new Thread(() -> {
            graph.getNode(node.name).setAttribute("ui.style", "size: 15px; fill-color: yellow;");            
        }).start();     
    }
    
    /**
     * Restablece el color de todos los nodos en función de si tienen tienda o no.
     * Los nodos con tienda se marcan de forma diferente.
     * 
     * @param grafo el objeto Graph personalizado donde se encuentran los nodos.
     */
    public void resetearColores(Graph grafo){
        new Thread(() -> {
            try {
                Thread.sleep(7000);
                Node current = grafo.getNodes().head;
                while(current!=null){
                    if(current.node.hasStore == true){
                        //System.out.println("hola: "+current.node.name);
                        tieneSucursal(current.node);
                    }else{
                        System.out.println("hola: "+current.node.name);
                        resetColor(current.node);
                    }
                    current = current.next;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }).start();  
    }
    
    /**
     * Marca los nodos sugeridos en el grafo según algún algoritmo de recomendación.
     * Cambia el estilo del nodo para indicar que ha sido sugerido.
     * 
     * @param suggestedNodes la lista de nodos sugeridos.
     * @param grafo el objeto Graph personalizado donde se encuentran los nodos.
     */
    public void nodosSugeridos(LinkedList suggestedNodes, Graph grafo){
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                Node current = suggestedNodes.head;
                while(current!=null){
                    graph.getNode(current.node.name).setAttribute("ui.style", "size: 10px; fill-color: red;");
                    current = current.next;
                }
                resetearColores(grafo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }).start();  
    }
    
    /**
     * Borra la visualización del gráfico y cierra la ventana de visualizacion.
     * Este método elimina todos los nodos y aristas del grafo.
     */
    public void eliminarTodo(){
        graph.clear();
        viewer.close();
    }
}
