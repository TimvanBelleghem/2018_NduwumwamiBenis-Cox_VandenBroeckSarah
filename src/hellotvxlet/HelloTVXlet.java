package hellotvxlet;



import java.io.IOException;
import javax.tv.xlet.*;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.havi.ui.*;
import org.havi.ui.event.HActionListener;
import org.havi.ui.event.HBackgroundImageEvent;
import org.havi.ui.event.HBackgroundImageListener;
import java.awt.*;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.Image;











public class HelloTVXlet implements Xlet, ResourceClient, HBackgroundImageListener {
     HBackgroundImage image ;
     HStillImageBackgroundConfiguration hsbconfig;
     
     Toolkit toolkit;
     Image Kevin;
     Image Keeper;
    
     HSceneTemplate sceneTemplate = new HSceneTemplate ( ) ;
     HScene scene ;
     HGraphicButton Keuze1;
     HGraphicButton Keuze2;
    
    public void initXlet(XletContext ctx) throws XletStateChangeException { HScreen screen=HScreen.getDefaultHScreen();
    //Een template voor onze scene aanmaken, daarna de dimensies en locaties diefineren
    sceneTemplate.setPreference(org.havi.ui.HSceneTemplate.SCENE_SCREEN_DIMENSION,new HScreenDimension(1.0f,1.0f),org.havi.ui.HSceneTemplate.REQUIRED);
    sceneTemplate.setPreference(org.havi.ui.HSceneTemplate.SCENE_SCREEN_LOCATION,new HScreenPoint(0.0f,0.0f),org.havi.ui.HSceneTemplate.REQUIRED);
    // Een instantie van een Scene vragen aan de factory
    scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
    // Scene zichtbaar maken
    scene.validate();
    scene.setVisible(true);
        //Een achtergrond reserveren 
        HBackgroundDevice hbgDev=screen.getDefaultHBackgroundDevice(); 
        if (hbgDev.reserveDevice(this))
        {
            System.out.println("Achtergrond device succesvol gereserveerd");
        }
        // een configuratie maken voor de template
        HBackgroundConfigTemplate bgTemplate=new HBackgroundConfigTemplate();
        bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, HBackgroundConfigTemplate.REQUIRED);
        hsbconfig=(HStillImageBackgroundConfiguration) hbgDev.getBestConfiguration(bgTemplate);
       
         
        try {
            hbgDev.setBackgroundConfiguration(hsbconfig);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //Images inladen
        toolkit = Toolkit.getDefaultToolkit();
        image = new HBackgroundImage("Achtergrond.jpg");
        Kevin = toolkit.getImage("Kevin.png");
        Keeper = toolkit.getImage("Courtois.png");
        
        Keuze1 = new HGraphicButton();
        Keuze1.setGraphicContent(Kevin ,org.havi.ui.HVisible.NORMAL_STATE);
        Keuze1.setLocation(0, 0);
        Keuze1.setSize(150,200);
        Keuze1.setVisible(true);
        
        Keuze2 = new HGraphicButton();
        Keuze2.setGraphicContent(Keeper ,org.havi.ui.HVisible.NORMAL_STATE);
        Keuze2.setLocation(170, 0);
        Keuze2.setSize(150,200);
        Keuze2.setVisible(true);
        
        
        //scene toevoegen, zichtbaar maken, laden en voor de zekerheid hertekenen
        scene.add(Keuze1);
        scene.add(Keuze2);
        scene.setVisible(true);
        image.load(this); 
        scene.repaint();
        
    }   

    public void imageLoaded(HBackgroundImageEvent e) {
       System.out.println("Image succesvol geladen");
       try {
            hsbconfig.displayImage(image);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     }
    public void pauseXlet() {

       

    }

    public void startXlet() throws XletStateChangeException {

     

    }


    public boolean requestRelease(ResourceProxy proxy, Object requestData) {

        throw new UnsupportedOperationException("Not supported yet.");

    }


    public void release(ResourceProxy proxy) {

        throw new UnsupportedOperationException("Not supported yet.");

    }


    public void notifyRelease(ResourceProxy proxy) {

        throw new UnsupportedOperationException("Not supported yet.");

    }



    public void imageLoadFailed(HBackgroundImageEvent e) {

        throw new UnsupportedOperationException("Not supported yet.");

    }


  

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {

      

    }


}