package adrozhzha.mementoproducts.commands;

public interface Command<T> {

    T execute();

    void undo();

    void redo();
}
