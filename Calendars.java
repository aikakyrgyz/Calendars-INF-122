

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.HashMap;



public class Calendars
{
    public static int ID = 0;
    enum visualModes {DAY, WEEK, MONTH, YEAR}
    Person owner;
    String nameCalendar;
    MonthVisual monthV;
    boolean mode; //private or public 
    visualModes currentVisualMode;
    boolean isPublic;
    CalendarTypeEnum type;
    int todayDay;
    int todayMonth;
    int todayYear;
    int currentDay;
    int currentMonth;
    int currentYear;
    String name;
    int calendarID;
    Calendar calendar; 
    HashMap<Integer, Event> myEventDatabase;

    Calendars(String name)
    {
        calendarID = ID++;
        //Get real month/year
        calendar = new GregorianCalendar(); 
        type = CalendarTypeEnum.GREGORIAN;
        this.name = name;
        todayDay = calendar.get(GregorianCalendar.DAY_OF_MONTH); 
        todayMonth = calendar.get(GregorianCalendar.MONTH); 
        todayYear = calendar.get(GregorianCalendar.YEAR); 
        // current = user's chosen date currently
        currentDay = todayDay;
        currentYear = todayYear;
        currentMonth = todayMonth;
        // default view will be month
        currentVisualMode = visualModes.MONTH;
        monthV = new MonthVisual();
        myEventDatabase = new HashMap();
        
    }

    public String toString()
    {
        return "\t" + calendarID + "\t\t" + name + "\t\t " + myEventDatabase.size();
    }

    int getID()
    {
        return calendarID;
    }

    Event getEvent(int id)
    {
        return myEventDatabase.get(id);
    }
    

    void displayMonthVisual()
    {
        System.out.print(monthV.display());
    }

    void moveToNext()
    {
        if(currentVisualMode == visualModes.MONTH)
        {
            monthV.moveToNext();
            System.out.print(monthV.display());
        }
    }


    void moveToPrevious()
    {
        if(currentVisualMode == visualModes.MONTH)
        {
            monthV.moveToPrevious();
            System.out.print(monthV.display());
        }
    }




   visualModes getCurrentView()
   {
    return currentVisualMode;
   }


   /* For future changes 
    void setVisualMode(int choice)
    {
        switch(choice)
        {
            case 1: currentVisualMode = visualModes.DAY; break;
            case 2: currentVisualMode = visualModes.WEEK; break;
            case 3: currentVisualMode = visualModes.MONTH; break;
            case 4: currentVisualMode = visualModes.YEAR; break;
        }
    }

    ArrayList<Object> determineFieldsToAsk()
    {
        ArrayList<Object> arr = new ArrayList<Object>();

        if(currentVisualMode == visualModes.DAY)
        {
            arr.add(3);
            arr.add("day");
            arr.add("month");
            arr.add("year");
        }
        else if(currentVisualMode == visualModes.MONTH)
        {
            arr.add(2);
            arr.add("month");
            arr.add("year");
        }
        else if(currentVisualMode == visualModes.YEAR)
        {
            arr.add(1);
            arr.add("year");
        }
        return arr;
    }

    void setSpecificViewPeriod(int[] arr)
    {
        if(currentVisualMode == visualModes.DAY)
        {
            currentDay = arr[0];
            currentMonth = arr[1];
            currentYear = arr[2];
        }
        else if(currentVisualMode == visualModes.MONTH)
        {
            currentMonth = arr[0];
            currentYear = arr[1];
        }
        else if(currentVisualMode == visualModes.YEAR)
        {
            currentYear = arr[0];
        }
    }
    
    */
    
    Person getOwner()
    {
        return owner;
    }

    boolean getMode()
    {
        return mode;
    }


    void addEvent(String[] inputs)
    {

        Event myEvent = new Event(inputs);
        myEventDatabase.put(myEvent.getid(), myEvent);

    }

    void viewAllEvents()
    {
        if(currentVisualMode == visualModes.MONTH)
        {
            monthV.viewAllEvents(myEventDatabase);
        }
    }
    

    boolean deleteEvent(int eventID)
    {
        try
        {
            Event toDelete = myEventDatabase.get(eventID);
            System.out.println(toDelete);
            myEventDatabase.remove(eventID);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("The id was entered wrong");
            return false;
        }

    }

}