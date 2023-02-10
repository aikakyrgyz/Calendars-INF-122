import java.util.Scanner;

import javax.swing.JTable.PrintMode;


public class CalendarInViewMenu implements Menu {

    public static String[] CALENDAR_IN_VIEW_MENU_OPTIONS = {"1 --- View all events",
                                                    "2 --- Add an event",
                                                    "3 --- Delete an event",
                                                    "4 --- Update an event",
                                                    "5 --- Move to next",
                                                    "6 --- Move to previous",
                                                    "7 --- Back <-"
                                                };

    public String[] getOptions()
    {
        return CALENDAR_IN_VIEW_MENU_OPTIONS;
    }                          
    

    public void display()
    {
        
        System.out.println("\n\nPlease choose from following options: ");
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        boolean nextMenu = false;
        while (!nextMenu)
        {
            System.out.println();
            for (String e : getOptions()) {
                System.out.println(e);   
            }             
            // try {
                
                System.out.print("\nYour input: ");
                option = scanner.nextInt();

                System.out.println("Choice: " + "[" + getOptions()[option-1] + "]"); 
                System.out.println();

                switch (option)
                {
                case 1: 
                    {
                        // View All Events choice
                        MainWindow.currentChosenCalendar.viewAllEvents();
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
                        updateEvent(); 
                        break;                           
                }
                case 5: 
                {
                    MainWindow.currentChosenCalendar.moveToNext();
                    break;
                }

                case 6: 
                {
                    MainWindow.currentChosenCalendar.moveToPrevious();
                    break;
                }
                case 7:
                {
                    nextMenu = true;
                    activatePreviousMenu();
                }
                }
            
        }

    }


    void askForEventDetails()
    {
        String[] prompts = {"Enter title: ", "Enter day: ", "Enter month: ", "Enter year: ",  "Enter start time in HH-MM format: ", 
        "Enter end time in HH-MM format: "};
        String[] userInputs = new String[6];
        for (int i = 0; i<prompts.length; i++)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print(prompts[i]);
            String input = scanner.nextLine();
            userInputs[i] = input;
            System.out.println();

        }
        MainWindow.currentChosenCalendar.addEvent(userInputs);
        // *** Refractor:
        // I have changed the following line of code, instead of passing each parameter separately, I will pass the string array at once 
        // and let the function handle deconstruction of it to pieces. 

        // MainWindow.currentChosenCalendar.addEvent(name, startTime, endTime, Integer.parseInt(day),Integer.parseInt(month)-1, Integer.parseInt(year));

        // *** Refractor:
        // I have changed how the inputs for title, day, month, year, startTime, endTime are 
        // being asked. Instead of it having a scanner line for each I have created a for loop and initilzied an array 
        // with the questions that need to be asked, however, this will require the user to enter all of the data
        // without errors
        // System.out.print("Enter title: ");
        // String name = scanner.nextLine();
        // System.out.println();

        // System.out.print("Enter day: ");
        // String day = scanner.nextLine();
        // System.out.println();


        // System.out.print("Enter month: ");
        // String month = scanner.nextLine();
        // System.out.println();

        // System.out.print("Enter year: ");
        // String year = scanner.nextLine();
        // System.out.println();


        // System.out.print("Enter start time in HH-MM format: ");
        // String startTime = scanner.nextLine();
        // System.out.println();

        // System.out.print("Enter end time in HH-MM format: ");
        // String endTime = scanner.nextLine();
        // System.out.println();
        // ***
        // month starts from 0!

    }


    void updateEvent()
    {
        System.out.print("Enter the ID of the event you want to update:");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        Event eventToEdit = MainWindow.currentChosenCalendar.getEvent(id);
        if(eventToEdit==null)
            System.out.println("No event with such an ID.");
        else
            eventToEdit.editEvent();
    }

    void deleteEvent()
    {
        MainWindow.currentChosenCalendar.viewAllEvents();
        System.out.print("Enter the ID of the event you want to delete: ");
        Scanner scanner = new Scanner(System.in);
        int eventToDelete = scanner.nextInt();

        boolean deleted = MainWindow.currentChosenCalendar.deleteEvent(eventToDelete);
        if(deleted)
            System.out.println("The event was successfully deleted");
        else
            System.out.println("The id was not correctly entered");
    }



    public static void activateMenu()
    {
        Menu CIVM  = new CalendarInViewMenu();
        MainWindow.setMenuType(CIVM);
    }

    public static void activatePreviousMenu()
    {
        CalendarViewMenu.activateMenu();
    }

    }
