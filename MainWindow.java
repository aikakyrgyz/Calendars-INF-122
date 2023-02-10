
import java.io.*;
import java.util.Scanner;

import javax.swing.plaf.metal.MetalBorders.MenuBarBorder;

import java.util.HashMap;


// The MainWindow shall act as a "Host" that performs an operation, that is,
// this window will take care of switchin between different types of menus 
// It has a method called setMenuType() === setAlgorithm() depending on which 
// menu needs to be shown. 
// It will perform the operation of starting the loop for the different menus


public class MainWindow {

    public static UserDatabase myUserDatabase = UserDatabase.getInstance();;
    public static CalendarDatabase myCalendarDatabase = CalendarDatabase.getInstance();
    public static Settings mySettings = Settings.getInstance();
    public static Calendars currentChosenCalendar;
    public static Person currentLoggedInUser;
    public static Menu menuType;


    static void setMenuType(Menu m)
    {
        menuType = m;
    }

    static void setCurrentUser(Person u)
    {
        currentLoggedInUser = u;
    }

   static void setCurrentCalendar(Calendars c)
    {
        currentChosenCalendar = c;
    }

    void run()

    {
        Menu m = new MainMenu();
        menuType = m;

        // *** Design Pattern
        // Strategy method applied here
        while(true)
        {
            menuType.display();
        }
    }
}





