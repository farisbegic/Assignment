package com.company;

import java.util.ArrayList;

public class Follower {
    private String name;
    private ArrayList<String> followings;
    private int followingSize;

    public Follower(){
        this.name = "";
        this.followings = new ArrayList<>();
        this.followingSize = -1;
    }

    public Follower(String name, int followingSize) {
        this.name = name;
        followings = new ArrayList<>();
        this.followingSize = followingSize;
    }

    public String getName() {
        return name;
    }

    public void setFollowingSize(int followingSize) {
        this.followingSize = followingSize;
    }

    public int getFollowingSize() {
        return followingSize;
    }


    public ArrayList<String> getFollowings() {
        return followings;
    }

    public void addFollowing(String s){
        followings.add(s);
    }

    void Display(){
        System.out.println(this.name + " " + this.followingSize);
        System.out.println(this.followings);
    }

    void removeDuplicates(){
        ArrayList<String> newArrayList = new ArrayList<>();
        for (String s : followings) {
            if (!newArrayList.contains(s))
                newArrayList.add(s);
        }
        followings = newArrayList;
    }

    boolean isFollowingStreamerMultiple(){
        for (String s : followings) {
            int cnt = 0;
            for (String si : followings) {
                if (s.equals(si)){
                    cnt++;
                }
                if (cnt>1){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "name='" + name + '\'' +
                ", followings=" + followings +
                ", followingSize=" + followingSize +
                '}';
    }
}
