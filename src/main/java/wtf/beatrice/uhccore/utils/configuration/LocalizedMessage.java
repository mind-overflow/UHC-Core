package wtf.beatrice.uhccore.utils.configuration;

public enum LocalizedMessage {

    INFO("info"),
    PLAYER_POSITION("player_position"),

    WARNING_CONSOLE_ACCESS("error.console_access"),

    ERROR_CONSOLE_ACCESS_BLOCKED("error.console_access_blocked"),
    ERROR_SERVER_NOT_SET_UP("error.server_not_set_up"),
    ERROR_NOT_IN_LOBBY_WORLD("error.not_in_lobby_world"),


        ;

    public String path;

    LocalizedMessage(String path)
    {
        this.path = path;
    }
}
