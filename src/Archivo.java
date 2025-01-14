import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Esta clase maneja operaciones de archivos relacionadas con la carga de grafos, particularmente
 * cargar grafos desde archivos JSON. Proporciona métodos para seleccionar un archivo. 
 * a través de un selector de archivos y analizando el contenido para construir un grafo.
 * 
 * @author valen
 */
public class Archivo {
    /** Nombre del grafo (si aplica). */
    private String nombre;
    
    /**
     * Carga un grafo desde un archivo JSON seleccionado por el usuario a través de un selector de archivos.
     *
     * @param graph el objeto Graph en el que cargar datos
     * @return el objeto Graph cargado
     */
    public Graph cargarGrafo(Graph graph){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo JSON");
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            //System.out.println(fileToOpen.getName().toLowerCase().endsWith(".json"));
            if (!fileToOpen.getName().toLowerCase().endsWith(".json")){
                //System.out.println(fileToOpen.getName().toLowerCase());
                return graph;
            }else{
                try {
                    loadGraphFromJson(fileToOpen, graph);
                    //graph.printGraph(); // Imprimir el grafo para verificar
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return graph;
    }
    
    /**
     * Lee el contenido de un archivo JSON y carga los datos del gráfico.
     *
     * @param file el archivo JSON para leer
     * @param graph el objeto Graph para completar con datos
     * @return el objeto Graph actualizado
     * @throws IOException si ocurre un error durante la lectura del archivo
     */
    public Graph loadGraphFromJson(File file, Graph graph) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();

        // Parsear el JSON manualmente
        String jsonString = jsonBuilder.toString();
        graph = parseJson(jsonString, graph);
        return graph;
        
    }
    
    /**
     * Analiza la cadena JSON y completa la estructura del grafo.
     *
     * @param json la cadena JSON que representa los datos del grafo
     * @param graph el objeto Graph a completar
     * @return el objeto Graph actualizado
     */
    private Graph parseJson(String json, Graph graph) {
        // Eliminar caracteres innecesarios
        json = json.replace("{", "\n").replace("}", "\n")
               .replace("[", "\n").replace("]", "\n]");
        // Añadir salto de línea después de cada coma
        json = json.replaceAll(",\\s*", "\n");
        // Dividir el JSON en líneas
        String[] lines = json.split("\n");
        
        String currentLine = null;
        GraphNode lastNode = null; // Variable para mantener la última parada procesada
        boolean isInLineArray = false;
        
        int i=0;
        for (String line : lines) {
            i++;
            line = line.trim();
            //System.out.println(line);
            if(i==2){
                nombre = line;
            }
            if (line.isEmpty()) {
                continue;
            }
            if(line.endsWith(":")){
                isInLineArray = true;
                continue;
            }
            
            // Si encontramos un cierre de arreglo
            if (line.endsWith("]")) {
                isInLineArray = false; // Salir del arreglo
                lastNode = null;
                continue; // Saltar esta línea
            }
            if(isInLineArray){
                //System.out.println(line);
                if(line.contains(":")){
                    // Procesar conexión peatonal
                    //System.out.println("stoooooop1 "+ line);
                    String[] parts = line.split(":");
                    String stop1 = parts[0].replace("\"", "").trim();
                    
                    
                    //System.out.println("stop1 "+ stop1);
                    String stop2 = parts[1].replace("\"", "").trim();
                    
                    //stop2 = stop2.substring(0, Math.min(stop1.length(), 10));
                    //System.out.println("stop2 "+ stop2);
                    
                    // Asegurarse de que ambos nodos existan
                    GraphNode node1 = graph.findNodeByName(stop1);
                    if (node1 == null) {
                        node1 = graph.addNode(stop1);
                    }
                    GraphNode node2 = graph.findNodeByName(stop2);
                    if (node2 == null) {
                        node2 = graph.addNode(stop2);
                    }

                    // Agregar el cruce peatonal
                    graph.addPedestrianCrossing(node1, node2);
                    graph.addEdge(node1, node2);
                    
                    // Conectar con la parada anterior en la misma línea
                    if (lastNode != null) {
                        graph.addEdge(node1, lastNode); // Conectar el nuevo nodo con el último nodo
                    }

                    // Actualizar el último nodo procesado
                    lastNode = node1;
                }else if(line.startsWith("\"")){
                    String stopName = line.replace("\"", "").trim();
                    
                    
                    // Agregar nodo para la parada
                    GraphNode node = graph.findNodeByName(stopName);
                    if (node == null) {
                        node = graph.addNode(stopName);
                    }

                    // Conectar con la parada anterior en la misma línea
                    if (lastNode != null) {
                        graph.addEdge(node, lastNode); // Conectar el nuevo nodo con el último nodo
                    }

                    // Actualizar el último nodo procesado
                    lastNode = node;
                }
            }
        } 
        return graph;
    }

    /**
     * Retorna el nombre del grafo
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
}
