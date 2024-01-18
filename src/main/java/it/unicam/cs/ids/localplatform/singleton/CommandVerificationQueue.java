package it.unicam.cs.ids.localplatform.singleton;

import it.unicam.cs.ids.localplatform.command.Command;

import java.util.LinkedList;
import java.util.Queue;

public class CommandVerificationQueue {
    private static CommandVerificationQueue instance;
    private Queue<Command> toBeVerified = new LinkedList<>();

    private CommandVerificationQueue() {
    }

    public static CommandVerificationQueue getInstance() {
        if (instance == null) {
            instance = new CommandVerificationQueue();
        }
        return instance;
    }

    public Queue<Command> getToBeVerified() {
        return toBeVerified;
    }
}
