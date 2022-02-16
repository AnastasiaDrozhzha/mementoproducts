package adrozhzha.mementoproducts.commands;

/**
 * Command is expected to atomically perform some amount of work or fail altogether.
 * Special care should be taken to ensure command doesn't change any state of the system in case it fails.
 *
 * @param <T>
 */
public interface Command<T> {

    T execute();

    void undo();

    void redo();
}
