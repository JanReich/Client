package main;

import com.sun.prism.Image;
import graphics.interfaces.BasicInteractableObject;
import toolBox.DrawHelper;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;


public class ReadyButton implements BasicInteractableObject {

    private double x;
    private double y;
    private double width = 300;
    private double heigth = 150;
    private BufferedImage image;



    public ReadyButton(double x, double y) {

        this.x = x;
        this.y = y;

        try {

            image = ImageIO.read(new File("res/images/rdyButton.png"));
        } catch (Exception e) {


        }
    }


    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }

    public void mouseReleased(MouseEvent e) {
        if (e.getX() > x && e.getX() < x + width && e.getY() > y && e.getY() < y + heigth) {
            //Jan schreibt hier was lustiges rein!
        }

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void draw(DrawHelper draw) {

        draw.drawImage(image, 100, 100, 300, 150);
    }
}