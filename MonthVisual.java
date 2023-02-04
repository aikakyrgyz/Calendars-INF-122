import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Locale;

import java.util.*;


class MonthVisual implements Movable, Displayable
{
    static String[] dayTitles = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri",
    "Sat"};

    int year;
    String currentMonthName;
    int month;
    int monthLength;
    int firstDayOfMonth; 
    Calendar cal;
    Calendar temp;


    public MonthVisual()
    {
        cal = Calendar.getInstance();
        currentMonthName = cal.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.US);
        firstDayOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK); //assuming the calendar was just initialized
        monthLength = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        temp = (Calendar)cal.clone();
    }

    public String display()
    {

        String output = "Today's date: " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR) +"\n";
        // working with the temp  cal since it will change as the user moves around
        String name = temp.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.US);
        int monthLength = temp.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        temp.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfMonth = temp.get(GregorianCalendar.DAY_OF_WEEK); //assuming the calendar was just initialized
        output+= "\n\n\t\t\t " + name.toUpperCase() + ", " + temp.get(Calendar.YEAR) + "\n" ;
        

         for (String dayTitle: dayTitles)
        {
            output += dayTitle + " \t"; 
        }


        output += String.format("\n");

        for(int i = 0; i < firstDayOfMonth-1;  i++){
            output += String.format("  \t");
        }

        for (int i = 0, dayOfMonth = 1; dayOfMonth <= monthLength; i++) {
            for (int j = ((i == 0) ? firstDayOfMonth - 1 : 0); j < 7 && (dayOfMonth <= monthLength); j++) {
                output += String.format("%2d\t ", dayOfMonth);
                dayOfMonth++;
            }
            output+= "\n";
        }
        return output;
    }

   public void moveToNext() 
   {
        temp.add(Calendar.MONTH, 1);
   }

   public void moveToPrevious()
   {
        temp.add(Calendar.MONTH, -1);
   }

   int getCurrentMonth()
   {
        return temp.get(Calendar.MONTH);
   }

   void viewAllEvents(Map<Integer,Event> events)
   {
        int count = 0;
        System.out.println("********************************************************\n");
        System.out.println("Events of month: " + temp.getDisplayName(Calendar.MONTH, Calendar.LONG , Locale.US));
        System.out.println("\n");

        String heading = "ID\t\t Title\t\t Date\t\t Start Time\t End Time\t";
        System.out.println(heading);

        for (Map.Entry<Integer,Event> mapElement : events.entrySet()) 
        {
            Integer key = mapElement.getKey();
            // Adding some bonus marks to all the students
            Event event = mapElement.getValue();
            // System.out.println("Events month: " + event.getEventMonth());

            // System.out.println("Views month: " + temp.get(Calendar.MONTH));

            if (event.getEventMonth() == temp.get(Calendar.MONTH) && event.getEventYear() == temp.get(Calendar.YEAR))
            {
                count++;
                System.out.println(key + "\t\t" + event.getTitle() + "\t      " + event.getDate() + "\t     " + event.getStartTime() + "\t    " + event.getEndTime());
            }
        }
        if(count == 0)
        {
            System.out.println("\t\t\t ---- NO EVENTS ----");
        }

        System.out.println("********************************************************\n");

}


}