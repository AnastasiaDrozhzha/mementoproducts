package adrozhzha.mementoproducts.commands.inmemory;

import adrozhzha.mementoproducts.commands.CommandStream;
import adrozhzha.mementoproducts.commands.CommandStreamTest;

public class InmemoryCommandStreamTest extends CommandStreamTest {

    @Override
    protected CommandStream createCommandStream() {
        return new InmemoryCommandStream();
    }
}