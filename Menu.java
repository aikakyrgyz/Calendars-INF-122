
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;


public class Menu {

    UserDatabase myUserDatabase;
    CalendarDatabase myCalendarDatabase;
    Calendars currentChosenCalendar;
    Person currentLoggedInUser;
    // MainMenu mainMenu;
    // CalendarViewMenu calendarViewMenu;

    public Menu()
    {
        myUserDatabase = UserDatabase.getInstance();
        myCalendarDatabase = CalendarDatabase.getInstance();
      
    }

    public  void mainMenu()
    {
        System.out.println("Please choose from following options: ");
        String[] options = {"1 --- Log-in",
                            "2 --- Sign-up",
                            "3 --- View all registered users",
                            "4 --- Exit",
                };
            Scanner scanner = new Scanner(System.in);
            int option = 1;
            while (option!=4)
            {    
                for (String e : options) {
                    System.out.println(e);    
                }        
                try {
                    option = scanner.nextInt();
                    System.out.println("Choice: " + "[" + options[option-1] + "]"); 
                    switch (option)
                    {
                    case 1: 
                    {
                        logIn();
                        break;
                    }
                    case 2: 
                    {
                        signUp();
                        break;
                    }
                    case 3: 
                    {
                        viewAllUsers();
                        break;
                    }
            
                }
                 } catch (Exception ex)
                 {
                    System.out.println("Please enter an integer value between 1 and " + options.length);
                    scanner.next();
                 }
                finally
                {
                    System.out.println();
                }
            
    }
}

        void viewAllUsers()
        {
            System.out.println(myUserDatabase.getAllUsers());
        }

        // user chooses to LOGIN
        public  void logIn()
        {
            System.out.println("Please enter your username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            while (!myUserDatabase.isPresent(username))
                    {
                        System.out.println("Cannot find the username, please reenter your username: ");
                        username = scanner.nextLine();
                    }
            // the user was successfully found
            currentLoggedInUser = myUserDatabase.getUser(username);
            System.out.println("You were successfully signed in as: " + currentLoggedInUser.getUserName());
            System.out.println();
            loggedInMenu();
        }

        // user chooses to SIGNUP 
        public void signUp()
            {
                System.out.println("Please enter a username");
                Scanner scanner = new Scanner(System.in);
                String username = scanner.nextLine();

                    while (myUserDatabase.isPresent(username))
                    {
                        System.out.println("Please choose another username");
                        username = scanner.nextLine();
                    }
                Person newUser = new Person(username);
                myUserDatabase.addNewUser(username, newUser);
                System.out.println("User with username: " + username + " was successfully created");
            }



    public void loggedInMenu()
    {
        System.out.println("Please choose from following options: ");
        String[] options = {"1 --- View my Calendars"  ,
                            "2 --- Create a Calendar " ,
                            "3 --- Choose a Calendar"  ,
                            "4 --- Delete a calendar",
                            "5 --- Delete my account",
                            "6 --- Back <-"
                };


            Scanner scanner = new Scanner(System.in);
            int option = 1;
            while (option!=6)
            {
                for (String e : options) {
                    System.out.println(e);   
                }             
                try {
                    option = scanner.nextInt();
                    System.out.println("Choice: " + "[" + options[option-1] + "]"); 
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
                            chooseCalendar();
                            break;
                        }
                        case 4: 
                        {
                            System.out.println("Enter the ID of the calendar you want to delete");
                            Scanner in = new Scanner(System.in);
                            int id = in.nextInt();
                            boolean deleted = myCalendarDatabase.deleteCalendar(currentLoggedInUser, id);
                            if(deleted)
                                System.out.print("The calendar was successfully deleted");
                            else
                                System.out.print("Calendar ID was entered wrong");
                            break;

                        }
                        case 5: 
                        {
                            boolean deleted = myUserDatabase.deleteUser(currentLoggedInUser);
                            if(deleted){
                                System.out.println("User was deleted, back to initial menu <-");
                                currentLoggedInUser = null;
                            }
                            // mainMenu();
                            // break;
                            return;
                        }

                        
                }
                
                 } catch (Exception ex)
                {
                    System.out.println(ex);
                    System.out.println("Please enter an integer value between 1 and " + options.length);
                    scanner.next();
                } finally
                {
                    System.out.println();
                }
            }
            // mainMenu();
        }


        //show all existing calendars of the user
        public void viewCalendars()
        {
            System.out.println("Below are all of your exisiting calendars");
            System.out.println("Calendar ID\t\t Name");
            HashMap<Integer, Calendars>  c = myCalendarDatabase.getAllCalendars(currentLoggedInUser);
            if(! (c == null || c.isEmpty()))
            {
                for (Calendars e : c.values()) {
                    System.out.println(e);
                }
            }
            else{
                System.out.println("\n-------------------- NO EXISTING CALENDARS --------------\n");
            }
        }

        // User chooses to create a new calendar

        public void createCalendar()
        {
            System.out.println("Please enter a name for your new calendar");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();

                // while (myCalendarDatabase.isPresent(currentLoggedInUser, name))
                // {
                //     System.out.println("Please choose another username");
                //     name = scanner.nextLine();
                // }
            Calendars calendar = new Calendars(name);
            myCalendarDatabase.addNewCalendar(name, currentLoggedInUser, calendar);
            System.out.println("Calendar with name: " + name + " was successfully created for user: " + currentLoggedInUser.getUserName());
        }


        public void chooseCalendar()
        {
            System.out.println("Please enter the ID of the calendar you would like to see");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();

                while (!myCalendarDatabase.isPresent(currentLoggedInUser, id))
                {
                    System.out.println("Please choose another username");
                    id = scanner.nextInt();
                }
            Calendars calendar = myCalendarDatabase.getCalendar(currentLoggedInUser, id);
            currentChosenCalendar = calendar;
            calendarViewMenu();
        }


        

        


        public void calendarViewMenu()
        {
            System.out.println("You are viewing: " + currentChosenCalendar.name + " calendar\n");
            System.out.println("Please choose from following options: ");
            String[] options = {"1 --- View Monthly",
                                "2 --- View Daily",
                                "3 --- View Yearly",
                                "4 --- Back <- ",
                    };
                Scanner scanner = new Scanner(System.in);
                int option = 1;
                while (option!=5)
                {
                    for (String e : options) {
                        System.out.println(e);   
                    }             
                    // try {
            
                        option = scanner.nextInt();

                        System.out.println("Choice: " + "[" + options[option-1] + "]"); 

                        switch (option)
                        {
                        case 1: 
                            {
                                // logOption1();
                                currentChosenCalendar.displayMonthVisual();
                                calendarInViewMenu();
                                break;
                            
                            }

                        case 2: System.out.println("Your choice " + option); break;
                        case 3: System.out.println("Your choice " + option); break;
                        case 4: return;
                        }
                    //  } catch (Exception ex)
                    // {
                    //     System.out.println("Please enter an integer value between 1 and " + options.length);
                    //     scanner.next();
                    // } finally
                    // {
                    //     System.out.println("exiting");
                    // }
                }
                loggedInMenu();
            }


            public void calendarInViewMenu()
        {
            System.out.println("Please choose from following options: ");
            String[] options = {"1 --- View all events",
                                "2 --- Add an event",
                                "3 --- Delete an event",
                                "4 --- Move to next",
                                "5 --- Move to previous",
                                "6 --- Back <-"
                                // "4- ",
                    };
                Scanner scanner = new Scanner(System.in);
                int option = 1;
                while (option!=6)
                {
                    for (String e : options) {
                        System.out.println(e);   
                    }             
                    // try {
            
                        option = scanner.nextInt();

                        System.out.println("Choice: " + "[" + options[option-1] + "]"); 

                        switch (option)
                        {
                        case 1: 
                            {
                                // View All Events choice
                                currentChosenCalendar.viewAllEvents();
                                break;
                            
                            }
                        case 2: 
                        {               
                                askForEventDetails();
                                break;
                        }
                        case 3:
                        {
                                deleteEvent();
                                break;
                        }
                        case 4: 
                        {
                            currentChosenCalendar.moveToNext();
                            break;
                        }

                        case 5: 
                        {
                            currentChosenCalendar.moveToPrevious();
                            break;
                        }


                        // case 6: return;
                        }
                    //  } catch (Exception ex)
                    // {
                    //     System.out.println("Please enter an integer value between 1 and " + options.length);
                    //     scanner.next();
                    // } finally
                    // {
                    //     System.out.println("exiting");
                    // }
                }
                // loggedInMenu();
            }

    void askForEventDetails()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter title: ");
        String name = scanner.nextLine();
        System.out.println();

        System.out.print("Enter day");
        String day = scanner.nextLine();
        System.out.println();


        System.out.print("Enter month");
        String month = scanner.nextLine();
        System.out.println();

        System.out.print("Enter year");
        String year = scanner.nextLine();
        System.out.println();


        System.out.print("Enter start time");
        String startTime = scanner.nextLine();
        System.out.println();

        System.out.print("Enter end time");
        String endTime = scanner.nextLine();
        System.out.println();

        // month starts from 0!
        currentChosenCalendar.addEvent(name, startTime, endTime, Integer.parseInt(day),Integer.parseInt(month)-1, Integer.parseInt(year));

    }

    void deleteEvent()
    {
        currentChosenCalendar.viewAllEvents();
        System.out.println("Enter the ID of the event you want to delete: ");
        Scanner scanner = new Scanner(System.in);
        int eventToDelete = scanner.nextInt();

        boolean deleted = currentChosenCalendar.deleteEvent(eventToDelete);
        if(deleted)
            System.out.println("The event was successfully deleted");
        else
            System.out.println("The id was not correctly entered");
    }
    
}





