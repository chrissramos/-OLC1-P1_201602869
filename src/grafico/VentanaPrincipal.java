/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafico;

import Objeto.Pieza;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
    ArrayList<Pieza> listaPiezas;
    ArrayList<String> listaTableros;
    int indice; 
    int estado;
    String lexema;
    String nombreLetra;
    
    
    String tablero; // aqui guardar tablero
    
    int nivelActual;
    
    
    //variables para archivo de entrada1
    int dimX;
    int dimY;
    String nombreNivel;
    int contadorNivel;// contador de niveles
    int cantidadNiveles;//cantidad de niveles archivo1
    int contadorPosicionM;
    // fin variables archivo entrada1
    DefaultTableModel modelo;
    DefaultTableModel modeloPieza;
    DefaultTableModel modeloNivel;
    
    //para matriz
    
    int dimensionX = 0;
    int dimensionY = 0;
    int tamX = 0;
    int tamY = 0;
    static final int tableroX = 500;
    static final int tableroY = 500;
    
    
    //matrices de piezas
    // figura I 
    JLabel [][] matrizIarriba = new JLabel[4][4];
    JLabel [][] matrizIabajo = new JLabel[4][4];
    JLabel [][] matrizIderecha = new JLabel[4][4];
    JLabel [][] matrizIizquierda = new JLabel[4][4];
    
    //figura J 
    JLabel [][] matrizJarriba = new JLabel[4][4];
    JLabel [][] matrizJabajo = new JLabel[4][4];
    JLabel [][] matrizJderecha = new JLabel[4][4];
    JLabel [][] matrizJizquierda = new JLabel[4][4];
    
    //figura L 
    JLabel [][] matrizLarriba = new JLabel[4][4];
    JLabel [][] matrizLabajo = new JLabel[4][4];
    JLabel [][] matrizLderecha = new JLabel[4][4];
    JLabel [][] matrizLizquierda = new JLabel[4][4];
    
    //figura O 
    JLabel [][] matrizOarriba = new JLabel[4][4];
    JLabel [][] matrizOabajo = new JLabel[4][4];
    JLabel [][] matrizOderecha = new JLabel[4][4];
    JLabel [][] matrizOizquierda = new JLabel[4][4];
    
    // figura S
    JLabel [][] matrizSarriba = new JLabel[4][4];
    JLabel [][] matrizSabajo = new JLabel[4][4];
    JLabel [][] matrizSderecha = new JLabel[4][4];
    JLabel [][] matrizSizquierda = new JLabel[4][4];
    
    //figura Z
    JLabel [][] matrizZarriba = new JLabel[4][4];
    JLabel [][] matrizZabajo = new JLabel[4][4];
    JLabel [][] matrizZderecha = new JLabel[4][4];
    JLabel [][] matrizZizquierda = new JLabel[4][4];
        
    //figura T
    JLabel [][] matrizTarriba = new JLabel[4][4];
    JLabel [][] matrizTabajo = new JLabel[4][4];
    JLabel [][] matrizTderecha = new JLabel[4][4];
    JLabel [][] matrizTizquierda = new JLabel[4][4];
    
    public void llenarMatricesPiezas(){
        
        // todas las matrices
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // I 
                matrizIarriba[i][j] = new JLabel(" ");
                matrizIabajo[i][j] = new JLabel(" ");
                matrizIderecha[i][j] = new JLabel(" ");
                matrizIizquierda[i][j] = new JLabel(" ");
                
                // J
                matrizJarriba[i][j] = new JLabel(" ");
                matrizJabajo[i][j] = new JLabel(" ");
                matrizJderecha[i][j] = new JLabel(" ");
                matrizJizquierda[i][j] = new JLabel(" ");
                // L 
                matrizLarriba[i][j] = new JLabel(" ");
                matrizLabajo[i][j] = new JLabel(" ");
                matrizLderecha[i][j] = new JLabel(" ");
                matrizLizquierda[i][j] = new JLabel(" ");
                // O 
                matrizOarriba[i][j] = new JLabel(" ");
                matrizOabajo[i][j] = new JLabel(" ");
                matrizOderecha[i][j] = new JLabel(" ");
                matrizOizquierda[i][j] = new JLabel(" ");
                // S 
                matrizSarriba[i][j] = new JLabel(" ");
                matrizSabajo[i][j] = new JLabel(" ");
                matrizSderecha[i][j] = new JLabel(" ");
                matrizSizquierda[i][j] = new JLabel(" ");
                // Z 
                matrizZarriba[i][j] = new JLabel(" ");
                matrizZabajo[i][j] = new JLabel(" ");
                matrizZderecha[i][j] = new JLabel(" ");
                matrizZizquierda[i][j] = new JLabel(" ");
                // T 
                matrizTarriba[i][j] = new JLabel(" ");
                matrizTabajo[i][j] = new JLabel(" ");
                matrizTderecha[i][j] = new JLabel(" ");
                matrizTizquierda[i][j] = new JLabel(" ");
            }
        }
        
        JLabel lbl1 = new JLabel(" ");
        lbl1.setBorder(BorderFactory.createLineBorder(Color.RED));
        lbl1.setBackground(Color.RED);
        lbl1.setOpaque(true);
        JLabel lbl2 = new JLabel(" ");
        lbl2.setBorder(BorderFactory.createLineBorder(Color.RED));
        lbl2.setBackground(Color.RED);
        lbl2.setOpaque(true);
        JLabel lbl3 = new JLabel(" ");
        lbl3.setBorder(BorderFactory.createLineBorder(Color.RED));
        lbl3.setBackground(Color.RED);
        lbl3.setOpaque(true);
        JLabel lbl4 = new JLabel(" ");
        lbl4.setBorder(BorderFactory.createLineBorder(Color.RED));
        lbl4.setBackground(Color.RED);
        lbl4.setOpaque(true);
        // I arriba
        matrizIarriba[0][1] = lbl1;
        matrizIarriba[1][1] = lbl2;
        matrizIarriba[2][1] = lbl3;
        matrizIarriba[3][1] = lbl4;
        // I abajo
        matrizIabajo[0][1] = lbl1;
        matrizIabajo[1][1] = lbl2;
        matrizIabajo[2][1] = lbl3;
        matrizIabajo[3][1] = lbl4;
        // I derecha
        matrizIderecha[1][0] = lbl1;
        matrizIderecha[1][0] = lbl2;
        matrizIderecha[1][0] = lbl3;
        matrizIderecha[1][0] = lbl4;
        
        // I izq
        matrizIizquierda[1][0] = lbl1;
        matrizIizquierda[1][0] = lbl2;
        matrizIizquierda[1][0] = lbl3;
        matrizIizquierda[1][0] = lbl4;
        
        ///////// letra J 
        
        // J arriba
        matrizJarriba[1][1] = lbl1;
        matrizJarriba[2][1] = lbl2;
        matrizJarriba[3][1] = lbl3;
        matrizJarriba[3][0] = lbl4;
        // J abajo
        matrizJabajo[1][0] = lbl1;
        matrizJabajo[2][0] = lbl2;
        matrizJabajo[3][0] = lbl3;
        matrizJabajo[1][1] = lbl4;
        // J derecha
        matrizJderecha[0][0] = lbl1;
        matrizJderecha[1][0] = lbl2;
        matrizJderecha[1][1] = lbl3;
        matrizJderecha[1][2] = lbl4;
        
        // J izq
        matrizJizquierda[0][0] = lbl1;
        matrizJizquierda[0][1] = lbl2;
        matrizJizquierda[0][2] = lbl3;
        matrizJizquierda[1][2] = lbl4;
        
        
        
        
        
        
        
        
        //pintar tablero
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pnlPieza.add(matrizJizquierda[i][j]);
            }
            
        }
        
        
    }
    
    private void inicializar(){
        pnlPieza.setOpaque(false);
        indice =0;
        estado = 0;
        lexema = "";
        nivelActual = 1;
        nombreLetra = "";
        nombreNivel ="";
        dimX = 0;
        dimY = 0;
        tablero = "";
        contadorNivel = 1;
        cantidadNiveles = 0;
        contadorPosicionM = 0;
        listaErrores = new ArrayList();
        listaTokens = new ArrayList();
        listaLexemas = new ArrayList();
        listaTableros = new ArrayList();
        
        listaPiezas = new ArrayList();
        
        modelo=(DefaultTableModel) tblTokens.getModel(); 
        modeloPieza =(DefaultTableModel) tblPiezas.getModel();
        modeloNivel = (DefaultTableModel) tblNiveles.getModel();
        
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
                        //System.out.println("es letra se va al 1" + letra);
                        estado = 1;
                        lexema = ""+letra;
                        
                    }
                    else if(codigoAscii >= 48 && codigoAscii <= 57){
                        estado = 2;
                        lexema = ""+letra;
                        
                    }
                    else if(letra == '*' || letra == '#' || letra == '-' ){
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
                        /*if(!"".equals(tablero)){
                            System.out.println("tablero");
                            System.out.println(tablero);
                        }*/
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
                        
                        //System.out.println("identificador a aceptar: " + lexema);
                        
                        // aceptar y meter a tabla de llenado de nivel
                        Object[] niv = new Object[4];
                        niv[0]= contadorNivel++;
                        niv[1]=lexema;
                        niv[2]= dimX;
                        niv[3] = dimY;
                        
                        // guardar Tablero
                        if(!"".equals(tablero)){
                            listaTableros.add(tablero);
                            tablero = "";
                        }
                        
                        
                        //limpiarTablero 
                        
                        
                        modeloNivel.addRow(niv);
                        tblNiveles.setModel(modeloNivel);
                        
                        System.out.println("Viene un nivel " + lexema + " X:"+ dimX + " Y:" + dimY);
                        
                        
                        dimX = 0;
                        dimY = 0;
                        
                        
                        // vaciar variable tablero
                        //tablero = "";
                        
                        
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
                        
                        
                        
                        // ver variables
                        if(cantidadNiveles == 0){
                            cantidadNiveles = Integer.parseInt(lexema);
                        }
                        else if(dimY == 0){
                            dimY = Integer.parseInt(lexema);
                        }
                        else if(dimX == 0){
                            dimX = Integer.parseInt(lexema);
                        }
                        
                        lexema = "";
                        estado = 0;
                        indice--;
                    }
                    break;
                case 3:
                    /*if(!"*".equals(lexema) || !"#".equals(lexema) || !"-".equals(lexema)){
                        System.out.println("* no es igual a = " + lexema);
                        //System.out.println("caracter desconocido en matriz " + lexema);
                        //System.out.println(" y su letra " + letra);
                    }*/
                    Object[] fila2 = new Object[3];
                           
                    
                    switch (lexema) {
                        case "*":
                            tablero += lexema;
                            fila2[0] = "signo *";
                            break;
                        case "#":
                            tablero += lexema;
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
                    Object [] filaP = new Object[2];
                    Pieza p = new Pieza();
                    switch (lexema) {
                        case ">":
                            { 
                                fila[0] = "Direccion Derecha";
                                p.setNombre(nombreLetra);
                                p.setDireccion("derecha");
                                listaPiezas.add(p);
                                
                                filaP[0] = nombreLetra;
                                filaP[1] = ">";
                                modeloPieza.addRow(filaP);
                                tblPiezas.setModel(modeloPieza);
                                break;                        
                            }
                        case "<":
                            { 
                                fila[0] = "Direccion Izquierda";
                                p.setNombre(nombreLetra);
                                p.setDireccion("izquierda");
                                listaPiezas.add(p);
                                
                                filaP[0] = nombreLetra;
                                filaP[1] = "<";
                                modeloPieza.addRow(filaP);
                                tblPiezas.setModel(modeloPieza);
                                
                                break;                        
                            }
                        case "^":
                            { 
                                fila[0] = "Direccion Arriba";
                                p.setNombre(nombreLetra);
                                p.setDireccion("arriba");
                                listaPiezas.add(p);
                                
                                filaP[0] = nombreLetra;
                                filaP[1] = "^";
                                modeloPieza.addRow(filaP);
                                tblPiezas.setModel(modeloPieza);
                                
                                break;                        
                            }
                        case "v":
                            { 
                                fila[0] = "Direccion Abajo";
                                p.setNombre(nombreLetra);
                                p.setDireccion("abajo");
                                listaPiezas.add(p);
                                
                                filaP[0] = nombreLetra;
                                filaP[1] = "v";
                                modeloPieza.addRow(filaP);
                                tblPiezas.setModel(modeloPieza);
                                
                                break;                        
                            }
                        case ",":
                            {
                                fila[0] = "Coma";
                                break;
                            }
                        case "L":
                            {
                                
                                nombreLetra = lexema;
                                fila[0] = "Pieza";
                            }
                        case "I":
                            {
                                nombreLetra = lexema;
                                fila[0] = "Pieza";
                            }
                        case "J":
                            {
                                nombreLetra = lexema;
                                fila[0] = "Pieza";
                            }
                        case "O":
                            {
                                nombreLetra = lexema;
                                fila[0] = "Pieza";
                            }
                        case "S":
                            {
                                nombreLetra = lexema;
                                fila[0] = "Pieza";
                            }
                        case "Z":
                            {
                                nombreLetra = lexema;
                                fila[0] = "Pieza";
                            }
                        case "T":
                            {
                                
                                nombreLetra = lexema;
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
        pnlJuego = new javax.swing.JPanel();
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
        jScrollPane5 = new javax.swing.JScrollPane();
        tblPiezas = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNiveles = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        pnlPieza = new javax.swing.JPanel();
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

        pnlJuego.setLayout(new java.awt.GridLayout());
        getContentPane().add(pnlJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 160, 510, 510));

        jLabel2.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Siguiente Pieza");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 100, -1, -1));

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

        tblPiezas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pieza", "Direccion"
            }
        ));
        jScrollPane5.setViewportView(tblPiezas);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 100, 130, 170));

        tblNiveles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nivel", "Nombre", "X", "Y"
            }
        ));
        jScrollPane6.setViewportView(tblNiveles);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 240, 130));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Lista Niveles");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Lista Piezas");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 70, -1, -1));

        jButton4.setText("Matrix");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));
        getContentPane().add(pnlPieza, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 70, 90, 80));

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        listaTableros.add(tablero);
        pintarTablero();
        pintarFigura();
        llenarMatricesPiezas();
        System.out.println("cantidad tableros " + listaTableros.size());
        
        
    }//GEN-LAST:event_jButton4ActionPerformed
    
    public void pintarFigura(){
        DefaultTableModel tm = (DefaultTableModel) tblNiveles.getModel();
        
        //int datoX = Integer.parseInt(String.valueOf(tm.getValueAt(nivelActual-1 ,2)));
        //int datoY = Integer.parseInt(String.valueOf(tm.getValueAt(nivelActual-1 ,3)));
        pnlPieza.setLayout(new GridLayout(4, 4));
        pnlPieza.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        
//        for (int i =0; i<(4*4); i++){
//            final JLabel label = new JLabel(" ");
//            label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//           pnlPieza.add(label);
//        }
        
    }
    
    public void pintarTablero(){
        DefaultTableModel tm = (DefaultTableModel) tblNiveles.getModel();
        
        int datoX = Integer.parseInt(String.valueOf(tm.getValueAt(nivelActual-1 ,2)));
        int datoY = Integer.parseInt(String.valueOf(tm.getValueAt(nivelActual-1 ,3)));
        
        JOptionPane.showMessageDialog(null, "Dimensiones nivel: " + datoX + "," + datoY);
        pnlJuego.setLayout(new GridLayout(datoY, datoX));
        pnlJuego.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        
        
//        for (int i =0; i<(datoX*datoY); i++){
//            final JLabel label = new JLabel("#");
//            label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//            pnlJuego.add(label);
//        }
        JLabel [][] arregloTab = new JLabel[datoY][datoX];
        
        for (int i = 0; i < datoY; i++) {
            for (int j = 0; j < datoX; j++) {
                arregloTab[i][j] = new JLabel("#");
                //pnlJuego.add(arregloTab[i][j]);
            }
            
        }
        //obtener el tablero del nivel que es 
        
        String tabActual = listaTableros.get(nivelActual-1);
        
        System.out.println("Tablero Actual");
        System.out.println(tabActual);
        
        
        int contador=0;
        int tamanioTab = tabActual.length();
        //JOptionPane.showMessageDialog(null, "tamanio string " + tamanioTab);
        
        for (int i = 0; i < tabActual.length(); i++) {
            char caract = tabActual.charAt(i);
            if(caract == '*'){
                contador = 0;
                //System.out.println("encontre * en posicion: " + i);
                for (int j = 0; j < datoY; j++) {
                    for (int k = 0; k < datoX; k++) {
                        contador++;
                        if(contador == i){
                            //System.out.println("contador = " + contador +" = " + i + " en: " + j + "," + k );
                            int coord = k +1;
                            //System.out.println("posiciones: " + j + "," + coord);
                            JLabel lbl = new JLabel("*");
                            lbl.setBorder(BorderFactory.createLineBorder(Color.RED));
                            lbl.setBackground(Color.RED);
                            lbl.setOpaque(true);
                            arregloTab[j][coord] = lbl;
                            //pnlJuego.add(arregloTab[j][k]);
                        }
                    }
                }
            }
        }
        
        // pintar tablero
        
        
        for (int i = 0; i < datoY; i++) {
            for (int j = 0; j < datoX; j++) {
                pnlJuego.add(arregloTab[i][j]);
            }
            
        }
        
        
        //JButton btnTest = new JButton();
        //btnTest.setText("0");
        //pnlJuego.add(btnTest);
        
    }
    
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
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblArchivo1;
    private javax.swing.JLabel lblArchivo2;
    private javax.swing.JPanel pnlJuego;
    private javax.swing.JPanel pnlPieza;
    private javax.swing.JTable tblNiveles;
    private javax.swing.JTable tblPiezas;
    private javax.swing.JTable tblTokens;
    private javax.swing.JTextArea txtArchivo1;
    private javax.swing.JTextArea txtArchivo2;
    // End of variables declaration//GEN-END:variables
}
