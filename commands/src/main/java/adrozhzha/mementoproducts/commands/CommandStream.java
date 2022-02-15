package adrozhzha.mementoproducts.commands;

public abstract class CommandStream {

    public <T> T execute(Command<T> command) {
        try {
            lock();
            T result = command.execute();
            pushUndo(command);
            clearRedo();
            return result;
        } finally {
          unlock();
        }
    }

    public void undo() {
        try {
            lock();
            Command<?> command = popUndo();
            command.undo();
            pushRedo(command);
        } finally {
            unlock();
        }
    }

    public void redo() {
        try {
            lock();
            Command<?> command = popRedo();
            command.redo();
            pushUndo(command);
        } finally {
            unlock();
        }
    }

    protected abstract void pushRedo(Command<?> command);

    protected abstract void pushUndo(Command<?> command);

    protected abstract Command<?> popUndo();

    protected abstract Command<?> popRedo();

    protected abstract void clearRedo();

    protected abstract void lock();

    protected abstract void unlock();

}
