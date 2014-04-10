package co.uk.shadowchild.platformer.render;

import co.uk.shadowchild.platformer.Main;
import co.uk.shadowchild.platformer.util.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author ShadowChild
 */
public class GlobalRenderer {

    private static BufferedImage background, player;

    public static void renderHUD(Graphics g) {

        g.drawString("FPS: " + Utils.getInstance().frames, 20, 20);
        g.drawString("UPS: " + Utils.getInstance().ticks, 20, 35);
    }

    public static void renderBackground(Graphics g, int width, int height) {

        g.drawImage(background, 0, 0, width, height, Main.getInstance().getGame());
    }

    public static void preLoadImages() throws IOException {

        background = ImageIO.read(GlobalRenderer.class.getResourceAsStream("/background.png"));
    }
}
