import com.apple.eawt.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * La classe Main créee une instance de la GUI et permet de lancer le programmer
 * Les quelques lignes de code servent à adapater la GUI Swing
 * pour MacOS.
 */
public class Main {

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "Lambda");

        InputStream imgStream = Main.class.getResourceAsStream("logo.png");
        BufferedImage myImg = null;
        try {
            myImg = ImageIO.read(imgStream);
        } catch (IOException e) {
            System.out.println("Image not found");
        }

        JFrame app = new LambdaInterpreterGUI();

        Application macApp = Application.getApplication();
        macApp.setAboutHandler(aboutEvent -> JOptionPane.showMessageDialog(app, "Lambda Intrepeter By AUBRY DUBOIS POIVEY SCHERSACH", "About us", JOptionPane.PLAIN_MESSAGE));

        macApp.setDockIconImage(myImg);
        app.setIconImage(myImg);
        app.setVisible(true);
    }
}
