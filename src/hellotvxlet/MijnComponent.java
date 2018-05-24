package hellotvxlet;

import org.havi.ui.*;
import java.awt.*;

// Klasse van Hcomponent overerven
public class MijnComponent extends HComponent
{
    private Image bmap;
    private MediaTracker mtrack;

    public MijnComponent (String bitmapnaam, int x, int y)
    {
        bmap = this.getToolkit().getImage(bitmapnaam);
        mtrack = new MediaTracker(this);
        mtrack.addImage(bmap, 0);
        try
        {
            mtrack.waitForAll(); // WACHT TOT ALLE BITMAPS GELADEN ZIJN
        }
        catch (Exception e)
            {
                System.out.println(e.toString());
            }
        this.setBounds(x, y, bmap.getWidth(null), bmap.getWidth(null));
    }
    
    public MijnComponent(int xPos, int yPos, int height, int width)
    {
        this.setBounds(xPos, yPos, height, width);
    }
    
    // Paint methode overriden
    public void paint(Graphics g)
    {
        g.drawImage(bmap, 0, 0, null);
    }
}