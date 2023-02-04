import java.util.ArrayList;
import java.util.List;

public class Person {
    static int id=0;
    String username;
    int userID;
    List<Calendars> calendars;

    Person(String name)
    {
        this.username = name;
        this.userID = id++;
        calendars = new ArrayList<Calendars>();
    }

    public String toString()
    {
        return this.userID + "\t\t" + username + "\t\t\t\t" + calendars.size();
    }

    String getUserName()
    {
        return username;
    }

    void addCalendar(Calendars cal)
    {
        calendars.add(cal);
    }

}
