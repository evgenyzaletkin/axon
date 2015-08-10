package org.home.axon;

import org.axonframework.commandhandling.annotation.CommandHandler;

public class OrderCommandHandler {
    /**
     * Handles the given <code>command</code>.
     *
     * @param commandMessage The message carrying the command to process.
     * @return The result of the command processing, if any.
     * @throws Throwable any exception that occurs during command handling
     */
    @CommandHandler()
    public void handle(OrderCommand commandMessage) throws Throwable {
        throw new RuntimeException(commandMessage.toString());
    }
}
