import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.sql.Time;
import java.util.Scanner;

public class Event {
    // String fajr_prayertime  =       prayerTimes.get(0);
    // DateFormat formatter = new SimpleDateFormat("HH:mm");
    // Time timeValue = newTime(formatter.parse(fajr_prayertime).getTime());
    String title;
    // Time startTime;
    // Time endTime;
    String startTime;
    String endTime;
    String location;
    Person owner;
    Calendar cal;
    Calendar eventDateTime;
    static int ID = 0;
    int id;
    // Event(){}

    Event(String title, String startTime, String endTime, int day, int month, int year)
    {
        id = ID++;
        this.title = title;
        eventDateTime = new GregorianCalendar();
        eventDateTime.set(Calendar.DAY_OF_MONTH, day);
        eventDateTime.set(Calendar.MONTH, month);
        eventDateTime.set(Calendar.YEAR, year);
        // "HH-MM"
        eventDateTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.split("-")[0]));
        eventDateTime.set(Calendar.MINUTE, Integer.parseInt(startTime.split("-")[1]));
        this.startTime = startTime;
        this.endTime = endTime;


        // eventData


        // DateFormat formatter = new SimpleDateFormat("HH:mm");
        // this.startTime = new Time(formatter.parse(startTime).getTime());
        // this.endTime = new Time(formatter.parse(endTime).getTime());
        // this.owner = owner; 
        // this.cal = cal;

    }


    int getid()
    {
        return id;
    }

    String getTitle()
    {
        return title;
    }

    void editEvent()
    {
        System.out.println("What would you like to change:");
            String[] options = {"1 --- Date",
                                "2 --- Time",
                                "3 --- Title"
                                // "3 --- ",
                                // "4 --- Move to next",
                                // "5 --- Move to previous",
                                // "6 --- Back to the View Menu"
                                // "4- ",
                    };
                for (String e : options) {
                    System.out.println(e);   
                }  
                int option = 1;
                Scanner scanner = new Scanner(System.in);
                option = scanner.nextInt();
                
        switch(option)
        {
            case 1:
            {
                changeDate();
                break;
            }
            case 2:
            {
                changeTime();
                break;
            }
            case 3:
            {
                changeTitle();
                break;
            }

        }
        // return new Event();
    }


    int getEventMonth()
    {
        return eventDateTime.get(Calendar.MONTH);
    }


    String getStartTime(){
        return startTime;

    }

    String getEndTime() {
        return endTime;
    }

    void changeDate()
    {
        System.out.println("Please enter the new date in the MM-DD-YYYY format: ");
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.nextLine();
        String[] dateArr = dateString.split("-");
        int day = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int year = Integer.parseInt(dateArr[2]);
        eventDateTime.set(Calendar.DAY_OF_MONTH, day);
        eventDateTime.set(Calendar.MONTH, month);
        eventDateTime.set(Calendar.YEAR, year);
        eventDateTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTime.substring(0, 1)));
        eventDateTime.set(Calendar.MINUTE, Integer.parseInt(startTime.substring(2 )));

    }

    void changeTime()
    {
        System.out.println("Please enter the new start time in the HH-MM format: ");
        Scanner scanner = new Scanner(System.in);
        String timeString = scanner.nextLine();
        this.startTime = timeString;
        String[] timeArr = timeString.split("-");
        int hours = Integer.parseInt(timeArr[0]);
        int min = Integer.parseInt(timeArr[1]);
        eventDateTime.set(Calendar.HOUR_OF_DAY, hours);
        eventDateTime.set(Calendar.MINUTE, min);


        System.out.println("Please enter the new end time in the HH-MM format: ");
        String endTimeString = scanner.nextLine();
        this.endTime = endTimeString;
        // String[] endTimeArr = endTimeString.split("-");


    }

    void changeTitle()
    {
        System.out.println("Please enter new title ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        this.title = title;
    }


    String getDate()
    {
       return (eventDateTime.get(Calendar.MONTH)+1) + "/" + (eventDateTime.get(Calendar.DATE)) + "/" +  eventDateTime.get(Calendar.YEAR);
    }

}
