package serverClasses;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    List<User> friends = new ArrayList<User>();

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}