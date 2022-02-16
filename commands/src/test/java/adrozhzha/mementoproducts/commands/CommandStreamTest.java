package adrozhzha.mementoproducts.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThrows(NothingToUndoException.class, commandStream::undo);
    }

    @Test
    public void emptyStream_onRedo_throwsException() {
        assertThrows(NothingToRedoException.class, commandStream::redo);
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
    public void execute_undecided_successful() {
        commandStream.execute(new TestCommand("A"));
        commandStream.undo();
        commandStream.redo();
        commandStream.undo();
        commandStream.redo();
        commandStream.undo();

        assertThat(commandsExecutionResult, equalTo("Execute[A]Undo[A]Redo[A]Undo[A]Redo[A]Undo[A]"));
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

        assertThrows(NothingToUndoException.class, commandStream::undo);

        assertThat(commandsExecutionResult, equalTo("Execute[A]Undo[A]"));
    }

    @Test
    public void executeA_thenRedo_throwsException() {
        commandStream.execute(new TestCommand("A"));

        assertThrows(NothingToRedoException.class, commandStream::redo);

        assertThat(commandsExecutionResult, equalTo("Execute[A]"));
    }

    @Test
    public void executeThrowsException_thenUndo_throwsException() {
        assertThrows(RuntimeException.class, () -> commandStream.execute(new ExecuteThrowsExceptionCommand()));
        assertThrows(NothingToUndoException.class, commandStream::undo);
        assertThat(commandsExecutionResult, equalTo("ExecuteEx"));
    }

    @Test
    public void undoThrowsException_thenRedoFails() {
        commandStream.execute(new UndoThrowsExceptionCommand());
        assertThrows(RuntimeException.class, commandStream::undo);
        assertThat(commandsExecutionResult, equalTo("ExecuteUndoEx"));
        assertThrows(NothingToRedoException.class, commandStream::redo);
        assertThat(commandsExecutionResult, equalTo("ExecuteUndoEx"));
    }

    @Test
    public void redoThrowsException_thenUndoFails() {
        commandStream.execute(new RedoThrowsExceptionCommand());
        commandStream.undo();
        assertThrows(RuntimeException.class, commandStream::redo);
        assertThrows(NothingToUndoException.class, commandStream::undo);
    }

    @Test
    public void dispose() {
        commandStream.execute(new TestCommand("A"));
        commandStream.dispose();
        assertThrows(IllegalStateException.class, commandStream::redo);
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

    private class ExecuteThrowsExceptionCommand implements Command<Void> {

        @Override
        public Void execute() {
            commandsExecutionResult += "ExecuteEx";
            throw new RuntimeException("Ex");
        }

        @Override
        public void undo() {
            commandsExecutionResult += "Undo";
        }

        @Override
        public void redo() {
            commandsExecutionResult += "Redo";
        }
    }

    private class UndoThrowsExceptionCommand implements Command<Void> {

        @Override
        public Void execute() {
            commandsExecutionResult += "Execute";
            return null;
        }

        @Override
        public void undo() {
            commandsExecutionResult += "UndoEx";
            throw new RuntimeException("Ex");
        }

        @Override
        public void redo() {
            commandsExecutionResult += "Redo";
        }
    }

    private class RedoThrowsExceptionCommand implements Command<Void> {
        @Override
        public Void execute() {
            commandsExecutionResult += "Execute";
            return null;
        }

        @Override
        public void undo() {
            commandsExecutionResult += "Undo";
        }

        @Override
        public void redo() {
            commandsExecutionResult += "RedoEx";
            throw new RuntimeException("Ex");
        }
    }
}
