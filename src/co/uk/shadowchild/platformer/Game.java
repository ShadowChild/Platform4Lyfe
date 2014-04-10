package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.render.GlobalRenderer;
import co.uk.shadowchild.platformer.util.LogHelper;

import java.awt.*;
import java.awt.image.BufferStrategy;


/**
 * @author ShadowChild
 */
public class Game extends Canvas {

    public Game(int width, int height, int scale) {

        this.setPreferredSize(new Dimension(width * scale, height * scale));
        this.setMaximumSize(new Dimension(width * scale, height * scale));
        this.setMinimumSize(new Dimension(width * scale, height * scale));
    }

    public synchronized void start() {

        Main main = Main.getInstance();

        if(main.gameRunning) return;

        main.gameRunning = true;
        main.gameLoop = new GameThread();
        main.gameLoop.start();
    }

    public synchronized void stop() {

        Main main = Main.getInstance();

        if(!main.gameRunning) return;

        LogHelper.getInstance().info("Shutting Down");
        main.gameRunning = false;
        try {

            main.gameLoop.join(1000);
        } catch(InterruptedException e) {

            LogHelper.getInstance().error("This Shouldnt Happen", e);
        }
    }

    public void tick() {

    }

    public void render() {

        BufferStrategy strat = this.getBufferStrategy();

        if(strat != null) {

            Graphics g = strat.getDrawGraphics();

            GlobalRenderer.renderBackground(g, getWidth(), getHeight());
            //            LogHelper.getInstance().info("RENDERING");
            GlobalRenderer.renderHUD(g);

            //            System.out.println(getWidth() + " " + getHeight());

            g.dispose();
            strat.show();
        } else {

            this.createBufferStrategy(3);
        }
    }
}
