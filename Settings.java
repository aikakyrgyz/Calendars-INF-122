
public class Settings {
    Theme theme;
    TimeZone timeZone;


    public static Settings mySettings;


    private Settings()

    {
        // by default
        this.theme = Theme.LIGHT;
        this.timeZone = TimeZone.PST;
    }

    public static Settings getInstance()
    {
        if (mySettings == null)
            return new Settings();
        else
            return mySettings;
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
