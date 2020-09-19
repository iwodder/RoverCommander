package com.wodder.rover.commands;

import com.wodder.rover.Rover;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class RoverCommands implements ActionListener, KeyListener {

    private final List<Command> commands;
    private final DefaultListModel<String> commandList;
    private final JPanel panel;
    private final Rover rover;
    private Command lastCmd;
    private boolean undo = false;

    public RoverCommands(DefaultListModel<String> commandList, JPanel panel, Rover rover) {
        this.commandList = commandList;
        this.panel = panel;
        this.rover = rover;
        this.commands = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (undo) {
            lastCmd.undo();
            undo = false;
            lastCmd = null;
        } else if (!commands.isEmpty()) {
            lastCmd = commands.remove(0);
            lastCmd.execute();
        }
        if (!commandList.isEmpty()) {
            commandList.removeElementAt(0);
        }
        panel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            addCommand(new MoveRightCommand(rover));
        } else if (key == KeyEvent.VK_LEFT) {
            addCommand(new MoveLeftCommand(rover));
        } else if (key == KeyEvent.VK_UP) {
            addCommand(new MoveUpCommand(rover));
        } else if (key == KeyEvent.VK_DOWN) {
            addCommand(new MoveDownCommand(rover));
        } else if (key == KeyEvent.VK_Z && e.isControlDown()) {
            if (lastCmd != null) {
                undo = true;
                commandList.addElement("Undo: " + lastCmd.toString());
            }
        }
    }

    public void addCommand(Command c) {
        commands.add(c);
        commandList.addElement(c.toString());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
