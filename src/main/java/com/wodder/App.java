package com.wodder;

import com.wodder.gui.MainFrame;
import com.wodder.gui.MarsPanel;
import com.wodder.rover.Rover;
import com.wodder.rover.commands.RoverCommands;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class App {
    private static final int MOVE_DELAY = 15_000;

    public static void main( String[] args ) throws Exception {
        displayDialog(start());
    }

    private static void displayDialog(JFrame owner) {
        JDialog dialog = new JDialog(owner, "Instructions");
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(owner);
        dialog.add(createInstructions(), BorderLayout.CENTER);
        dialog.setVisible(true);
        dialog.pack();
    }

    private static JTextArea createInstructions() {
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setPreferredSize(new Dimension(275, 250));
        text.append("Welcome to Rover Commander!\n\n");
        text.append("Use the arrow keys to maneuver the rover.\n");
        text.append("Use Ctrl-Z to undo your last maneuver.\n\n");
        text.append("Actions are delayed just like controlling,\n  a rover on Mars!");
        return text;
    }

    private static JFrame start() throws Exception {
        Rover rov = new Rover(MarsPanel.cellSize());
        JPanel marsPanel = new MarsPanel(rov);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        RoverCommands roverCommands = new RoverCommands(listModel, marsPanel, rov);

        JFrame frame = new MainFrame("Rover Commander");
        frame.addKeyListener(roverCommands);
        frame.setFocusable(true);
        frame.add(marsPanel, BorderLayout.WEST);
        frame.add(createCommandPanel(listModel), BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);

        startTimer(roverCommands);
        return frame;
    }

    public static JPanel createCommandPanel(ListModel<String> model) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(200, 500));
        panel.setBorder(LineBorder.createBlackLineBorder());

        panel.add(createList(model), BorderLayout.CENTER);
        return panel;
    }

    public static JScrollPane createList(ListModel<String> model) {
        JList<String> commandList = new JList<>(model);
        commandList.setLayoutOrientation(JList.VERTICAL);
        commandList.setVisibleRowCount(-1);


        JScrollPane scrollPane = new JScrollPane(commandList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(100, 500));
        return scrollPane;
    }

    private static void startTimer(RoverCommands roverCommands) {
        Timer moveTimer = new Timer(MOVE_DELAY, roverCommands);
        moveTimer.start();
    }
}
