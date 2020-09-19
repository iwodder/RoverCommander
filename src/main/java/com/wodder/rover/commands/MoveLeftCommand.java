package com.wodder.rover.commands;

import com.wodder.gui.MarsPanel;
import com.wodder.rover.Rover;

public class MoveLeftCommand implements Command {

    private final Rover rover;

    public MoveLeftCommand(Rover r) {
        this.rover = r;
    }

    @Override
    public void execute() {
        int newX = rover.getxPos() - MarsPanel.cellSize();
        if (newX < 0) {
            newX = MarsPanel.width() - MarsPanel.cellSize();
        }
        rover.setxPos(newX);
    }

    @Override
    public void undo() {
        new MoveRightCommand(rover).execute();
    }

    @Override
    public String toString() {
        return "Moving left...";
    }
}
