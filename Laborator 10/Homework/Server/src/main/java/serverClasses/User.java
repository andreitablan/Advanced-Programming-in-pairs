package serverClasses;

import java.util.*;

/**
 * This class describes a specific user.
 */

public class User {
    public String name;
    private List<User> friends = new ArrayList<User>();
    private HashMap<User, List<String>> messages = new HashMap<>();

    public User() {
    }
    /**
     * This method adds a message to the messages list.
     * @param user
     * @param message
     */
    public void addMessage(User user, String message) {
        for (User user1 : friends) {
            if (!messages.containsKey(user)) {
                messages.put(user, new ArrayList<String>());
            }

            messages.get(user).add(message);
        }
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

    public void addFriend(User user) {
        this.friends.add(user);
    }

    public HashMap<User, List<String>> getMessages() {
        return messages;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void setMessages(HashMap<User, List<String>> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", friends=" + friends +
                ", messages=" + messages +
                '}';
    }
}