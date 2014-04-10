package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.util.LogHelper;
import co.uk.shadowchild.platformer.util.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author ShadowChild
 */
public class Main extends Canvas {

    public static Main game;
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 2;

    public boolean gameRunning = false;
    public Thread gameLoop;

    private static BufferedImage background;

    public synchronized void start() {

        if(gameRunning) return;

        gameRunning = true;
        gameLoop = new GameThread();
        gameLoop.start();
    }

    public synchronized void stop() {

        if(!gameRunning) return;

        LogHelper.getInstance().info("Shutting Down");
        gameRunning = false;
        try {

            LogHelper.getInstance().info("Attempting to join threads");
            gameLoop.join(1000);
        } catch(InterruptedException e) {

            LogHelper.getInstance().error("This Shouldnt Happen", e);
        }
    }

    public Main() {

        LogHelper.getInstance().info("Started Loading Process");

        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT  * SCALE));
        this.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    }

    public static void main(String... args) {

        try {

            background = ImageIO.read(Main.class.getResourceAsStream("/background.png"));
        } catch(IOException e) {

            e.printStackTrace();
        }

        game = new Main();

        Runtime.getRuntime().addShutdownHook(new ExitThread(game));

        JFrame frame = new JFrame("Platformer");
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        EventQueue.invokeLater(() -> {

            frame.setVisible(true);
            LogHelper.getInstance().info("Opened Window");
        });

        game.start();
    }

    public void tick() {

    }

    public void render() {

        BufferStrategy strat = this.getBufferStrategy();

        if(strat != null) {

             Graphics g = strat.getDrawGraphics();

//            LogHelper.getInstance().info("RENDERING");
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            g.drawString("FPS: " + Utils.getInstance().frames, 20, 20);
            g.drawString("UPS: " + Utils.getInstance().ticks, 20, 35);

            System.out.println(getWidth() + " " + getHeight());

            g.dispose();
            strat.show();
        } else {

            this.createBufferStrategy(3);
        }
    }

    public static class ExitThread extends Thread {

        Main game;

        public ExitThread(Main game) {

            super();
            this.setName("ExitThread");
            this.game = game;
        }

        @Override
        public void run() {

            game.stop();
        }
    }
}