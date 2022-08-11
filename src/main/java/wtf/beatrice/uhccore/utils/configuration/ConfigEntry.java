package wtf.beatrice.uhccore.utils.configuration;

public enum ConfigEntry
{
    UHC_WORLDS("settings.uhcworlds"),

    LOBBY_WORLDS("settings.lobbyworlds"),

    FRIENDLY_FIRE("settings.friendly-fire"),

    TEAMS_NUMBER("settings.teams"),
    TEAMS_NAMES("teams"),
    TEAMS_ITEMS("team-items"),

    SPAWN_WORLD("spawn.world"),
    SPAWN_X("spawn.x"),
    SPAWN_Y ("spawn.y"),
    SPAWN_Z("spawn.z"),
    SPAWN_PITCH("spawn.pitch"),
    SPAWN_YAW("spawn.yaw"),

    FIREWORK_POS("firework-pos"),

    BORDER_CENTER_X("settings.border.center-x"),
    BORDER_CENTER_Z("settings.border.center-z"),
    BORDER_SIZE("settings.border.size"),

    ;

    public String path;

    ConfigEntry(String path)
    {
        this.path = path;
    }
}
