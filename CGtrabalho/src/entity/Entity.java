package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity{
//Atributos da classe Entity
    public int x, y;
    public int speed;
    public BufferedImage right, left, up, down, upright, upleft, downright, downleft;
    public String direction; 
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
