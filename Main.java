
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Calendar;




public class Main {

    public static String[] dayTitles = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    public static void main(String args[]) {


        Menu myConsole = new Menu();
        Settings mySettings = new Settings();



        
//         String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
//  // if no ids were returned, something is wrong. get out.
//         if (ids.length == 0)
//              System.exit(0);

//         SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);

//         Calendar calendar = new GregorianCalendar();
//         System.out.println(Calendar.JANUARY);
//         System.out.println(Calendar.FEBRUARY);
//         System.out.println(Calendar.DECEMBER);
//         System.out.println("ERA: " + calendar.get(Calendar.ERA));
//         System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
//         System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
//         System.out.println(calendar.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.US));
//         System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
//         System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
//         System.out.println("DATE: " + calendar.get(Calendar.DATE));
//         System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
//         System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
//         System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
//         System.out.println("DAY_OF_WEEK_IN_MONTH: "
//                            + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));


//         Calendar calendar1 = Calendar.getInstance();

//         String name = calendar1.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.US);
//         int monthLength = calendar1.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
//         String output = "Today's date: " + name + " " + calendar1.get(Calendar.DAY_OF_MONTH) + "\n";
//         calendar1.set(Calendar.DAY_OF_MONTH, 1);
//         int firstDayOfMonth = calendar1.get(GregorianCalendar.DAY_OF_WEEK); //assuming the calendar was just initialized

//         System.out.println("STARTS FROM " + firstDayOfMonth);
//         // System.out.println(firstDayOfMonth);
//         output+= "\t\t\t " + name.toUpperCase() + "\n";
        

//          for (String dayTitle: dayTitles)
//         {
//             output += dayTitle + " \t"; 
//         }


//         output += String.format("\n");

//         for(int i = 0; i < firstDayOfMonth-1;  i++){
//             output += String.format("  \t");
//         }

//         for (int i = 0, dayOfMonth = 1; dayOfMonth <= monthLength; i++) {
//             for (int j = ((i == 0) ? firstDayOfMonth - 1 : 0); j < 7 && (dayOfMonth <= monthLength); j++) {
//                 output += String.format("%2d\t ", dayOfMonth);
//                 dayOfMonth++;
//             }
//             output+= "\n";
//         }
//         System.out.println(output);

//         System.out.println("PARSED");

//         System.out.println(Integer.parseInt("04"));

        System.out.println("Your current setttings are:");
        System.out.println("[THEME] --- " + mySettings.getTheme());
        System.out.println("[TIMEZONE] --- " + mySettings.getTimeZone());
        

        myConsole.mainMenu();
        
    }
}


