import java.util.Scanner;

import javax.swing.LookAndFeel;


public class LoggedInMenu implements Menu {
    
    public static String[] LOGGED_IN_MENU_OPTIONS = {"1 --- View my Calendars"  ,
                                                    "2 --- Create a Calendar " ,
                                                    "3 --- Choose a Calendar"  ,
                                                    "4 --- Delete a calendar",
                                                    "5 --- Delete my account",
                                                    "6 --- Back <-"
                                                    };

    public void display()
    {
        Scanner scanner = new Scanner(System.in);
        boolean nextMenu = false;
        int option = 0;
        System.out.println("\nChoose from following options: ");
        
        while (!nextMenu)
        {
            for (String e : getOptions()) {
                System.out.println(e);   
            }             
            System.out.println();

            try {
                System.out.print("\nYour input: ");
                option = scanner.nextInt();
                System.out.println("Choice: " + "[" + getOptions()[option-1] + "]\n"); 
                switch (option)
                {
                    case 1: 
                    {
                        // View all calendars
                        viewCalendars();
                        break;
                    }
                    case 2: 
                    {
                        // Create a calendar
                        createCalendar();
                        break;
                    }
                    case 3: 
                    {
                        // Choose a calendar 
                        nextMenu = true;
                        chooseCalendar();
                        break;
                    }
                    case 4: 
                    {
                        // *** Refractor
                        // Extracted the code within case 4 block into a method. 
                        // *** 
                        deleteCalendar();
                        break;

                    }
                    case 5: 
                    {
                        // *** Refractor
                        // Extracted the code within the case 5 block into a method.
                        // ***
                        nextMenu = true;
                        deleteUser();
                        break;
                    }
                    case 6:
                    {
                        nextMenu = true;
                        activatePreviousMenu();
                        break;
                    }
            }
            
                } catch (Exception ex)
            {
                System.out.println(ex);
                System.out.println("Please enter an integer value between 1 and " + getOptions().length);
                scanner.next();
            } finally
            {
                System.out.println();
            }
        }
        // mainMenu();
    }

    public String[] getOptions()
    {
        return LOGGED_IN_MENU_OPTIONS;
    }

  

    //show all existing calendars of the user
    public void viewCalendars()
    {
        
        // *** Refractor:
        // ----------------------------------
        //System.out.println("Below are all of your exisiting calendars: \n");
        // System.out.println("Calendar ID\t\t Name \t\t # of Events");
        // HashMap<Integer, Calendars>  c = myCalendarDatabase.getAllCalendars(currentLoggedInUser);
        // if(! (c == null || c.isEmpty()))

        // if(! (c == null || c.isEmpty()))
        // {
        //     for (Calendars e : c.values()) {
        //         System.out.println(e);
        //     }
        // }
        // else{
        //     System.out.println("\n-------------------- NO EXISTING CALENDARS --------------\n");
        // }
        // -----------------------------------
        // I shall put all of the code above  in the CalendarDatabase class and make it take care of the 
        // printing, because this method is using all of the foreign variables instead of this own
        // thus it should be moved to the class that has those functionalities.

        // new code:
        System.out.println(MainWindow.myCalendarDatabase.viewAllCalendars());
        // ***

    }

    // User chooses to create a new calendar

    public void createCalendar()
    {
        System.out.print("Please enter a name for your new calendar: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Calendars calendar = new Calendars(name);
        MainWindow.myCalendarDatabase.addNewCalendar(name, MainWindow.currentLoggedInUser, calendar);
        System.out.println("Calendar with name: " + name + " was successfully created for user: " + MainWindow.currentLoggedInUser.getUserName());
    }


    public void chooseCalendar()
    {
        System.out.print("Please enter the ID of the calendar you would like to see: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Calendars calendar = MainWindow.myCalendarDatabase.getCalendar(MainWindow.currentLoggedInUser, id);
        MainWindow.setCurrentCalendar(calendar);
        activateNextMenu();
    }

    public void activateNextMenu()
    {
        CalendarViewMenu m = new CalendarViewMenu();
        MainWindow.setMenuType(m);
    }

    public void deleteCalendar()
    {
        System.out.print("Enter the ID of the calendar you want to delete: ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        boolean deleted = MainWindow.myCalendarDatabase.deleteCalendar(MainWindow.currentLoggedInUser, id);
        if(deleted)
            System.out.print("The calendar was successfully deleted.");
        else
            System.out.print("Calendar ID was entered wrong.");
    }

    void deleteUser()
    {
        boolean deleted = MainWindow.myUserDatabase.deleteUser(MainWindow.currentLoggedInUser);
        if(deleted){
            System.out.println("User was deleted, back to initial menu <-");
            MainWindow.setCurrentUser(null);
        }
        activatePreviousMenu();
    }

    public static void activateMenu()
    {
        Menu LIM  = new LoggedInMenu();
        MainWindow.setMenuType(LIM);
    }

    void activatePreviousMenu()
    {
        MainMenu.activateMenu();
    }

        
}
