
import junit.framework.TestCase;
import java.awt.Graphics;

public class Test extends TestCase
{
    Juego juego;   
    Graphics g;
    Ladrillo ladrillo;
    protected void setUp() throws Exception{
        super.setUp();
        juego = new Juego();
        ladrillo = new Ladrillo(7,3,700,1); 
    }

    protected void tearDowm() throws Exception{
        super.setUp();
        juego = new Juego();
        ladrillo = new Ladrillo(7,3,700,1); 
    }

    public void testMoveIza(){
        assertEquals("MoverIzq", 340, juego.moverIzq() );
    }
    
    public void testMoverDer(){
        assertEquals("MoverDer",360,juego.moverDer());
    }
    
    public void testNovisible(){
        
        assertEquals("No visible",0,ladrillo.noVisible(0,1,1));
    }
    
}
