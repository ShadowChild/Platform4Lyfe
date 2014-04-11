package co.uk.shadowchild.platformer.render;

import java.awt.image.BufferedImage;


/**
 * @author ShadowChild
 */
public class GlobalRenderer {

    private static BufferedImage sprites;
    private static BufferedImage background;
    public static BufferedImage player;

    //    public static void renderHUD(Graphics g) {
    //
    //        g.drawString("FPS: " + Utils.getInstance().frames, 20, 20);
    //        g.drawString("UPS: " + Utils.getInstance().ticks, 20, 35);
    //    }
    //
    //    public static void renderBackground(Graphics g, int width, int height) {
    //
    //        g.drawImage(background, 0, 0, width, height, Launch.getInstance().getGame());
    //    }
    //
    //    public static void preLoadImages() throws IOException {
    //
    //        background = ImageIO.read(GlobalRenderer.class.getResourceAsStream("/background.png"));
    //        sprites = ImageIO.read(GlobalRenderer.class.getResourceAsStream("/spriteSheet.png"));
    //
    //        player = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
    //
    //        Graphics g = player.getGraphics();
    //
    //        g.drawImage(sprites, 0, 0, 64, 64, Main.getInstance().getGame());
    //
    ////        g.dispose();
    //    }
    //
    //    public static void renderPlayer(Graphics g, Player player) {
    //
    //        g.drawImage(player.getPlayerImage(), player.getXCoord(), player.getYCoord(), Main.getInstance().getGame());
    //    }
}
