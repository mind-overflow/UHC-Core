package wtf.beatrice.uhccore.utils.configuration;

public enum LocalizedMessages {

    INFO("info"),
    PLAYER_POSITION("player_position"),

    WARNING_CONSOLE_ACCESS("error.console_access"),

    ERROR_CONSOLE_ACCESS_BLOCKED("error.console_access_blocked"),
    ERROR_SERVER_NOT_SET_UP("error.server_not_set_up"),


        ;

    public String path;

    LocalizedMessages(String path)
    {
        this.path = path;
    }
}
