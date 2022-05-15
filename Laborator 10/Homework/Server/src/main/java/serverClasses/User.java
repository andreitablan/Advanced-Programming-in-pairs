package serverClasses;

import java.util.*;

public class User {
    public String name;
    private List<User> friends = new ArrayList<User>();
    private HashMap<User,List<String>> messages= new HashMap<>();

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

    public void addFriend(User user)
    {
        this.friends.add(user);
    }

    public void addMessage(User user, String message){
     messages.put(user, Collections.singletonList(message));
    }

    public HashMap<User, List<String>> getMessages() {
        return messages;
    }
}