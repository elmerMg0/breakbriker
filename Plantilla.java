import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Write a description of class Inicio here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plantilla extends JPanel
{
    Image imagen;
    public Plantilla(String s){
        try
        {
            imagen = ImageIO.read(new File("imagen/"+s));
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void paintComponent2(Graphics g,int x, int y,int ancho,int alto){
        g.drawImage(imagen, x, y, ancho,alto,null);
    }
    
    public void paintComponent(Graphics g){
        g.drawImage(imagen, -10, -10, null);
    }
}
