package adrozhzha.mementoproducts.commands;

public interface CommandStream {
    <T> T execute(Command<T> command);

    void undo();

    void redo();

    void dispose();
}
