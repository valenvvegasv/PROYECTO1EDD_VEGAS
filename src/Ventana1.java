
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author valen
 */
public class Ventana1 extends javax.swing.JFrame {

    /**
     * Creates new form Ventana1
     */
    public Graph graph = new Graph();
    int radio;
    public Ventana1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FONDO = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ingresarJSON = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        valorT = new javax.swing.JButton();
        siguienteVentana = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FONDO.setBackground(new java.awt.Color(204, 204, 255));
        FONDO.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(11, 165, 155));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Captura de pantalla 2024-10-23 210024.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(103, 103, 103))
        );

        FONDO.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 200, 420));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 52)); // NOI18N
        jLabel2.setText("BIENVENIDO");
        FONDO.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        ingresarJSON.setBackground(new java.awt.Color(255, 204, 255));
        ingresarJSON.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 14)); // NOI18N
        ingresarJSON.setText("INGRESAR ARCHIVO CON RED DE METRO");
        ingresarJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarJSONActionPerformed(evt);
            }
        });
        FONDO.add(ingresarJSON, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 280, 50));

        jLabel3.setText("IMPORTANTE: el archivo debe ser de tipo .json");
        FONDO.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 250, -1));

        valorT.setText("INGRESAR VALOR DE T");
        valorT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorTActionPerformed(evt);
            }
        });
        FONDO.add(valorT, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, -1));

        siguienteVentana.setText("SIGUIENTE");
        siguienteVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteVentanaActionPerformed(evt);
            }
        });
        FONDO.add(siguienteVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FONDO, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FONDO, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarJSONActionPerformed
        // TODO add your handling code here:
        Archivo archivo = new Archivo();
        graph = archivo.cargarGrafo(graph);
        if(graph == null){
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un archivo de tipo .json", "Error de archivo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ingresarJSONActionPerformed

    private void valorTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorTActionPerformed
        // TODO add your handling code here:
        String respuesta = JOptionPane.showInputDialog(this, "Ingresa el valor del radio (t)");
        try{
            radio = Integer.parseInt(respuesta);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Por favor, ingresa un numero valido", "Error de valor", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_valorTActionPerformed

    private void siguienteVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteVentanaActionPerformed
        // TODO add your handling code here:
        if(radio == 0 || graph == null){
            JOptionPane.showMessageDialog(null, "Debes ingresar un archivo y fijar un valor de t antes de continuar", "Error datos incompletos", JOptionPane.ERROR_MESSAGE);
        }
        /*
        else if{
            Ventana2 ventana2 = new Ventana2();
            ventana2.setVisible(true);
            //tengo que pasarle el valor del grafo y del radio a la siguiente ventana
        }
        */
    }//GEN-LAST:event_siguienteVentanaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FONDO;
    private javax.swing.JButton ingresarJSON;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton siguienteVentana;
    private javax.swing.JButton valorT;
    // End of variables declaration//GEN-END:variables
}
