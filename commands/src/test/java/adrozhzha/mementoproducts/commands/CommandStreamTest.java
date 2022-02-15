package adrozhzha.mementoproducts.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public abstract class CommandStreamTest {

    CommandStream commandStream;

    String commandsExecutionResult;

    @BeforeEach
    public void init() {
        commandStream = createCommandStream();
        commandsExecutionResult = "";
    }

    protected abstract CommandStream createCommandStream();

    @Test
    public void emptyStream_onUndo_throwsException() {
        Assertions.assertThrows(NothingToUndoException.class, commandStream::undo);
    }

    @Test
    public void emptyStream_onRedo_throwsException() {
        Assertions.assertThrows(NothingToRedoException.class, commandStream::redo);
    }

    @Test
    public void execute_thenUndo_successful() {
        commandStream.execute(new TestCommand("A"));
        commandStream.undo();

        assertThat(commandsExecutionResult, equalTo("Execute[A]Undo[A]"));
    }

    @Test
    public void executeAndUndo_thenRedo_successful() {
        commandStream.execute(new TestCommand("A"));
        commandStream.undo();
        commandStream.redo();

        assertThat(commandsExecutionResult, equalTo("Execute[A]Undo[A]Redo[A]"));
    }

    @Test
    public void executeAB_thenUndoBA_successful() {
        commandStream.execute(new TestCommand("A"));
        commandStream.execute(new TestCommand("B"));
        commandStream.undo();
        commandStream.undo();

        assertThat(commandsExecutionResult, equalTo("Execute[A]Execute[B]Undo[B]Undo[A]"));
    }

    @Test
    public void executeAB_thenUndoBA_thenRedoAB_successful() {
        commandStream.execute(new TestCommand("A"));
        commandStream.execute(new TestCommand("B"));
        commandStream.undo();
        commandStream.undo();
        commandStream.redo();
        commandStream.redo();

        assertThat(commandsExecutionResult, equalTo("Execute[A]Execute[B]Undo[B]Undo[A]Redo[A]Redo[B]"));
    }

    @Test
    public void executeABC_thenUndoCB_thenRedoB_successful() {
        commandStream.execute(new TestCommand("A"));
        commandStream.execute(new TestCommand("B"));
        commandStream.execute(new TestCommand("C"));
        commandStream.undo();
        commandStream.undo();
        commandStream.redo();

        assertThat(commandsExecutionResult, equalTo("Execute[A]Execute[B]Execute[C]Undo[C]Undo[B]Redo[B]"));
    }

    @Test
    public void executeA_thenUndo2_throwsException() {
        commandStream.execute(new TestCommand("A"));
        commandStream.undo();

        Assertions.assertThrows(NothingToUndoException.class, commandStream::undo);

        assertThat(commandsExecutionResult, equalTo("Execute[A]Undo[A]"));
    }

    @Test
    public void executeA_thenRedo_throwsException() {
        commandStream.execute(new TestCommand("A"));

        Assertions.assertThrows(NothingToRedoException.class, commandStream::redo);

        assertThat(commandsExecutionResult, equalTo("Execute[A]"));
    }

    private class TestCommand implements Command<Void> {

        private final String marker;

        TestCommand(String marker) {
            this.marker = marker;
        }

        @Override
        public Void execute() {
            commandsExecutionResult += String.format("Execute[%s]", marker);
            return null;
        }

        @Override
        public void undo() {
            commandsExecutionResult += String.format("Undo[%s]", marker);
        }

        @Override
        public void redo() {
            commandsExecutionResult += String.format("Redo[%s]", marker);
        }
    }
}
