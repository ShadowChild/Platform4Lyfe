package co.uk.shadowchild.platformer.geom;

import java.util.Random;


/**
 * @author ShadowChild
 */
public class Box {

    public Point topLeft;
    public int width, height;

    public float red = 0F, green = 0F, blue = 0F;

    public Box(Point topLeft, int width, int height) {

        this.topLeft = topLeft;
        this.width = width;
        this.height = height;

        randomizeColours();
    }

    private void randomizeColours() {

        Random rand = new Random();
        red = rand.nextFloat();
        green = rand.nextFloat();
        blue = rand.nextFloat();
    }
}
