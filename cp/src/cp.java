/*
Simulate a social Network where nodes represent users represent friendships.
Players must recommend new friends for a user by identifying mutual friends
or friends of friends
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Graph {
    private ArrayList<ArrayList<String>> adjacencyList;
    private ArrayList<String> users;

    public Graph() {
        adjacencyList = new ArrayList<>();
        users = new ArrayList<>();
    }
    public void addUser(String user) {
        if (!users.contains(user)) {
            users.add(user);
            adjacencyList.add(new ArrayList<>());
        }
    }
    public void addFriendship(String user1, String user2) {
        addUser(user1);
        addUser(user2);
        int index1 = users.indexOf(user1);
        int index2 = users.indexOf(user2);
        adjacencyList.get(index1).add(user2);
        adjacencyList.get(index2).add(user1);
    }
    public List<String> getFriends(String user) {
        int index = users.indexOf(user);
        return (index != -1) ? adjacencyList.get(index) : new ArrayList<>();
    }
    public void displayNetwork() {
        System.out.println("{");
        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            List<String> friends = adjacencyList.get(i);
            System.out.print("  \"" + user + "\": [");
            for (int j = 0; j < friends.size(); j++) {
                System.out.print("\"" + friends.get(j) + "\"");
                if (j < friends.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("],");
        }
        System.out.println("}");
    }
    public List<String> recommendFriends(String user) {
        List<String> recommendations = new ArrayList<>();
        List<String> ConnectedFriends = getFriends(user);

        if (ConnectedFriends.isEmpty()) {
            System.out.println("User not found or has no friends in the network.");
            return recommendations;
        }
        for (String friend : ConnectedFriends) {
            List<String> friendsOfFriend = getFriends(friend);
            for (String potentialFriend : friendsOfFriend) {
                if (!potentialFriend.equals(user) && !ConnectedFriends.contains(potentialFriend) && !recommendations.contains(potentialFriend)) {
                    recommendations.add(potentialFriend);
                }
            }
        }
        return recommendations;
    }
}

public class cp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph socialNetwork = new Graph();
        socialNetwork.addFriendship("Dhaval", "Satej");
        socialNetwork.addFriendship("Dhaval", "Swaraj");
        socialNetwork.addFriendship("Dhaval", "Om");
        socialNetwork.addFriendship("Satej", "Ved");
        socialNetwork.addFriendship("Satej", "Vallabh");
        socialNetwork.addFriendship("Swaraj", "Ved");
        socialNetwork.addFriendship("Swaraj", "Yash");
        socialNetwork.addFriendship("Swaraj", "Pavan");
        socialNetwork.addFriendship("Om", "Yash");
        socialNetwork.addFriendship("Om", "Vallabh");
        socialNetwork.addFriendship("Om", "Sir");
        socialNetwork.addFriendship("Ved", "Mam");
        socialNetwork.addFriendship("Yash", "Sir");
        socialNetwork.addFriendship("Yash", "Mam");
        socialNetwork.addFriendship("Sir", "Mam");
        socialNetwork.addFriendship("Vallabh", "Dhaval");
        socialNetwork.addFriendship("Vallabh", "Om");
        socialNetwork.addFriendship("Mam", "Ved");
        socialNetwork.addFriendship("Mam", "Sir");
        socialNetwork.addFriendship("Mam", "Yash");
        socialNetwork.addFriendship("Pavan", "Swaraj");
        socialNetwork.displayNetwork();
        System.out.println("Enter Name: ");
        String user = sc.next();
        List<String> recommendations = socialNetwork.recommendFriends(user);
        System.out.println("\nFriend recommendations for " + user + ": " + recommendations);
    }
}