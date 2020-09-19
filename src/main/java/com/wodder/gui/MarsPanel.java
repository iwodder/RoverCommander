package com.wodder.gui;

import com.wodder.rover.Rover;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class MarsPanel extends JPanel {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int CELL_SIZE = 20;

    private Image mars;
    private final Rover rover;

    public MarsPanel(Rover rover) throws Exception {
        super(new BorderLayout());
        URL imageUrl = this.getClass().getResource("/mars.jpeg");
        mars = ImageIO.read(imageUrl);
        mars = mars.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        this.rover = rover;
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mars, 0, 0, null);
        drawGrid(g);
        drawRover(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int x = CELL_SIZE; x <= WIDTH; x += CELL_SIZE) {
            g.drawLine(x, 0, x, HEIGHT);
        }
        for (int y = CELL_SIZE; y <= HEIGHT; y += CELL_SIZE) {
            g.drawLine(0, y, WIDTH, y);
        }
    }

    private void drawRover(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(rover.getxPos(), rover.getyPos(), rover.getWidth(), rover.getHeight());
    }

    public static int width() {
        return WIDTH;
    }

    public static int height() {
        return HEIGHT;
    }

    public static int cellSize() { return CELL_SIZE; }
}
