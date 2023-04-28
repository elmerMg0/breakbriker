import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Rectangle;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
/**
 * Write a description of class GamePlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Juego extends JPanel implements ActionListener,KeyListener{
    private Timer timer;
        private int ritmo = 0;
    private int nroJugador = 0;
    private int nroLadrillos = 27;  
    private int balonPosX = 400;
    private int balonPosY = 480;
    private int balonDirX = +1;
    private int balonDirY = -2;
    private Ladrillo ladrillos;
    private Jugador jugador;
    private int cantJugadores = 0 ;
    private int barraPosX = 350;
    private int puntuacion = 0;
    private Plantilla fondo;
    private Plantilla barra;
    private Plantilla balon;
    ArrayList<Jugador> puntajes;
    boolean nivel2 = false;
    boolean registro = false;
    int nroVidas = 3;
    boolean play = false;
    JTextField texto;
    JButton    aceptar;
    int puntajeMax;
    public Juego(){ 
        setFocusable(true);//
        addKeyListener(this);
        setLayout(null);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(ritmo,this);
        ladrillos = new Ladrillo(3,9,700,1);
        fondo = new Plantilla("fondo102.jpg");
        puntajes = new ArrayList<>();
        texto = new JTextField();
        texto.setText("Su nombre aqui");
        aceptar = new JButton("Registrarse!");
        aceptar.addActionListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        barra = new Plantilla("cohete.png");
        balon = new Plantilla("astronauta.png");        

        fondo.paintComponent2(g,-10,-10,700,600);      
        barra.paintComponent2(g,barraPosX,510,150,40);
        balon.paintComponent2(g,balonPosX,balonPosY,20,40);

        if(nivel2){
            ladrillos.dibujarLad(g,80,50);
        }else{
            ladrillos.dibujarLad(g,0,0);
        }

        g.setColor(Color.white);
        g.setPaintMode();
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("NroVidas:"+ nroVidas, 560, 30);

        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("Puntuacion:"+puntuacion , 10, 30);

        if(balonPosY >= 570){
            balonFueraLim(g);
        }
        if(nroLadrillos<=0){
            ganador(g);
        }
        if(nroVidas <= 0){
            vidaAgotada(g);
        }
    }

    public void balonFueraLim(Graphics g){
        play = false;
        balonDirX = 0;
        balonDirY = 0;
        g.setColor(Color.RED);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Perdiste!!,Puntaje: "+puntuacion,200,300 );
        g.setFont(new Font("Arial",Font.BOLD,25));
        g.drawString("Presione ENTER para volver a intentarlo",100,350 );
    }

    public void ganador(Graphics g){
        play = false;
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("GANASTE!!,Puntaje perfecto: "+puntuacion,105,300 );
        g.setFont(new Font("Arial",Font.BOLD,25));
        nivel2 = true;
        if(puntuacion != 2700){ 
            g.drawString("Para volver a empezar, presione ENTER",80,350 );   
        }else{ 
            g.drawString("¿Quiere jugar el siguiente nivel?, presione ENTER",55,350 ); 
        }  

        balonDirX = 0;
        balonDirY = 0;
    }

    public void vidaAgotada(Graphics g){
        play = false;
        g.setColor(Color.red);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Tus vidas se terminaron",180,300 );
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,25));
        g.drawString("¿Quiere cargar su nroVidad?, presione ENTER",50,350 );
    }

    public int moverIzq(){
        barraPosX -= 10;
        if(!play){
            balonPosX  -= 10 ;
        } 
        return barraPosX;
    }

    public int moverDer(){
        barraPosX += 10;
        if(!play){
            balonPosX += 10;
        }
        return barraPosX;
    }

    @Override
    public void keyPressed(KeyEvent e){
        timer.start();
        if(e.getKeyCode()==KeyEvent.VK_C){
            play = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(barraPosX <= 0){
                barraPosX = 0;
            }else{
                moverIzq();
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(barraPosX >= 540){
                barraPosX = 540;
            }else
                moverDer();
        }    
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            reset();
        }
    }

    private void ponerPuntaje(){
        nroJugador = cantJugadores-1;
        Jugador j  =  puntajes.get(nroJugador);
        j.setScore(puntajeMax);

    }

    public void reset(){
        int ale;
        if(!play){
            if(nroVidas <= 0){
                if(nivel2){
                    nivel2 = false;
                }
                nroVidas = 3;
            }else{
                if(puntuacion!=2700 && balonPosY>=570){
                    nroVidas--;
                }
            }     

            if(puntajeMax < puntuacion){
                puntajeMax = puntuacion;
            }
            ponerPuntaje();

            balonPosX = 400;
            balonPosY = 480;

            ale = (int)(Math.random()*3)-1;
            balonDirX = ale;
            balonDirY = -2;
            barraPosX = 320;
            if(nivel2 && puntuacion!=1200){
                ladrillos = new Ladrillo(3,8,617,2);
                nroLadrillos = 12;
            }else{
                ladrillos = new Ladrillo(3,9,700,1);
                nroLadrillos = 27;
                nivel2 = false;
            }
            puntuacion = 0;
        }
    }

    public void agregarJugador(){
        String cad = texto.getText();     
        if(texto.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe poner su nombre o apodo");
        }else{
            Jugador jug = new Jugador(cad);
            puntajes.add(jug);
            cantJugadores++;
            JOptionPane.showMessageDialog(null, "Registro completo, bienvenido:"+" "+jug.getName() );
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == aceptar){
            agregarJugador();
            registro = true;            
        }

        if(play){
            if(balonPosX <= 0){
                balonDirX = -  balonDirX;
            }
            if(balonPosX >= 660){
                balonDirX = -  balonDirX;
            }
            if(balonPosY <= 0){
                balonDirY = - balonDirY;
            }

            Rectangle balonFig = new Rectangle(balonPosX,balonPosY,20,40); 
            Rectangle barra = new Rectangle(barraPosX,520,140,30);

            if(balonFig.intersects(barra)){               
                balonDirY= - balonDirY;
            }
            A:for(int i = 0; i< ladrillos.ladrillos.length;i++){
                for(int j = 0; j < ladrillos.ladrillos[0].length;j++){
                    if(ladrillos.ladrillos[i][j] > 0){
                        int ancho = ladrillos.anchoLadrillo;
                        int alto = ladrillos.altoLadrillo; 
                        int x,y;
                        if(nivel2){
                            x = 80; y= 50;
                        }else{
                            x = 0; y = 0;
                        }
                        int posladrilloX =j*ancho+x;
                        int posladrilloY =i*alto+y;

                        Rectangle ladrillo  = new Rectangle(posladrilloX, posladrilloY,ancho,alto);
                        if(balonFig.intersects(ladrillo)){
                            if(balonDirX == 0 ){
                                balonDirX = -1;
                            }
                            ladrillos.noVisible(0,i,j);
                            nroLadrillos--;
                            puntuacion += 100;
                            if(balonPosX+19 <= posladrilloX || balonPosX+1 >= posladrilloX+ancho ){
                                balonDirX= - balonDirX;
                            }
                            else{
                                if(balonPosY <= posladrilloY + alto ){
                                    balonDirY = - balonDirY;
                                }                      
                            }

                            break A; //rompe la iteracion del bucle 
                        }
                    }
                }
            }
            balonPosX = balonPosX +  balonDirX;
            balonPosY = balonPosY +  balonDirY;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void keyTyped(KeyEvent e){

    }

}

