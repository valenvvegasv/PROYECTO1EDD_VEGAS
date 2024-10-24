
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author valen
 */
public class GraphVisualizer {
private org.graphstream.graph.Graph graph;
    
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
    }
    
    public void visualizeGraph(){
        graph.setAttribute("ui.node.spacing", 7500);
        graph.display().enableAutoLayout();
    }
    
    public void nodoSeleccionado(Graph grafo, String respuesta){
        
        new Thread(() -> {
            graph.getNode(respuesta).setAttribute("ui.style", "size: 20px; fill-color: green;");
        }).start();
        
    }
    
    public void nodoDeseleccionado(Graph grafo, String respuesta){
        
        new Thread(() -> {
            graph.getNode(respuesta).setAttribute("ui.style", "size: 20px; fill-color: blue;");
        }).start();
        
    }
    
    public void markAccessibleNodes(Graph grafo, LinkedList accessibleNodes){
        
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                Node current = accessibleNodes.head;
                while(current!=null){
                    graph.getNode(current.node.name).setAttribute("ui.style", "size: 15px; fill-color: purple;");
                    current = current.next;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }).start();  
    }
    
    public void resetColor(Graph grafo){
        new Thread(() -> {
            Node current = grafo.getNodes().head;
            while(current!=null){
                graph.getNode(current.node.name).setAttribute("ui.style", "size: 20px; fill-color: blue;");
                current = current.next;
            }
        }).start();      
    }
    
    
       
}
