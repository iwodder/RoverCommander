package com.wodder.rover.commands;

import com.wodder.gui.MarsPanel;
import com.wodder.rover.Rover;

public class MoveDownCommand implements Command {

    private final Rover rover;

    public MoveDownCommand(Rover r) {
        this.rover = r;
    }

    @Override
    public void execute() {
        int newY = rover.getyPos() + MarsPanel.cellSize();
        if (newY > MarsPanel.height() - MarsPanel.cellSize()) {
            newY = 0;
        }
        rover.setyPos(newY);
    }

    @Override
    public void undo() {
        new MoveUpCommand(rover).execute();
    }

    @Override
    public String toString() {
        return "Moving down...";
    }
}
