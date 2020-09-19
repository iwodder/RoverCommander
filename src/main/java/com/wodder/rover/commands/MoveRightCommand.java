package com.wodder.rover.commands;

import com.wodder.gui.MarsPanel;
import com.wodder.rover.Rover;

public class MoveRightCommand implements Command {

    private final Rover rover;

    public MoveRightCommand(Rover r) {
        this.rover = r;
    }

    @Override
    public void execute() {
        int newX = rover.getxPos() + MarsPanel.cellSize();
        if (newX > MarsPanel.width() - MarsPanel.cellSize()) {
            newX = 0;
        }
        rover.setxPos(newX);
    }

    @Override
    public void undo() {
        new MoveLeftCommand(rover).execute();
    }

    @Override
    public String toString() {
        return "Moving right...";
    }
}
