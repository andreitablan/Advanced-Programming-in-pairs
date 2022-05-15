package serverClasses;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    List<User> friends = new ArrayList<User>();

    public User(String name) {
        this.name = name;
    }

}