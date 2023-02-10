
import java.util.Scanner;

public class CalendarViewMenu implements Menu {
    
    String[] CALENDAR_VIEW_MENU_OPTIONS = 
                    {"1 --- View Monthly",
                    // "2 --- View Daily",
                    // "3 --- View Yearly",
                    "2 --- Back <- ", };


    String[] getOptions()
    {
        return CALENDAR_VIEW_MENU_OPTIONS;
    }


    public void display()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are viewing: " + MainWindow.currentChosenCalendar.name + " calendar\n");
            System.out.println("Please choose from following options: ");
            // I have only one view currently
                boolean nextMenu = false;
                int option = 0;
                while (!nextMenu)
                {
                    System.out.println();   
                    for (String e : getOptions()) {
                        System.out.println(e);   
                    }             
                    try {
                        System.out.print("\nYour input: ");
                        option = scanner.nextInt();
                        System.out.println("Choice: " + "[" + getOptions()[option-1] + "]\n"); 
                        nextMenu = true;
                        switch (option)
                        {
                        case 1: 
                            {
                                MainWindow.currentChosenCalendar.displayMonthVisual();
                                activateNextMenu();
                                break;
                            }

                        case 2: 
                            {
                                nextMenu = true;
                                activatePreviousMenu();
                            };
                        }
                     } catch (Exception ex)
                    {
                        System.out.println("Please enter an integer value between 1 and " + getOptions().length);
                        scanner.next();
                    } finally
                    {
                        System.out.println("exiting");
                    }
                }
            }
            
            public static void activateMenu()
            {
                CalendarViewMenu CVM = new CalendarViewMenu();
                MainWindow.setMenuType(CVM);
            }
            
            void getIntoMonthlyView()
            {
                MainWindow.currentChosenCalendar.displayMonthVisual();
                CalendarInViewMenu.activateMenu();
            }

            void activateNextMenu()
            {
                CalendarInViewMenu.activateMenu();
            }

            void activatePreviousMenu()
            {
                LoggedInMenu.activateMenu();
            }
        
    }


   

