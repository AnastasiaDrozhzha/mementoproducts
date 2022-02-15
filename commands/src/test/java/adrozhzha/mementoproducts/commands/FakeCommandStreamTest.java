package adrozhzha.mementoproducts.commands;

import java.util.ArrayDeque;
import java.util.Deque;

public class FakeCommandStreamTest extends CommandStreamTest {

    @Override
    protected CommandStream createCommandStream() {
        return new FakeCommandStream();
    }

    private static class FakeCommandStream extends CommandStream {

        Deque<Command<?>> undoStack = new ArrayDeque<>();
        Deque<Command<?>> redoStack = new ArrayDeque<>();

        @Override
        protected void pushRedo(Command<?> command) {
            redoStack.push(command);
        }

        @Override
        protected void pushUndo(Command<?> command) {
            undoStack.push(command);
        }

        @Override
        protected Command<?> popUndo() {
            if (undoStack.isEmpty()) {
                throw new NothingToUndoException();
            }
            return undoStack.pop();
        }

        @Override
        protected Command<?> popRedo() {
            if (redoStack.isEmpty()) {
                throw new NothingToRedoException();
            }
            return redoStack.pop();
        }

        @Override
        protected void clearRedo() {
            redoStack.clear();
        }

        @Override
        protected void lock() {

        }

        @Override
        protected void unlock() {

        }
    }
}
