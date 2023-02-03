import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;




public class Person {
    static int id=0;

    String username;
    int userID;
    List<Calendars> calendars;
    Calendars currentCalendar;
    HashMap<Calendars, ArrayList<Person>> sharedCalendars;
    HashMap<Event, ArrayList<Person>> sharedEvents;

    Person(String name)
    {
        this.username = name;
        this.userID = id++;
        calendars = new ArrayList();
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
   
    // void addCalendar(int id, Calendars cal) 
    // {
    //     calendars.add(cal);
    // }

    // void removeCalendar(Calendars cal)
    // {
    //     calendars.remove(cal);
    // }

    // void updateCalendar(Calendars cal)
    // {
    //     chooseView(cal);
        
    //     cal.crud();
    // }


    void chooseView(Calendars cal)
    {
        System.out.println("Please choose the view\n1. Daily View\n2. Weekly View\n3. Monthly View\n4. Yearly View\n");
        int choice = Integer.parseInt((new Scanner(System.in)).nextLine());  // Create a Scanner object
        cal.setVisualMode(choice);
    }

    void chooseSpecificPeriodOfView(Calendars cal)
    {
        System.out.println("You have chosen " + cal.getCurrentView() + "view");
        int[] period = new int [3];
        ArrayList<Object> arr = cal.determineFieldsToAsk();
        int range = (int) arr.get(0);
        for(int i = 1; i<= range; i++ )
        {
            System.out.println("Please enter " + (String) arr.get(i) + " :");
            period[i-1] = Integer.parseInt((new Scanner(System.in)).nextLine());;
        }
        cal.setSpecificViewPeriod(period);
    }

    // void viewAllCalendars()
    // {
    //     for (Calendars calendar : calendars) {
    //         System.out.println(calendar.calendarID + " | " + calendar.nameCalendar);
    //     }
    // }
}
