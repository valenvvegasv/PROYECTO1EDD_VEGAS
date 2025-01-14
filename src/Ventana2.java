
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 * Ventana2 es una clase que representa una ventana gráfica de la aplicación 
 * para manejar una red de metro, donde se pueden colocar sucursales, 
 * calcular coberturas y agregar líneas entre paradas.
 * 
 * La clase permite interactuar con la red de metro y visualizar la 
 * información relevante en un componente gráfico.
 * 
 * @author valen
 */
public class Ventana2 extends javax.swing.JFrame {
    
    /** El radio de cobertura para las sucursales. */
    private int radio;
    
    /** El grafo que representa la red de metro. */
    private Graph graph;
    
    /** El nombre de la red de metro. */
    private String nombre;
    
    /** El visualizador de grafos. */
    GraphVisualizer visualizer = new GraphVisualizer();
    
    /** La lista de nodos que tienen una tienda. */
    LinkedList nodesWithStore = new LinkedList();
    
    /** La lista de nodos accesibles desde una sucursal. */
    LinkedList accessibleNodes = new LinkedList();
    
    /**
    * Busca un nodo por su nombre en el grafo.
    * 
    * @param name El nombre del nodo a buscar.
    * @return El nodo correspondiente al nombre, o null si no se encuentra.
    */
    private GraphNode findNodeByName(String name) {
        Node current = graph.getNodes().head;
        while (current != null) {
            if (current.node.name.equals(name)) {
                return current.node;
            }
            current = current.next;
        }
        return null; // Nodo no encontrado
    }
    
    /**
     * Crea una nueva instancia de Ventana2.
     * 
     * @param graph El grafo que representa la red de metro.
     * @param radio El radio de cobertura para las sucursales.
     * @param nombre El nombre de la red de metro.
     */
    public Ventana2(Graph graph, int radio, String nombre) {
        this.graph = graph;
        this.radio = radio;
        this.nombre = nombre;
        System.setProperty("org.graphstream.ui", "swing");
        visualizer.construirGraph(graph);
        
        //visualizer.visualizeGraph();
        initComponents();
        this.jLabel1.setText("Red "+nombre);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        establecerT = new javax.swing.JButton();
        colocarSucursal = new javax.swing.JButton();
        coberturaSucursalBFS = new javax.swing.JButton();
        coberturaSucursalDFS = new javax.swing.JButton();
        agregarLinea = new javax.swing.JButton();
        CoberturaTotal = new javax.swing.JButton();
        eliminarSucursal = new javax.swing.JButton();
        cargarNueva = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));

        background.setBackground(new java.awt.Color(204, 204, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        establecerT.setBackground(new java.awt.Color(255, 206, 255));
        establecerT.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        establecerT.setText("CAMBIAR VALOR DE T");
        establecerT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                establecerTActionPerformed(evt);
            }
        });
        background.add(establecerT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, 50));

        colocarSucursal.setBackground(new java.awt.Color(255, 206, 255));
        colocarSucursal.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        colocarSucursal.setText("COLOCAR SUCURSAL");
        colocarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colocarSucursalActionPerformed(evt);
            }
        });
        background.add(colocarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 180, 50));

        coberturaSucursalBFS.setBackground(new java.awt.Color(255, 206, 255));
        coberturaSucursalBFS.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        coberturaSucursalBFS.setText("VER COBERTURAL SUCURSAL (BFS)");
        coberturaSucursalBFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coberturaSucursalBFSActionPerformed(evt);
            }
        });
        background.add(coberturaSucursalBFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, -1, 50));

        coberturaSucursalDFS.setBackground(new java.awt.Color(255, 206, 255));
        coberturaSucursalDFS.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        coberturaSucursalDFS.setText("VER COBERTURAL SUCURSAL (DFS)");
        coberturaSucursalDFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coberturaSucursalDFSActionPerformed(evt);
            }
        });
        background.add(coberturaSucursalDFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, -1, 50));

        agregarLinea.setBackground(new java.awt.Color(255, 206, 255));
        agregarLinea.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        agregarLinea.setText("AGREGAR LINEA");
        agregarLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarLineaActionPerformed(evt);
            }
        });
        background.add(agregarLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 170, 50));

        CoberturaTotal.setBackground(new java.awt.Color(255, 206, 255));
        CoberturaTotal.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        CoberturaTotal.setText("VER COBERTURA TOTAL");
        CoberturaTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CoberturaTotalActionPerformed(evt);
            }
        });
        background.add(CoberturaTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, 50));

        eliminarSucursal.setBackground(new java.awt.Color(255, 206, 255));
        eliminarSucursal.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        eliminarSucursal.setText("ELIMINAR SUCURSAL");
        eliminarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarSucursalActionPerformed(evt);
            }
        });
        background.add(eliminarSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 180, 50));

        cargarNueva.setBackground(new java.awt.Color(255, 206, 255));
        cargarNueva.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        cargarNueva.setText("CARGAR NUEVA RED DE METRO");
        cargarNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarNuevaActionPerformed(evt);
            }
        });
        background.add(cargarNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, -1, 50));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 530, 60));

        jPanel1.setBackground(new java.awt.Color(11, 163, 153));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 20)); // NOI18N
        jLabel3.setText("LEYENDA DE COLORES");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel4.setText("Amarillo: parada con sucursal");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel5.setText("Morado: paradas cubiertas");

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel6.setText("por una sucursal");

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel7.setText("para futuras sucursales");

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel8.setText("Rojo: sugerencia de ubicaciones");

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 18)); // NOI18N
        jLabel9.setText("Arista roja: cruce peatonal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        background.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 440));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Acción que se ejecuta al presionar el botón para establecer el valor de T.
     * Permite al usuario ingresar un nuevo valor para el radio de cobertura.
     * 
     * @param evt El evento de acción.
     */
    private void establecerTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_establecerTActionPerformed
        // TODO add your handling code here:
        String respuesta = JOptionPane.showInputDialog(this, "Ingresa el valor del radio (t)");
        try{
            radio = Integer.parseInt(respuesta);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Por favor, ingresa un numero valido", "Error de valor", JOptionPane.ERROR_MESSAGE);
        }
        if(radio>=0){
            graph.recalcularAccessedNodes(radio, accessibleNodes);
        }
    }//GEN-LAST:event_establecerTActionPerformed
    
    /**
     * Acción que se ejecuta al presionar el botón para colocar una sucursal.
     * Solicita al usuario el nombre de la parada donde desea colocar la sucursal.
     * 
     * @param evt El evento de acción.
     */
    private void colocarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colocarSucursalActionPerformed
        // TODO add your handling code here:
        String respuesta = JOptionPane.showInputDialog(this, "Introduzca el nombre de la parada donde desea colocar la sucursual");
        GraphNode nuevo = findNodeByName(respuesta);
        if(nuevo == null){
            JOptionPane.showMessageDialog(null, "Por favor, ingresa una parada valida", "Error: parada no encontrada", JOptionPane.ERROR_MESSAGE);
        }else{
            visualizer.nodoSeleccionado(graph, respuesta);
            nodesWithStore.insertar(nuevo);
            graph.bfsConRadio2(nuevo, radio);
            accessibleNodes = graph.getAccessibleNodes();
            graph.recalcularAccessedNodes(radio, accessibleNodes);
            //visualizer.markAccessibleNodes(graph, accessibleNodes);
            visualizer.resetearColores(graph);
            //que se marque de amarillo los nodos con tienda
        }
        //accessibleNodes = graph.getAccessibleNodes();
    }//GEN-LAST:event_colocarSucursalActionPerformed
    
    /**
     * Acción que se ejecuta al presionar el botón para ver la cobertura 
     * de la sucursal utilizando BFS.
     * 
     * @param evt El evento de acción.
     */
    private void coberturaSucursalBFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coberturaSucursalBFSActionPerformed
        // TODO add your handling code here:
        String respuesta = JOptionPane.showInputDialog(this, "Introduzca el nombre de la parada donde desea evaluar la cobertura");
        GraphNode nuevo = findNodeByName(respuesta);
        if(nuevo == null||nuevo.hasStore == false){
            JOptionPane.showMessageDialog(null, "Por favor, ingresa una parada valida", "Error: parada no encontrada", JOptionPane.ERROR_MESSAGE);
        }else{
            visualizer.nodoSeleccionado(graph, respuesta);
            LinkedList reachableNodes = graph.bfsConRadio3(nuevo, radio); //hacer una modificacion del metodo para que solo me devuelva los nodos accedidos por solo el nodo ingresado
            visualizer.markAccessibleNodes(graph, reachableNodes); //despues de unos segundos que vuelva a su color original
            //visualizer.resetearColores(graph);
        }
    }//GEN-LAST:event_coberturaSucursalBFSActionPerformed
    
    /**
     * Acción que se ejecuta al presionar el botón para ver la cobertura 
     * de la sucursal utilizando DFS.
     * 
     * @param evt El evento de acción.
     */
    private void coberturaSucursalDFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coberturaSucursalDFSActionPerformed
        // TODO add your handling code here:
        String respuesta = JOptionPane.showInputDialog(this, "Introduzca el nombre de la parada donde desea evaluar la cobertura");
        GraphNode nuevo = findNodeByName(respuesta);
        if(nuevo.hasStore == false || nuevo == null){
            JOptionPane.showMessageDialog(null, "Por favor, ingresa una parada valida", "Error de valor", JOptionPane.ERROR_MESSAGE);
        }else{ //AQUI ES CON DFS
            visualizer.nodoSeleccionado(graph, respuesta);
            //hacer una modificacion del metodo para que solo me devuelva los nodos accedidos por solo el nodo ingresado
            LinkedList accessedNodes = graph.dfsUnRadio(nuevo, radio); 
            visualizer.markAccessibleNodes(graph, accessedNodes);
        }
    }//GEN-LAST:event_coberturaSucursalDFSActionPerformed
    
    /**
     * Acción que se ejecuta al presionar el botón para agregar una nueva línea de metro 
     * entre dos paradas.
     * 
     * @param evt El evento de acción.
     */
    private void agregarLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarLineaActionPerformed
        // TODO add your handling code here:
        String respuesta1 = JOptionPane.showInputDialog(this, "Introduzca el nombre de la parada donde inicia la linea");
        GraphNode desde = findNodeByName(respuesta1);
        if(desde == null){
            JOptionPane.showMessageDialog(null, "El inicio de la linea debe ser una parada existente", "Error de valor", JOptionPane.ERROR_MESSAGE);
        }else{
            String respuesta2 = JOptionPane.showInputDialog(this, "Introduzca el nombre de la parada que se enlaza a la parada anterior");
            if(respuesta1.equals(respuesta2)){
                JOptionPane.showMessageDialog(null, "La linea no puede ir desde y hacia la misma parada", "Error de valor", JOptionPane.ERROR_MESSAGE);
            }else{
                GraphNode hacia = findNodeByName(respuesta2);
                if(hacia == null){
                    hacia = graph.addNode(respuesta2); //se inserta el nombre de la nueva parada
                    System.out.println("adding..."+hacia.name);
                    visualizer.insertarNodo(respuesta2);
                }
                graph.addEdge(desde, hacia);
                visualizer.ingresarConexion(respuesta1, respuesta2);
                accessibleNodes = new LinkedList();
                graph.recalcularAccessedNodes(radio, accessibleNodes);
            }
            
            
            //despues de agregar una linea se deben recalcular los bfs de los nodos con tienda
            
        }
    }//GEN-LAST:event_agregarLineaActionPerformed
    
    /**
     * Acción que se ejecuta al presionar el botón para ver la cobertura total 
     * de la red.
     * 
     * @param evt El evento de acción.
     */
    private void CoberturaTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CoberturaTotalActionPerformed
        // TODO add your handling code here:
        //accessibleNodes = graph.getAccessibleNodes();
        accessibleNodes = new LinkedList();
        graph.recalcularAccessedNodes(radio, accessibleNodes);
        visualizer.markAccessibleNodes(graph, accessibleNodes);
        //visualizer.resetearColores(graph);
        LinkedList accessibleNodesCopy = new LinkedList();
        Node current = accessibleNodes.head;
        while(current != null){
            accessibleNodesCopy.insertar(current.node);
            current = current.next;
        }
        LinkedList suggestedNodes = graph.suggestNodesToVisit(radio, accessibleNodesCopy);
        visualizer.nodosSugeridos(suggestedNodes, graph);
    }//GEN-LAST:event_CoberturaTotalActionPerformed
    
    /**
     * Acción que se ejecuta al presionar el botón para eliminar una sucursal.
     * 
     * @param evt El evento de acción.
     */
    private void eliminarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarSucursalActionPerformed
        // TODO add your handling code here:
        String respuesta = JOptionPane.showInputDialog(this, "Introduzca el nodo donde desea quitar la sucursual");
        visualizer.nodoDeseleccionado(graph, respuesta);
        //System.out.println(findNodeByName(respuesta).name);
        graph.deleteStore(findNodeByName(respuesta));
        GraphNode actual = findNodeByName(respuesta);
        actual.hasStore = false;
        nodesWithStore.eliminarTienda(findNodeByName(respuesta), nodesWithStore.head);
        //accessibleNodes = graph.getAccessibleNodes();
        visualizer.resetearColores(graph);
        graph.recalcularAccessedNodes(radio, accessibleNodes);
        //visualizer.markAccessibleNodes(graph, accessibleNodes);
    }//GEN-LAST:event_eliminarSucursalActionPerformed
    
    /**
    * Acción que se ejecuta al presionar el botón para cargar una nueva red de metro.
    * Este método cierra la ventana actual, limpia el visualizador y el grafo, 
    * y abre una nueva instancia de Ventana1.
    * 
    * @param evt El evento de acción.
    */
    private void cargarNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarNuevaActionPerformed
        // TODO add your handling code here:
        this.dispose();
        visualizer.eliminarTodo();
        graph.eliminarTodo();
        Ventana1 ventana1 = new Ventana1();
        ventana1.setVisible(true); 
    }//GEN-LAST:event_cargarNuevaActionPerformed

 
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CoberturaTotal;
    private javax.swing.JButton agregarLinea;
    private javax.swing.JPanel background;
    private javax.swing.JButton cargarNueva;
    private javax.swing.JButton coberturaSucursalBFS;
    private javax.swing.JButton coberturaSucursalDFS;
    private javax.swing.JButton colocarSucursal;
    private javax.swing.JButton eliminarSucursal;
    private javax.swing.JButton establecerT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
