
public class Settings {
    Theme theme;
    TimeZone timeZone;

    Settings()
    {
        // by default
        this.theme = Theme.LIGHT;
        this.timeZone = TimeZone.PST;
    }

    Theme getTheme()
    {
        return theme;
    }
    TimeZone getTimeZone()
    {
        return timeZone;
    }

    void setTimeZone(TimeZone timeZone)
    {
        this.timeZone = timeZone;
    }

    void setTheme(Theme theme)
    {
        this.theme = theme;
    }
}
