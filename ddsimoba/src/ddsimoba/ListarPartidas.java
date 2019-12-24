/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddsimoba;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author domin
 */
public class ListarPartidas extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListarUsuarios
     */
    
    public ListarPartidas() {
        initComponents();
        mostrarTabla();
    }
       
    public void mostrarTabla(){
        
        //Creamos conexión BBDD
        ConexionDB conex = new ConexionDB();
        
        //Creamos el modelo de la tabla
        DefaultTableModel modelotabla =  new DefaultTableModel();
        
        //Creamos la consulta
        String consulta = "SELECT * FROM partida";
        //Obtenemos resultados de la consulta
        ResultSet rs = conex.creaTabla(consulta);
        
        //Modificamos el modelo para decirle que las columnas llevan dichos identificadores ó nombres que aparecerán
        modelotabla.setColumnIdentifiers(new Object[]{"idPartida","tipo","descripcion"});
    
        //Iniciamos el relleno de la tabla
        
        try{
            
            while(rs.next()){ //Iteramos sobre el ResultSet
                //Añadimos filas al modelo
                modelotabla.addRow(new Object[]{rs.getString("idPartida"),rs.getString("tipo"),rs.getString("descripcion")});
            }
            
            this.tabla.setModel(modelotabla);
            
            conex.cerrarConexionDB();
            
        }catch(SQLException e){
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    
    
    
    
    }
        
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        bEliminar = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        refrescar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Lista Partidas");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        bEliminar.setText("Eliminar Partida");
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });

        bModificar.setText("Modificar Partida");
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });

        refrescar.setText("Refrescar");
        refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(bEliminar)
                .addGap(75, 75, 75)
                .addComponent(bModificar)
                .addGap(69, 69, 69)
                .addComponent(refrescar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bEliminar)
                    .addComponent(bModificar)
                    .addComponent(refrescar))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        
        //Vamos a modificar un usuario. 
        //Para ello Duplico el formulario de Alta de usuario, pero pongo No editable el campo de nombre de usuario
        //dado que es la clave primaria, no la queremos editar por si acaso. Podría hacerse pero habría que comprobar
        //que no hubiera otra clave primaria igual...y es un tostoncito.
        //Vamos a hacerlo más simple, pudiendo editar los demás campos
        
        //Primero comprobamos que se ha seleccionado una fila en la tabla, si no, avisa de que hay que seleccionar una
        
        int numeroFila = this.tabla.getSelectedRow(); //Número de fila seleccionada
        int numeroColumnas = this.tabla.getColumnCount(); //Número de columnas totales
        
        if(numeroFila != -1){ //Se ha seleccionado una fila
            
            //Recogemos los datos de la tabla
            ArrayList<String> datosFila = new ArrayList<>();
            
            for(int i = 0; i< numeroColumnas; i++){
                datosFila.add(this.tabla.getValueAt(numeroFila, i).toString());
                
            }
            //Una vez recogido los datos y sabiendo el orden:
            //nombre,idequipo,contraseña,correo,fec_nac,servidor,saldo,karma
            
            //Creamos una nueva ventana de modificación
            
            
            FormularioModificacionPartida modi = new FormularioModificacionPartida(datosFila.get(0), datosFila.get(1), datosFila.get(2));
            //La añandimos a la ventana principal (Al escritorio principal)
            VentanaPrincipal.escritorio.add(modi);
            //La colocamos delante
            modi.toFront();
            //La hacemos visible
            modi.setVisible(true); 
            
            
        }else{//No se ha seleccionado una fila. Damos mensaje de alerta
            
            JOptionPane.showMessageDialog(null, "Necesita seleccionar una fila de la lista", "Alerta ", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
        
    }//GEN-LAST:event_bModificarActionPerformed

    private void refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refrescarActionPerformed
       this.mostrarTabla();
    }//GEN-LAST:event_refrescarActionPerformed

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        int numeroFila = this.tabla.getSelectedRow(); //Número de fila seleccionada
        int numeroColumnas = this.tabla.getColumnCount(); //Número de columnas totales
        
        if(numeroFila != -1){ //Se ha seleccionado una fila
            
            //Recogemos el usuario seleccionado
            
            String partidaSelected = this.tabla.getValueAt(numeroFila,0).toString();
            
            //Añadimos ventana de aviso al panel
            //VentanaAvisoBorrado ventana = new VentanaAvisoBorrado();
            //VentanaPrincipal.escritorio.add(ventana);
            //ventana.toFront();
            //ventana.setVisible(true);
            
            //Recogemos la respuesta de la pregunta
            //boolean respuesta = ventana.SioNo();
            
            //if(respuesta){ //si la respuesta es que sí
                
            //ventana.dispose(); //Cerramos la ventana
            
            //Inciamos la conexión a la BBDD
            ConexionDB conexion = new ConexionDB();
        
            //Preparo la consulta
            PreparedStatement pstm1 = null;   
                String sqlQuery1 = "DELETE FROM juega WHERE idpartida = ?";
                
            PreparedStatement pstm2 = null;   
                String sqlQuery2 = "DELETE FROM partida WHERE idpartida = ?";
               
            //Preparo la sentencia a ejecutar.
            try{
            
            
            pstm1 = conexion.con.prepareStatement(sqlQuery1);
            pstm1.setString(1, partidaSelected);
                                
            int resultado = pstm1.executeUpdate();
        
                    if(resultado !=0){
            
                        JOptionPane.showMessageDialog(null, "Se ha eliminado la fila correctamente", "Alerta ", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "No se ha eliminado en juega ya que no está dicha partida", "Alerta ", JOptionPane.INFORMATION_MESSAGE);
                    }
            
             }catch(SQLException ex){
                ex.printStackTrace(System.out);
                throw new RuntimeException(ex);
             
             }finally{
            
                    try{
                        if(pstm1!=null) pstm1.close();
                        //if(conexion.con!=null) conexion.cerrarConexionDB();
                     }catch(SQLException ex){
                           ex.printStackTrace(System.out);
                            throw new RuntimeException(ex);
                    }
            }
            
            //Preparo la sentencia a ejecutar.
            try{
            
            
            pstm2 = conexion.con.prepareStatement(sqlQuery2);
            pstm2.setString(1, partidaSelected);
        
            int resultado = pstm2.executeUpdate();
        
                    if(resultado !=0){
            
                        JOptionPane.showMessageDialog(null, "Se ha eliminado la fila correctamente", "Alerta ", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        throw new RuntimeException("No se pudo eliminar la fila");
                    }
            
             }catch(SQLException ex){
                ex.printStackTrace(System.out);
                throw new RuntimeException(ex);
             
             }finally{
            
                    try{
                        if(pstm2!=null) pstm1.close();
                        if(conexion.con!=null) conexion.cerrarConexionDB();
                     }catch(SQLException ex){
                           ex.printStackTrace(System.out);
                            throw new RuntimeException(ex);
                    }
            }
            
            
            
            
        }else{//No se ha seleccionado una fila. Damos mensaje de alerta
            
            JOptionPane.showMessageDialog(null, "Necesita seleccionar una fila de la lista", "Alerta ", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_bEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton bModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refrescar;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
    

