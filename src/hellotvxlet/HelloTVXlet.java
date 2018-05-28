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
import org.dvb.ui.DVBColor;











public class HelloTVXlet implements Xlet, ResourceClient, HBackgroundImageListener, HActionListener {
     HBackgroundImage image ;
     HStillImageBackgroundConfiguration hsbconfig;
     
     Toolkit toolkit;
     Image Kevin;
     Image Keeper;
     Image Hazard;
     Image Lukaku;
     Image Vertongen;
     Image Mertens;
    
     HSceneTemplate sceneTemplate = new HSceneTemplate ( ) ;
     HScene scene ;
     
     HStaticText Gekozenspeler; //promptekst dat gekozen speler laat zien
     HGraphicButton Keuze1;
     HGraphicButton Keuze2;
     HGraphicButton Keuze3;
     HGraphicButton Keuze4;
     HGraphicButton Keuze5;
     HGraphicButton Keuze6;
     
     
    
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
        Hazard = toolkit.getImage("Hazard.png");
        Lukaku = toolkit.getImage("Lukaku.png");
        Vertongen = toolkit.getImage("vertongen.png");
        Mertens = toolkit.getImage("mertens.png");
        
        Keuze1 = new HGraphicButton();
        Keuze1.setGraphicContent(Kevin ,org.havi.ui.HVisible.NORMAL_STATE);
        Keuze1.setLocation(60, 50);
        Keuze1.setSize(150,200);
        
        Keuze2 = new HGraphicButton();
        Keuze2.setGraphicContent(Keeper ,org.havi.ui.HVisible.NORMAL_STATE);
        Keuze2.setLocation(280, 50);
        Keuze2.setSize(150,200);
        Keuze2.setVisible(true);
        
        Keuze3 = new HGraphicButton();
        Keuze3.setGraphicContent(Hazard, org.havi.ui.HVisible.NORMAL_STATE);
        Keuze3.setLocation(500,50);
        Keuze3.setSize(150,200);
        
        Keuze4 = new HGraphicButton();
        Keuze4.setGraphicContent(Lukaku, org.havi.ui.HVisible.NORMAL_STATE);
        Keuze4.setLocation(60,320);
        Keuze4.setSize(150,200);
        
        Keuze5 = new HGraphicButton();
        Keuze5.setGraphicContent(Vertongen, org.havi.ui.HVisible.NORMAL_STATE);
        Keuze5.setLocation(280,320);
        Keuze5.setSize(150,200);
        
        
        Keuze6 = new HGraphicButton();
        Keuze6.setGraphicContent(Mertens, org.havi.ui.HVisible.NORMAL_STATE);
        Keuze6.setLocation(500,320);
        Keuze6.setSize(150,200);
        
        Keuze1.setFocusTraversal(null, Keuze4, Keuze6, Keuze2); //op, neer, links, rechts
        Keuze2.setFocusTraversal(null,Keuze5,Keuze1,Keuze3);
        Keuze3.setFocusTraversal(null, Keuze6, Keuze2, Keuze4);
        Keuze4.setFocusTraversal(Keuze1, null, Keuze3, Keuze5);
        Keuze5.setFocusTraversal(Keuze2, null, Keuze4, Keuze6);
        Keuze6.setFocusTraversal(Keuze3, null, Keuze5, Keuze1);
        
        //scene toevoegen, zichtbaar maken, laden en voor de zekerheid hertekenen
        scene.add(Keuze1);
        scene.add(Keuze2);
        scene.add(Keuze3);
        scene.add(Keuze4);
        scene.add(Keuze5);
        scene.add(Keuze6);
        
        image.load(this); 
        
        
        Keuze1.requestFocus();
        
        Keuze1.setActionCommand("keuze1");
        Keuze2.setActionCommand("keuze2");
        Keuze3.setActionCommand("keuze3");
        Keuze4.setActionCommand("keuze4");
        Keuze5.setActionCommand("keuze5");
        Keuze6.setActionCommand("keuze6");
        
        Keuze1.addHActionListener(this);
        Keuze2.addHActionListener(this);
        Keuze3.addHActionListener(this);
        Keuze4.addHActionListener(this);
        Keuze5.addHActionListener(this);
        Keuze6.addHActionListener(this);
        
        
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
    scene.setVisible(true);
    scene.repaint();

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

    public void actionPerformed(ActionEvent e) {
        String Keuze = e.getActionCommand();
        
        if(Keuze == "keuze1"){
            Keuze1.setLocation(280,120);
            Keuze1.setBackground(new DVBColor(0,255,0,179));
            Gekozenspeler = new HStaticText("Kevin de Bruyne is jouw Rode Duivel van de match!", 115, 330, 485, 30); //x, y, b ,h
            Gekozenspeler.setVerticalAlignment(HVisible.VALIGN_TOP);
            Gekozenspeler.setHorizontalAlignment (HVisible.HALIGN_LEFT);
            Gekozenspeler.setBackground(new DVBColor(0,0,0,179));
            Gekozenspeler.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            scene.add(Gekozenspeler);
            scene.remove(Keuze2);
            scene.remove(Keuze3);
            scene.remove(Keuze4);
            scene.remove(Keuze5);
            scene.remove(Keuze6);
            
            scene.setVisible(true);
            scene.repaint();
        
            
        }
        else if(Keuze == "keuze2"){
            Keuze2.setLocation(280,120);
            Keuze2.setBackground(new DVBColor(0,255,0,179));
            Gekozenspeler = new HStaticText("Thibaut Courtois is jouw Rode Duivel van de match!",115, 330, 485, 30); //x, y, b ,h
            Gekozenspeler.setVerticalAlignment(HVisible.VALIGN_TOP);
            Gekozenspeler.setHorizontalAlignment (HVisible.HALIGN_LEFT);
            Gekozenspeler.setBackground(new DVBColor(0,0,0,179));
            Gekozenspeler.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            scene.add(Gekozenspeler);
            scene.remove(Keuze1);
            scene.remove(Keuze3);
            scene.remove(Keuze4);
            scene.remove(Keuze5);
            scene.remove(Keuze6);
        
            scene.setVisible(true);
            scene.repaint();
        }
        else if(Keuze == "keuze3"){
            Keuze3.setLocation(280,120);
            Keuze3.setBackground(new DVBColor(0,255,0,179));
            Gekozenspeler = new HStaticText("Eden Hazard is jouw Rode Duivel van de match!", 115, 330, 450, 30);
            Gekozenspeler.setVerticalAlignment(HVisible.VALIGN_TOP);
            Gekozenspeler.setHorizontalAlignment (HVisible.HALIGN_LEFT);
            Gekozenspeler.setBackground(new DVBColor(0,0,0,179));
            Gekozenspeler.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            scene.add(Gekozenspeler);
            scene.remove(Keuze1);
            scene.remove(Keuze2);
            scene.remove(Keuze4);
            scene.remove(Keuze5);
            scene.remove(Keuze6);
        
            scene.setVisible(true);
            scene.repaint();
            
    }
    else if(Keuze == "keuze4"){
            Keuze4.setLocation(280,120);
            Keuze4.setBackground(new DVBColor(0,255,0,179));
            Gekozenspeler = new HStaticText("Romelu Lukaku is jouw Rode Duivel van de match!", 115, 330, 480, 30);
            Gekozenspeler.setVerticalAlignment(HVisible.VALIGN_TOP);
            Gekozenspeler.setHorizontalAlignment (HVisible.HALIGN_LEFT);
            Gekozenspeler.setBackground(new DVBColor(0,0,0,179));
            Gekozenspeler.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            scene.add(Gekozenspeler);
            scene.remove(Keuze1);
            scene.remove(Keuze2);
            scene.remove(Keuze3);
            scene.remove(Keuze5);
            scene.remove(Keuze6);
        
            scene.setVisible(true);
            scene.repaint();
            
    }
    else if(Keuze == "keuze5"){
            Keuze5.setLocation(280,120);
            Keuze5.setBackground(new DVBColor(0,255,0,179));
            Gekozenspeler = new HStaticText("Jan Vertonghen is jouw Rode Duivel van de match!", 115, 330, 480, 30);
            Gekozenspeler.setVerticalAlignment(HVisible.VALIGN_TOP);
            Gekozenspeler.setHorizontalAlignment (HVisible.HALIGN_LEFT);
            Gekozenspeler.setBackground(new DVBColor(0,0,0,179));
            Gekozenspeler.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            scene.add(Gekozenspeler);
            scene.remove(Keuze1);
            scene.remove(Keuze2);
            scene.remove(Keuze3);
            scene.remove(Keuze4);
            scene.remove(Keuze6);
        
            scene.setVisible(true);
            scene.repaint();
            
    }else if(Keuze == "keuze6"){
            Keuze6.setLocation(280,120);
            Keuze6.setBackground(new DVBColor(0,255,0,179));
            Gekozenspeler = new HStaticText("Dries Mertens is jouw Rode Duivel van de match!", 115, 330, 465, 30);
            Gekozenspeler.setVerticalAlignment(HVisible.VALIGN_TOP);
            Gekozenspeler.setHorizontalAlignment (HVisible.HALIGN_LEFT);
            Gekozenspeler.setBackground(new DVBColor(0,0,0,179));
            Gekozenspeler.setBackgroundMode(HVisible.BACKGROUND_FILL);
            
            scene.add(Gekozenspeler);
            scene.remove(Keuze1);
            scene.remove(Keuze2);
            scene.remove(Keuze3);
            scene.remove(Keuze5);
            scene.remove(Keuze4);
        
            scene.setVisible(true);
            scene.repaint();
            
    }
   


}
}