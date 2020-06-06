/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chriss Ramos
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    ArrayList<String> listaErrores;
    ArrayList<String> listaTokens;
    ArrayList<String> listaLexemas;
    int indice; 
    int estado;
    String lexema;
    DefaultTableModel modelo;
   
    
    private void inicializar(){
        indice =0;
        estado = 0;
        lexema = "";
        
        listaErrores = new ArrayList();
        listaTokens = new ArrayList();
        listaLexemas = new ArrayList();
        
        modelo=(DefaultTableModel) tblTokens.getModel(); 
    }
    
    public VentanaPrincipal() {
        initComponents();
        inicializar();
        this.setLocationRelativeTo(null);
        
    }
    
    private void analizarUno(){
    
        String entrada = txtArchivo1.getText() + ' ';
        String textoLimpio = "";
        for (int i = 0; i < entrada.length(); i++) {
            char letra = entrada.charAt(i);
            switch (letra) {
                case '\r':
                case '\t':
                case '\b':
                case '\f':
                                   
                    break;
                default:
                    textoLimpio = textoLimpio + letra;
            }
        }
        
        for (indice = 0; indice <textoLimpio.length(); indice++) {
            char letra = textoLimpio.charAt(indice);
            int codigoAscii = letra;
            switch (estado) {
                case 0:
                    if((codigoAscii >= 65 && codigoAscii <= 90 )
                            || (codigoAscii >= 97 && codigoAscii <= 122)){
                        System.out.println("es letra se va al 1" + letra);
                        estado = 1;
                        lexema = ""+letra;
                    
                    }
                    else if(codigoAscii >= 48 && codigoAscii <= 57){
                        estado = 2;
                        lexema = ""+letra;
                    }
                    else if(codigoAscii == 42 || codigoAscii == 35 || codigoAscii == 45 ){
                        estado = 3;
                        lexema = ""+letra;
                    }
                    else if(letra == '/'){
                        estado = 4;
                        lexema = ""+letra;
                    }
                    else if(letra == '<'){
                        estado = 6;
                        lexema = ""+letra;
                    }
                    else if (letra == ' '){ // espacio
                        estado = 0;
                    }
                    else if (letra == '\n'){
                        estado = 0;
                    }
                    break;
                case 1:
                    if( (codigoAscii >= 65 && codigoAscii <= 90) 
                            || (codigoAscii >= 97 && codigoAscii <= 122)
                            || ( codigoAscii >= 48 && codigoAscii <= 57)
                            || letra == '_'){
                        estado = 1;
                        lexema = lexema+letra;
                    }else{
                        //aqui acepto identificadores
                        Object[] fila = new Object[3];
                        fila[0] = "Identificador";
                        fila[1] = lexema;
                        fila[2] = "archivo uno";
                        modelo.addRow(fila);
                        tblTokens.setModel(modelo);
                        
                        System.out.println("identificador a aceptar: " + lexema);
                        lexema = "";
                        estado = 0;
                        indice--;
                    }
                    break;
                case 2:
                    if(codigoAscii >= 48 && codigoAscii <= 57){
                        estado = 2;
                        lexema = lexema + letra;
                    }else{
                        Object[] fila = new Object[3];
                        fila[0] = "Numero";
                        fila[1] = lexema;
                        fila[2] = "archivo uno";
                        modelo.addRow(fila);
                        tblTokens.setModel(modelo);
                        
                        lexema = "";
                        estado = 0;
                        indice--;
                    }
                    break;
                case 3:
                    Object[] fila2 = new Object[3];
                    switch (lexema) {
                        case "*":
                            fila2[0] = "signo *";
                            break;
                        case "#":
                            fila2[0] = "signo #";
                            break;
                        case "-":
                            fila2[0] = "signo -";
                            break;
                        default:
                            break;
                    }
                    
                    fila2[1] = lexema;
                    fila2[2] = "archivo uno";
                    modelo.addRow(fila2);
                    tblTokens.setModel(modelo);
                            
                    lexema = "";
                    estado = 0;
                    indice--;
                    
                    break;
                case 4:
                    if(letra == '/'){
                        estado = 5;
                        lexema = lexema + letra;
                        System.out.println("se esta armando coment");
                    }
                    break;
                case 5:
                    // acepto comentario simple
                    if(letra == '\n'){
                        // acepta
                        System.out.println("aceptare el lexema " + lexema);
                        //meter a tabla
                        Object [] fila3=new Object[3]; 
                        
                        fila3[0] = "Comentario simple";
                        fila3[1] = lexema;
                        fila3[2] = "archivo dos";
                        //fila[3] = contadorLinea;
                        modelo.addRow(fila3);
                        tblTokens.setModel(modelo);
                        
                        //listaLexemas.add(lexema);
                        lexema = "";
                        estado = 0;
                        indice--;
                    }
                    else{
                        estado = 6;
                        //System.out.println("letra que vino en comentario " + letra);
                        lexema = lexema +letra;
                    }
                    break;
                case 6:
                    if(letra == '!'){
                        estado = 7;
                        lexema = lexema + letra;
                    }
                    break;
                case 7:
                    if(letra == '!'){
                        estado = 8;
                        lexema =  lexema +letra;
                    }else{
                        estado = 7;
                        lexema = lexema + letra;
                    }
                    break;
                case 8:
                    if(letra == '>'){
                        estado = 9;
                        lexema = lexema+letra;
                    }
                    break;
                case 9:// acepto comentario multi
                    Object [] fila4=new Object[3]; 
                        
                    fila4[0] = "Comentario Multilinea";
                    fila4[1] = lexema;
                    fila4[2] = "archivo dos";
                    //fila[3] = contadorLinea;
                    modelo.addRow(fila4);
                    tblTokens.setModel(modelo);


                    listaLexemas.add(lexema);
                    lexema = "";
                    estado = 0;
                    indice--;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    
    private void analizarDos(){
    
        String entrada = txtArchivo2.getText() + ' ';
        String textoLimpio = "";
        int contadorLinea = 0;
        for (int i = 0; i < entrada.length(); i++) {
            char letra = entrada.charAt(i);
            switch (letra) {
                case '\r':
                case '\t':
                case '\b':
                case '\f':
                                   
                    break;
                default:
                    textoLimpio = textoLimpio + letra;
            }
        }
        
        for (indice = 0; indice <textoLimpio.length(); indice++) {
            char letra = textoLimpio.charAt(indice);
            int codigoAscii = letra;
            
            switch (estado) {
                case 0:
                    if (letra == '/') {
                        estado = 1;
                        lexema = "" + letra;
                    }
                    else if (letra == '<'){
                        //aqui ver lexema anterior, si era coma entonces fijo es direccion, si no entonces es comentario
                        estado = 2;
                        lexema = "" + letra;
                    }
                    else if (letra == ',' || letra == '>' || letra == 'v' || codigoAscii == 94
                            || codigoAscii == 73 || codigoAscii == 74|| codigoAscii == 76|| codigoAscii == 79
                            || codigoAscii == 83|| codigoAscii == 90|| codigoAscii == 84){
                         estado =3;
                         lexema = "" +letra;
                         System.out.println("lexema que se manda en case 0 " + lexema);
                    }
                    else if (letra == ' '){ // espacio
                        estado = 0;
                    }
                    else if (letra == '\n'){
                        contadorLinea++;
                        estado = 0;
                    }
                    else{
                        //aqui es error lexico
                        System.out.println("error lexico " + codigoAscii);
                        estado = 0;
                        listaErrores.add("" + lexema);
                    }
                    break;
                case 1:
                    if (letra == '/') {
                        estado = 6;
                        lexema = lexema + letra;
                    }
                    break;
                case 2:
                    // ver aqui si es signo o comentario
                    //System.out.println("estoy en case 2 de la < ");
                    ///System.out.println(" la letra es: " + letra );
                    //System.out.println(" el lexema es: " + lexema);
                    if (letra == '!') {
                        //es comentario
                        //System.out.println("soy un comentario");
                        estado = 4;
                        lexema = lexema + letra;
                    }
                    else if(codigoAscii == 10) {
                        
                        //System.out.println("soy una direccion porque mi letra es " + letra);
                        //System.out.println("y mi lexema : " + lexema);
                        estado = 3;
                        lexema = "" + lexema;
                    }
                    
                    break;
                case 3: 
                    // estado de aceptacion
                    System.out.println("letra que vino : " + letra);
                    System.out.println("lexema completo " + lexema);
                    Object [] fila=new Object[3];
                    switch (lexema) {
                        case ">":
                            { 
                                fila[0] = "Direccion Derecha";
                                
                                break;                        
                            }
                        case "<":
                            { 
                                fila[0] = "Direccion Izquierda";
                                
                                break;                        
                            }
                        case "^":
                            { 
                                fila[0] = "Direccion Arriba";
                                
                                break;                        
                            }
                        case "v":
                            { 
                                fila[0] = "Direccion Abajo";
                                
                                break;                        
                            }
                        case ",":
                            {
                                fila[0] = "Coma";
                                break;
                            }
                        case "L":
                            {
                                
                                fila[0] = "Pieza";
                            }
                        case "I":
                            {
                                
                                fila[0] = "Pieza";
                            }
                        case "J":
                            {
                                
                                fila[0] = "Pieza";
                            }
                        case "O":
                            {
                                
                                fila[0] = "Pieza";
                            }
                        case "S":
                            {
                                
                                fila[0] = "Pieza";
                            }
                        case "Z":
                            {
                                
                                fila[0] = "Pieza";
                            }
                        case "T":
                            {
                                
                                fila[0] = "Pieza";
                            }
                        
                        default:
                            //listaLexemas.add(lexema);
                            // reportar error lexico si no viene letra
                            
                            break;
                    }
                    
                    fila[1] = lexema;
                    fila[2] = "archivo dos";
                    modelo.addRow(fila);
                    tblTokens.setModel(modelo);
                    lexema = lexema.replaceFirst("\n", "");
                    listaLexemas.add(lexema);
                    
                    
                    
                    
                    lexema = "";
                    estado = 0;
                    indice --;
                    
                    break;
                case 4:
                    if(letra == '!'){
                        estado = 5;
                        lexema =  lexema +letra;
                    }else{
                        estado = 4;
                        lexema = lexema + letra;
                    }
                    break;
                case 5:
                    if(letra == '>'){
                        estado = 7;
                        lexema = lexema +letra;
                    }
                    break;
                case 6:
                    if(letra == '\n'){
                        // acepta
                       // System.out.println("aceptare el lexema " + lexema);
                        //meter a tabla
                        Object [] fila2=new Object[3]; 
                        
                        fila2[0] = "Comentario simple";
                        fila2[1] = lexema;
                        fila2[2] = "archivo dos";
                        //fila[3] = contadorLinea;
                        modelo.addRow(fila2);
                        tblTokens.setModel(modelo);
                        
                        listaLexemas.add(lexema);
                        lexema = "";
                        estado = 0;
                        indice--;
                    }
                    else{
                        estado = 6;
                        //System.out.println("letra que vino en comentario " + letra);
                        lexema = lexema +letra;
                    }
                    break;
                case 7:
                        Object [] fila3=new Object[3]; 
                        
                        fila3[0] = "Comentario Multilinea";
                        fila3[1] = lexema;
                        fila3[2] = "archivo dos";
                        //fila[3] = contadorLinea;
                        modelo.addRow(fila3);
                        tblTokens.setModel(modelo);
                        
                        
                        listaLexemas.add(lexema);
                        lexema = "";
                        estado = 0;
                        indice--;
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
    }
    
    
    
    public void imprimirDos(){
        System.out.println("---------------------------------------------------");
        System.out.println("hay " + listaLexemas.size());
        for (int i = 0; i < listaLexemas.size(); i++) {
            System.out.println(listaLexemas.get(i));
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArchivo2 = new javax.swing.JTextArea();
        lblArchivo1 = new javax.swing.JLabel();
        lblArchivo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        abrirArchivoUno = new javax.swing.JMenuItem();
        abrirArchivoDos = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtArchivo1.setBackground(new java.awt.Color(29, 41, 81));
        txtArchivo1.setColumns(20);
        txtArchivo1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtArchivo1.setForeground(new java.awt.Color(51, 255, 0));
        txtArchivo1.setRows(5);
        jScrollPane1.setViewportView(txtArchivo1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 230, 360));

        txtArchivo2.setBackground(new java.awt.Color(29, 41, 81));
        txtArchivo2.setColumns(20);
        txtArchivo2.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtArchivo2.setForeground(new java.awt.Color(102, 255, 51));
        txtArchivo2.setRows(5);
        jScrollPane2.setViewportView(txtArchivo2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 230, 360));

        lblArchivo1.setFont(new java.awt.Font("Lucida Console", 0, 24)); // NOI18N
        lblArchivo1.setForeground(new java.awt.Color(255, 255, 255));
        lblArchivo1.setText("Archivo 1");
        getContentPane().add(lblArchivo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        lblArchivo2.setFont(new java.awt.Font("Lucida Console", 0, 24)); // NOI18N
        lblArchivo2.setForeground(new java.awt.Color(255, 255, 255));
        lblArchivo2.setText("Archivo 2");
        getContentPane().add(lblArchivo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 130, 40));

        jLabel1.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Area De Juego");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 10, 270, 50));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 70, 460, 650));

        jLabel2.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Siguiente Pieza");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 70, -1, -1));

        jButton1.setText("Rotar");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 270, 90, -1));

        jButton2.setText("Dejar Caer");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 320, -1, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tokens Analizados");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Lexema", "Archivo"
            }
        ));
        jScrollPane3.setViewportView(tblTokens);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 510, 100));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable2);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 700, 510, 100));

        jLabel4.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Errores Lexicos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, -1, -1));

        jButton3.setText("Lex2");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, -1, -1));

        fondo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fondo.setForeground(new java.awt.Color(255, 255, 255));
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        fondo.setToolTipText("");
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 920));

        jMenu1.setText("Archivo");

        abrirArchivoUno.setText("Abrir Archivo 1");
        abrirArchivoUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirArchivoUnoActionPerformed(evt);
            }
        });
        jMenu1.add(abrirArchivoUno);

        abrirArchivoDos.setText("Abrir Archivo 2");
        abrirArchivoDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirArchivoDosActionPerformed(evt);
            }
        });
        jMenu1.add(abrirArchivoDos);

        jMenuItem3.setText("Salir");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Juego");

        jMenuItem4.setText("Analizar Archivo 1");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Analizar Archivo 2");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Jugar");
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayuda");

        jMenuItem7.setText("Manual de Usuario");
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Manual Tecnico");
        jMenu3.add(jMenuItem8);

        jMenuItem9.setText("Acerca De");
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Reportes");

        jMenuItem10.setText("Errores Lexicos");
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirArchivoUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoUnoActionPerformed
        // TODO add your handling code here:
        String aux="";   
        String texto="";
        try
            {
             /**llamamos el metodo que permite cargar la ventana*/
             JFileChooser file=new JFileChooser();
             file.showOpenDialog(this);
             /**abrimos el archivo seleccionado*/
             File abre=file.getSelectedFile();

             /**recorremos el archivo, lo leemos para plasmarlo
             *en el area de texto*/
             if(abre!=null)
             {     
                FileReader archivos=new FileReader(abre);
                BufferedReader lee=new BufferedReader(archivos);
                while((aux=lee.readLine())!=null)
                {
                   texto+= aux+ "\n";
                }
                   lee.close();
              }    
             }
             catch(IOException ex)
             {
               JOptionPane.showMessageDialog(null,ex+"" +
                     "\nNo se ha encontrado el archivo",
                           "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
              }
            txtArchivo1.setText(texto);
        //return texto;//El texto se almacena en el JTextArea
    }//GEN-LAST:event_abrirArchivoUnoActionPerformed

    private void abrirArchivoDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoDosActionPerformed
        // TODO add your handling code here:
        String aux="";   
        String texto="";
        try
            {
             /**llamamos el metodo que permite cargar la ventana*/
             JFileChooser file=new JFileChooser();
             file.showOpenDialog(this);
             /**abrimos el archivo seleccionado*/
             File abre=file.getSelectedFile();

             /**recorremos el archivo, lo leemos para plasmarlo
             *en el area de texto*/
             if(abre!=null)
             {     
                FileReader archivos=new FileReader(abre);
                BufferedReader lee=new BufferedReader(archivos);
                while((aux=lee.readLine())!=null)
                {
                   texto+= aux+ "\n";
                }
                   lee.close();
              }    
             }
             catch(IOException ex)
             {
               JOptionPane.showMessageDialog(null,ex+"" +
                     "\nNo se ha encontrado el archivo",
                           "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
              }
            txtArchivo2.setText(texto);
    }//GEN-LAST:event_abrirArchivoDosActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        analizarDos();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        imprimirDos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        analizarUno();
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    
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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirArchivoDos;
    private javax.swing.JMenuItem abrirArchivoUno;
    private javax.swing.JLabel fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblArchivo1;
    private javax.swing.JLabel lblArchivo2;
    private javax.swing.JTable tblTokens;
    private javax.swing.JTextArea txtArchivo1;
    private javax.swing.JTextArea txtArchivo2;
    // End of variables declaration//GEN-END:variables
}
