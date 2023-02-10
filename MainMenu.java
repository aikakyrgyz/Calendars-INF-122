

import java.util.Scanner;

public class MainMenu implements Menu {

    // *** changed the array to be a  static variable because it is simply a constant variable and is inherent by the class 
    public static String[] MAIN_MENU_OPTIONS = {
                                "1 --- Log-in",
                                "2 --- Sign-up",
                                "3 --- View all registered users",
                                "4 --- View current logged-in user",
                                "5 --- Configure settings",
                                "6 --- Exit", 
                                };

    public String[] getOptions()
    {
        return MAIN_MENU_OPTIONS;
    }
    public void display()
    {
            Scanner scanner = new Scanner(System.in);
            boolean nextMenu = false;
            int option;



            // *** 
            // In all of the possible Menus I have changed the termination value for the loop, by
            // giving it a good name to make it obvious when the loop shall terminate. When the loop
            // terminates, it means that a new "Strategy" was set for the client side, and 
            // the menu will change accordingly, while the Main Menu Window only has 1 call to display. 
            // *** 

    
            while (!nextMenu)
            {    


                System.out.println("\n\n\nYour current setttings are:");
                System.out.println("[THEME] --- " + MainWindow.mySettings.getTheme());
                System.out.println("[TIMEZONE] --- " + MainWindow.mySettings.getTimeZone() + "\n\n\n\n");
                System.out.println("Please choose from following options: \n");


                // *** Refractor:
                // For the loop before I had it to iterate though a MAIN_MENU_OPTIONS,
                // however, I have changed it to have function that returns getOptions()
                //  that shall return the String. I have decided to make this so that 
                // all menus have similiar code and if there is another menu option added, this
                // or the name of the string changes it does not affect this part of code
                // ***

                for (String e : getOptions()) {
                    System.out.println(e);    
                }        
                try {
                    System.out.print("\nYour input: ");
                    option = scanner.nextInt();
                    System.out.println("Choice: " + "[" + getOptions()[option-1] + "]\n"); 
                    switch (option)
                    {
                    case 1: 
                    {
                        nextMenu = true;
                        logIn();
                        activateNextMenu();
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

                    case 4:
                    {
                        System.out.println("Current Logged-in User: " + MainWindow.currentLoggedInUser.getUserName());
                        break;
                    }

                    case 5:
                    {
                        configureSettings();
                        break;
                    }

                    case 6:
                    {
                        System.out.println("Closing Calendars App... Goood bye");
                        System.exit(0); 
                    }
            
                }
                    } catch (Exception ex)
                    {
                    System.out.println("Please enter an integer value between 1 and " + MAIN_MENU_OPTIONS.length);
                    scanner.next();
                    }
                finally
                {
                    System.out.println();
                }
            
        }
    }   
        // user chooses to LOGIN
        public  void logIn()
        {
            System.out.print("Please enter your username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            while (!MainWindow.myUserDatabase.isPresent(username))
                    {
                        System.out.print("Cannot find the username, please reenter your username: ");
                        username = scanner.nextLine();
                    }
            // the user was successfully found
            Person currentLoggedInUser = MainWindow.myUserDatabase.getUser(username);
            MainWindow.setCurrentUser(currentLoggedInUser);
            System.out.println("You were successfully signed in as: " + currentLoggedInUser.username + "\n");
        }
        
        public void activateNextMenu()
        {
            LoggedInMenu.activateMenu();
        }

    
        // user chooses to SIGNUP 
        public void signUp()
            {
                System.out.print("Please enter a username: ");
                Scanner scanner = new Scanner(System.in);
                String username = scanner.nextLine();

                    while (MainWindow.myUserDatabase.isPresent(username))
                    {
                        System.out.print("Please choose another username: ");
                        username = scanner.nextLine();
                    }
                Person newUser = new Person(username);
                MainWindow.myUserDatabase.addNewUser(username, newUser);
                System.out.println("User with username: " + username + " was successfully created.");
            }

        void viewAllUsers()
        {
            System.out.println(MainWindow.myUserDatabase.getAllUsers());
        }


        void configureSettings()
            {
                System.out.println("What would you like to configure ");
                String[] options = {"1 --- Theme",
                                    "2 --- TimeZone",
                        };

                for (String e : options) {
                    System.out.println(e);    
                }  
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();

                if(input == 1)
                {
                    System.out.println("Choose: ");
                    System.out.println("1 --- Light");
                    System.out.println("2 --- Dark");
                    int theme = scanner.nextInt();
                    if(theme==1)
                        MainWindow.mySettings.setTheme(Theme.LIGHT);
                    else
                        MainWindow.mySettings.setTheme(Theme.DARK);
                }
                else if(input ==2 )
                {
                    System.out.println("Choose: ");
                    System.out.println("1 --- CST");
                    System.out.println("2 --- EST");
                    int theme = scanner.nextInt();
                    if(theme==1)
                        MainWindow.mySettings.setTimeZone(TimeZone.CST);
                    else
                        MainWindow.mySettings.setTimeZone(TimeZone.EST);
                }

            }

            public static void activateMenu()
            {
                Menu m = new MainMenu();
                MainWindow.setMenuType(m);
            }

}


