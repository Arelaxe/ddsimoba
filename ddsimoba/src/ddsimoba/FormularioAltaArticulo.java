/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddsimoba;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.*;
/**
 *
 * @author noelia
 */
public class FormularioAltaArticulo extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrame
     */
    public FormularioAltaArticulo() {
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

        jToggleButton1 = new javax.swing.JToggleButton();
        nombreArticulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        addArticulo = new javax.swing.JButton();
        clear = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Añadir artículo");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        nombreArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreArticuloActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre del artículo");

        jLabel2.setText("Precio");

        jLabel3.setText("Descripción");

        addArticulo.setText("Añadir");
        addArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addArticuloActionPerformed(evt);
            }
        });

        clear.setText("Limpiar campos");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addArticulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addComponent(clear))
                    .addComponent(precio, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreArticulo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcion, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addArticulo)
                    .addComponent(clear))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreArticuloActionPerformed

    private void addArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addArticuloActionPerformed
        ConexionDB conex = new ConexionDB();
        Random r = new Random();
        
        PreparedStatement pst = null;
        
        int i = r.nextInt(10001);
        String ident = Integer.toString(i);
        String name = nombreArticulo.getText();
        int prize = Integer.parseInt(precio.getText());
        String desc = descripcion.getText();
        
        String sqlQuery = "INSERT INTO tienda(idarticulo,nombre,descripcion,precio) VALUES(?,?,?,?)";
        
        try{
            pst = conex.con.prepareStatement(sqlQuery);
            pst.setString(1, ident);
            pst.setString(2, name);
            pst.setString(3, desc);
            pst.setInt(4, prize);
            
            int resultado = pst.executeUpdate();
        
            if(resultado ==1){
                System.out.print("Fila insertada correctamente");
                JOptionPane.showMessageDialog(null, "Se ha insertado la fila correctamente", "Alerta ", JOptionPane.INFORMATION_MESSAGE);
            }else{
                throw new RuntimeException("No se pudo insertar la fila");
            }
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
            throw new RuntimeException(ex);
        }finally{
            
            try{
                if(pst!=null) pst.close();
                if(conex.con!=null) conex.cerrarConexionDB();
            }catch(SQLException ex){
                ex.printStackTrace(System.out);
                throw new RuntimeException(ex);
            }
        }
        this.nombreArticulo.setText("");
        this.precio.setText("");
        this.descripcion.setText("");
    }//GEN-LAST:event_addArticuloActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        this.nombreArticulo.setText("");
        this.precio.setText("");
        this.descripcion.setText("");
    }//GEN-LAST:event_clearActionPerformed

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addArticulo;
    private javax.swing.JButton clear;
    private javax.swing.JTextField descripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField nombreArticulo;
    private javax.swing.JTextField precio;
    // End of variables declaration//GEN-END:variables
}