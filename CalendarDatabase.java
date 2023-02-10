

import java.util.HashMap;



// why need a CalendarDatabase? 
// If the user chooses to see all of the public calendars of all the users in the
// application then we will need to have the database. 
public class CalendarDatabase {

    private static CalendarDatabase myCalendarDatabase = new CalendarDatabase();

    HashMap<Person, HashMap<Integer, Calendars>> calendars;

    // Singleton class
    private CalendarDatabase()
    {
        calendars = new HashMap<>();
    }

    public static CalendarDatabase getInstance()
    {
        return myCalendarDatabase;
    }

    // returns calendar with ID = id for Person = owner
    Calendars getCalendar(Person owner, int id)
    {
        HashMap<Integer, Calendars> c = calendars.get(owner);
        return c.get(id);
    }

    HashMap<Integer, Calendars> getAllCalendars(Person owner) 
    {
        // System.out.println(calendars.get(owner));
        return calendars.get(owner);
    }

    void addNewCalendar(String name, Person owner, Calendars cal)
    {
        if(calendars.get(owner)==null)
        {
            HashMap<Integer, Calendars> cals = new HashMap<>();
            calendars.put(owner,cals);

        }
        owner.addCalendar(cal);
        calendars.get(owner).put(cal.getID(), cal);

    }

    boolean deleteCalendar(Person owner, Integer id)
    {
        try{
            calendars.get(owner).remove(id);
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }

    boolean isPresent(Person owner, Integer id)
    {
        HashMap<Integer, Calendars> calendarsOfOwner = calendars.get(owner);
        if(calendarsOfOwner==null) return false;
        return calendarsOfOwner.containsKey(id);
    }

    String viewAllCalendars()
    {
        String output = "";

        output += "Below are all of your exisiting calendars: \n\nCalendar ID\t\t Name \t\t # of Events\n\n";
        HashMap<Integer, Calendars>  c = getAllCalendars(MainWindow.currentLoggedInUser);
        if(! (c == null || c.isEmpty()))
        {
            for (Calendars e : c.values()) {
                output += e + "\n";
            }
        }
        else{
            output += "\n\n-------------------- NO EXISTING CALENDARS --------------\n";
        }
        return output;
    }

}
