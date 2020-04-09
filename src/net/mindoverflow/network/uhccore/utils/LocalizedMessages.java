package net.mindoverflow.network.uhccore.utils;

public enum LocalizedMessages {

    INFO("info"),
    PLAYER_POSITION("player_position"),

    WARNING_CONSOLE_ACCESS("error.console_access"),

    ERROR_CONSOLE_ACCESS_BLOCKED("error.console_access_blocked");

    public String path;

    LocalizedMessages(String path)
    {
        this.path = path;
    }
}
