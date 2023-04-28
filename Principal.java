import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Font;
/**
 * Write a description of class Principal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Principal extends JFrame implements ActionListener
{
    Plantilla ayuda;
    Juego juego;
    Plantilla inicio;
    Plantilla mejPun;

    JButton atras;
    JButton jugar;
    JButton help;

    JLabel etiqueta;
    JLabel etiqueta2;
    JLabel etiqueta3;
    JButton top;
    JButton aceptarF;
    
    JTextField texto;  
    ArrayList<Jugador> puntajesF;
    public Principal(){
        setSize(700,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        iniciar();
    }

    public void iniciar(){     
        juego = new Juego();
        juego.setVisible(false);
        juego.setBounds(0, 0, 700, 600);

        mejPun = new Plantilla("fondoTop.jpg");
        mejPun.setVisible(false);
        mejPun.setBounds(0, 0, 700, 600);

        puntajesF = new ArrayList<>();

        etiqueta = new JLabel();
        etiqueta2 = new JLabel();
        etiqueta3 = new JLabel();

        ponerComponentesAceptar();
        add(aceptarF);

        inicio = new Plantilla("fondoOficial.jpg");
        inicio.setVisible(true);
        inicio.setBounds(0, 0, 700, 600);

        ayuda = new Plantilla("FondoAyuda.jpg");
        ayuda.setBounds(0, 0, 700, 600);
        ayuda.setVisible(false);
        ayuda.setLayout(null);

        ponerComponentesAtras();
        ponerComponentesJugar();
        ponerComponentesHelp();
        ponerComponentesTop();

        ponerComponentesEtiqueta();
        //botones
        add(atras);
        add(jugar);
        add(help);
        add(top);
        //paneles

        add(inicio);
        add(juego);
        add(ayuda);
        ayuda.add(etiqueta);
        ayuda.add(etiqueta2);
        add(mejPun);
        //textFi
        texto  = juego.texto;
        texto.setBounds(573, 500, 110, 30);
        add(texto);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == top){
            actualizarMejPun();
            actualizarTop();

        }
        if(e.getSource() == jugar){
            if(juego.registro == true){
                actualizarJugar();
            }else{
                JOptionPane.showMessageDialog(null, "Para jugar, primero debe registrarse!");
            }

        }

        if(e.getSource() == help){
            actualizarHelp();

        }

        if(juego.isVisible()&& e.getSource()==atras){
            actualizarAtras1();
        }

        if(ayuda.isVisible()&& e.getSource()==atras || mejPun.isVisible()&& e.getSource()==atras  ){
            actualizarAtras2();
        }
    }

    private void actualizarTop(){
        for(Jugador j : juego.puntajes){
            puntajesF.add(j);
        }
        ordenarLista(puntajesF);
        imprimirPuntajes(puntajesF);
    }

    public void ordenarLista(ArrayList<Jugador> mejores){
        for(int i = 0 ;i < mejores.size()-1;i++){
            for(int j = 0;j <mejores.size()-1;j++){
                Jugador jugadorA = mejores.get(j);
                int actual = mejores.get(j).getPuntaje();
                int siguiente = mejores.get(j+1).getPuntaje();      
                if( siguiente > actual){
                    mejores.set(j,mejores.get(j+1));
                    mejores.set(j+1,jugadorA);

                }
            }
        }
    }

    private void actualizarMejPun(){
        mejPun.setVisible(true);
        texto.setVisible(false);
        juego.setVisible(false);
        inicio.setVisible(false);
        ayuda.setVisible(false);
        help.setVisible(false);
        jugar.setVisible(false);
        atras.setVisible(true);
        top.setVisible(false);
        texto.setVisible(false);
        aceptarF.setVisible(false);
    }

    private void imprimirPuntajes(ArrayList<Jugador> mejores){
        String showMejores ="<html>";
        int cont=1;
        for(Jugador j : mejores){
            showMejores = showMejores+"<p>"+j.toString()+"   "+" Obteniendo el "+cont+" Lugar!" +"<html>";
            cont++;
        }
        etiqueta3.setText(showMejores);
        etiqueta3.setFont(new Font("arial",Font.BOLD,16));
        etiqueta3.setOpaque(true);
        etiqueta3.setBackground(Color.white);
        etiqueta3.setForeground(Color.black);
        mejPun.add(etiqueta3);
        puntajesF.clear();
    }

    private void actualizarAtras2(){
        texto.setVisible(true);
        juego.setVisible(false);
        inicio.setVisible(true);
        ayuda.setVisible(false);
        help.setVisible(true);
        jugar.setVisible(true);
        atras.setVisible(false);
        top.setVisible(true);
        texto.setVisible(true);
        aceptarF.setVisible(true);
    }

    private void ponerComponentesAceptar(){
        aceptarF = juego.aceptar;
        aceptarF.setBounds(573, 530, 110, 30);
        aceptarF.setBackground(Color.black);
        aceptarF.setForeground(Color.white);
    }

    private void ponerComponentesTop(){
        top = new JButton("top");
        top.addActionListener(this);
        top.setBounds(594, 0, 89, 27);
        top.setBackground(Color.black);
        top.setForeground(Color.white);
    }

    private void actualizarHelp(){
        inicio.setVisible(false);
        ayuda.setVisible(true);
        juego.setVisible(false);
        help.setVisible(false);
        jugar.setVisible(false);
        atras.setVisible(true);
        texto.setVisible(false);
        top.setVisible(false);
        aceptarF.setVisible(false);
    }

    private void actualizarAtras1(){
        juego.play = false;
        juego.nivel2 = false;
        juego.reset();
        juego.puntajeMax = 0; 
        juego.nroVidas = 3;

        texto.setVisible(true);
        juego.setVisible(false);
        inicio.setVisible(true);
        ayuda.setVisible(false);
        help.setVisible(true);
        jugar.setVisible(true);
        atras.setVisible(false);
        top.setVisible(true);
        texto.setVisible(true);
        aceptarF.setVisible(true);
    }

    private void actualizarJugar(){
        juego.setVisible(true);
        inicio.setVisible(false);
        help.setVisible(false);
        help.setVisible(false);
        jugar.setVisible(false);
        atras.setVisible(true);
        texto.setVisible(false);
        top.setVisible(false);
        aceptarF.setVisible(false);
        juego.registro = false;
    }

    private void ponerComponentesEtiqueta(){
        etiqueta.setFont(new Font("arial",Font.BOLD,20));
        etiqueta.setText("<html>El el objetivo del juego es "+ 
            "destruir todos los ladrillos "+
            "haciendo rebotar repetidamente el "+ 
            "astronauta en la nave, si "+
            "la nave del jugador falla el rebote "+ 
            "del astronauta, perderá una vida."+ 
            "<html>");
        etiqueta.setOpaque(true);
        etiqueta.setBackground(Color.white);
        etiqueta.setBounds(290, 420, 400, 140);
        etiqueta.setForeground(Color.black);

        etiqueta2.setFont(new Font("arial",Font.BOLD,20));
        etiqueta2.setText("<html>Reglas de juego. Usted contara con 3 vidas. Para disparar al astronauta al espacio utilice"+
            "la letra C, para mover la nave "+
            "utilice la tecla -Izq o Der- y para volver a jugar utilice la tecla ENTER.<html> ");
        etiqueta2.setBounds(10, 0, 650, 95);
        etiqueta2.setForeground(Color.WHITE);
    }

    private void ponerComponentesAtras(){
        atras = new JButton("Atras");
        atras.setBounds(0, 532, 83, 27);
        atras.addActionListener(this);
        atras.setVisible(false);
        atras.setForeground(Color.white);
        atras.setBackground(Color.black);
    }

    private void ponerComponentesJugar(){
        jugar = new JButton("JUGAR");
        jugar.addActionListener(this);
        jugar.setBounds(293, 200, 80, 40);
        jugar.setForeground(Color.white);
        jugar.setBackground(new Color(175,216,220));
    }

    private void ponerComponentesHelp(){
        help = new JButton("Ayuda");
        help.addActionListener(this);
        help.setBounds(0, 00, 89, 27);
        help.setForeground(Color.white);
        help.setBackground(Color.black);
    }
}
