package co.uk.shadowchild.platformer;

import co.uk.shadowchild.platformer.render.GlobalRenderer;
import co.uk.shadowchild.platformer.util.LogHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


/**
 * @author ShadowChild
 */
public class Main {

    private static Game game;
    private static Main instance;
    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final int SCALE = 2;

    public boolean gameRunning = false;
    public Thread gameLoop;

    public Main() {

        LogHelper.getInstance().info("Started Loading Process");
    }

    public static void main(String... args) {

        instance = new Main();
        game = new Game(WIDTH, HEIGHT, SCALE);

        try {

            GlobalRenderer.preLoadImages();
        } catch(IOException e) {

            e.printStackTrace();
        }

        JFrame frame = new JFrame("Platformer");
        frame.addWindowListener(new ExitListener());
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

    public static class ExitListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {

            getInstance().getGame().stop();
        }
    }

    public Game getGame() {

        return game;
    }

    public static Main getInstance() {

        return instance;
    }
}