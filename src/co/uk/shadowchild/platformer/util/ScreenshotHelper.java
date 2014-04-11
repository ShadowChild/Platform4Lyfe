package co.uk.shadowchild.platformer.util;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author ShadowChild
 */
public class ScreenshotHelper {

    public static void screenCap() throws IOException {

        LogHelper.getInstance().info("Saving Screenshot");

        GL11.glReadBuffer(GL11.GL_FRONT);
        int width = Display.getDisplayMode().getWidth();
        int height= Display.getDisplayMode().getHeight();
        int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
        GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer );

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        String format = "PNG"; // Example: "PNG" or "JPG"
        File file = new File("./screens/shot_" + dateFormat.format(new Date()) + ".png"); // The file to save to.
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        if(!file.getParentFile().exists()) {

            file.getParentFile().mkdirs();
        }

        for(int x = 0; x < width; x++)
            for(int y = 0; y < height; y++)
            {
                int i = (x + (width * y)) * bpp;
                int r = buffer.get(i) & 0xFF;
                int g = buffer.get(i + 1) & 0xFF;
                int b = buffer.get(i + 2) & 0xFF;
                image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
            }

        try {

            ImageIO.write(image, format, file);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
