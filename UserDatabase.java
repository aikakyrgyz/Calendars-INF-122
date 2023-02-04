
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {

    private static UserDatabase myDatabase = new UserDatabase();
    Map<String, Person> users;

    // Singleton class
    private UserDatabase()
    {
        users = new HashMap<String, Person>();
    }


    boolean deleteUser(Person user)
    {
        try{
        users.remove(user.username, user);
        return true;
        }
        catch(Exception e)
        {
            return false;
        }
        
    }
    String getAllUsers()
    {
        

        String output = "UserID\t\t Username\t\t # of Calendars\n";

        for(Person p: users.values())
        {
            output+= p + "\n";
        }
        if(users.size() == 0)
        {
            output+= " \n       --------- NO REGISTERED USERS ---------\n";
        }
        
        return output;
    }

    public static UserDatabase getInstance()
    {
        return myDatabase;
    }

    Person getUser(String username)
    {
        return users.get(username);
    }

    void addNewUser(String username, Person user)
    {
        users.put(username, user);
    }

    void deleteUser(String username)
    {
        users.remove(username);
    }

    boolean isPresent(String username)
    {
        return users.containsKey(username);
    }

}
