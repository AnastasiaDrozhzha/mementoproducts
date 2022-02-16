package adrozhzha.mementoproducts.commands.inmemory;

import adrozhzha.mementoproducts.commands.Command;
import adrozhzha.mementoproducts.commands.CommandStream;
import adrozhzha.mementoproducts.commands.NothingToRedoException;
import adrozhzha.mementoproducts.commands.NothingToUndoException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InmemoryCommandStream implements CommandStream {
    private final Deque<Command<?>> undoStack = new ArrayDeque<>();
    private final Deque<Command<?>> redoStack = new ArrayDeque<>();

    private final Lock lock = new ReentrantLock();

    private volatile boolean isActive = true;

    @Override
    public <T> T execute(Command<T> command) {
        verifyStreamIsActive();
        lock.lock();
        try {
            T result = command.execute();
            undoStack.push(command);
            redoStack.clear();
            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void undo() {
        verifyStreamIsActive();
        lock.lock();
        try {
            if (undoStack.isEmpty()) {
                throw new NothingToUndoException();
            }
            Command<?> command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void redo() {
        verifyStreamIsActive();
        lock.lock();
        try {
            if (redoStack.isEmpty()) {
                throw new NothingToRedoException();
            }
            Command<?> command = redoStack.pop();
            command.redo();
            undoStack.push(command);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void dispose() {
        lock.lock();
        try {
            this.isActive = false;
            undoStack.clear();
            redoStack.clear();
        } finally {
            lock.unlock();
        }
    }

    private void verifyStreamIsActive() {
        if (!isActive) {
            throw new IllegalStateException("Command stream has been disposed.");
        }
    }
}
