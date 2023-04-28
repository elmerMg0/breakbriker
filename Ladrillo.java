import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;

/**
 * Write a description of class MapGenerator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ladrillo
{
    public int ladrillos[][];
    public int anchoLadrillo;
    public int altoLadrillo;
    Plantilla imagenAzul;
    Plantilla imagenCeleste;
    Plantilla imagenLila;
    public Ladrillo(int fila, int col,int anchoLad,int incre){
        ladrillos = new int[fila][col];   
        for(int i = 0; i< fila; i++){
            for(int j = 0 ; j < col ;j = j + incre){
                ladrillos[i][j] = 1; 
            }
        }
        anchoLadrillo = anchoLad/col;
        altoLadrillo = 150/fila;
    }

    public void dibujarLad(Graphics g,int x, int y ){
        imagenLila   = new Plantilla("ladrilloLilla.png");
        imagenAzul = new Plantilla("ladrilloAzul.png");
        imagenCeleste   = new Plantilla("ladrilloCeleste.png");

        for(int i=0;i<ladrillos.length;i++){
            for(int j = 0; j < ladrillos[0].length;j++){
                if(ladrillos[i][j]>0){
                    imagenLila.paintComponent2(g,j*anchoLadrillo+x,i*altoLadrillo+y,anchoLadrillo,altoLadrillo);
                }
            }
            imagenLila = imagenAzul;
            if(i==1){
                imagenLila = imagenCeleste;
            }
        }
    }

    public int noVisible(int valor,int f, int c){
        ladrillos[f][c] = valor;
        return valor;
    }
}
