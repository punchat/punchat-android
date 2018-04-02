package com.punchat.navigation;

import ru.terrakok.cicerone.commands.Command;

/**
 * @author Alexander Artemov
 */
public class SystemLocalizedMessage implements Command {

    private int messageId;

    public SystemLocalizedMessage(int messageId) {
        this.messageId = messageId;
    }

    public int getMessageId() {
        return messageId;
    }
}
