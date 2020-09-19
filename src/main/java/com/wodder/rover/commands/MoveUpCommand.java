package com.wodder.rover.commands;

import com.wodder.gui.MarsPanel;
import com.wodder.rover.Rover;

public class MoveUpCommand implements Command {

    private final Rover rover;

    public MoveUpCommand(Rover r) {
        this.rover = r;
    }

    @Override
    public void execute() {
        int newY = rover.getyPos() - MarsPanel.cellSize();
        if (newY < 0) {
            newY = MarsPanel.height() - MarsPanel.cellSize();
        }
        rover.setyPos(newY);
    }

    @Override
    public void undo() {
        new MoveDownCommand(rover).execute();
    }

    @Override
    public String toString() {
        return "Moving up...";
    }
}
