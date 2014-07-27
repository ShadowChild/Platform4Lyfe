package co.uk.shadowchild.platformer.util;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;


/**
 * @author ShadowChild
 */
public class ResourceBank {

    public static final ResourceLocation BACKGROUND_RL = new ResourceLocation("textures:gui/background_tile.png");
    public static Texture BACKGROUND_T;

    public static void load() {

//        System.out.println(BACKGROUND_RL.getLocation());

        try {

            BACKGROUND_T = TextureLoader.getTexture("PNG", ResourceBank.class.getResourceAsStream(BACKGROUND_RL.getLocation()));
        } catch(IOException e) {

            e.printStackTrace();
        }
    }
}
