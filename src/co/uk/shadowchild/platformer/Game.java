package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.geom.Box;
import co.uk.shadowchild.platformer.handler.ControllerInputHandler;
import co.uk.shadowchild.platformer.handler.IInputHandler;
import co.uk.shadowchild.platformer.handler.KeyInputHandler;
import co.uk.shadowchild.platformer.handler.MouseInputHandler;
import co.uk.shadowchild.platformer.render.RenderGlobal;
import co.uk.shadowchild.platformer.util.LogHelper;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ShadowChild
 */
public class Game {

    private static Game gameInstance;

    private static RenderGlobal rgInstance = RenderGlobal.instance;

    public static final int WIDTH = 1600, HEIGHT = WIDTH / 16 * 9;

    public List<IInputHandler> handlers = new ArrayList<>();

    public Game() {

        gameInstance = this;
        try {

            Controllers.create();

            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle("3D Game");
            Display.setResizable(false);
            Display.create();
        } catch(LWJGLException e) {

            e.printStackTrace();
        }
    }

    public static Game getGameInstance() {

        return gameInstance;
    }

    public void run() {

        Controllers.poll();

        boolean registered = false;
        for(int i = 0; i < Controllers.getControllerCount(); i++) {

            Controller controller = Controllers.getController(i);
            LogHelper.debug("Installed controller on index " + i + ", name = " + controller.getName());
            if(controller.getName().contains("XBOX 360") && !registered) {

                ControllerInputHandler.controller = controller;
                registered = true;
            }
        }
        Controller controller = ControllerInputHandler.controller;
        if(controller != null) {

            for(int i = 0; i < controller.getAxisCount(); i++) {

                LogHelper.debug("Xbox controller axis name for index " + i + " is " + controller.getAxisName(i));
                controller.setDeadZone(i, 0.3F);
            }
            for(int i = 0; i < controller.getButtonCount(); i++) {

                LogHelper.debug("Xbox controller button name for index " + i + " is " + controller.getButtonName(i));
            }
        }

        handlers.add(new KeyInputHandler());
        handlers.add(new MouseInputHandler());
        if(controller != null) handlers.add(new ControllerInputHandler());

        rgInstance.initGL(WIDTH, HEIGHT);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        while(!Display.isCloseRequested()) {

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

//            Box bg = new Box(new Point(0, 0), 64, 64);
//            rgInstance.renderTexturedQuad(ResourceBank.BACKGROUND_T, bg);

            for(IInputHandler handler : handlers) {

                handler.handle();
            }

            for(Box box : rgInstance.boxes) {

                rgInstance.renderQuad(box);
            }

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }
}
