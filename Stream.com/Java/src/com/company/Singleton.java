package com.company;

import java.util.ArrayList;

public class Singleton {
    Object obj = new Object();
    static private Singleton instance;
    private ArrayList<Follower> mostFollowings;

    private Singleton(){
        mostFollowings = new ArrayList<>();
    }

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }

    public void addMostFollowings(Follower s){
        synchronized (obj){
            mostFollowings.add(s);
        }
    }

    public ArrayList<Follower> getMostFollowings() {
        return mostFollowings;
    }

    public void check(Follower f){
        synchronized (obj){
            for (int i=0 ; i< Singleton.getInstance().getMostFollowings().size() ; i++){
                if (f.getFollowingSize() > Singleton.getInstance().getMostFollowings().get(i).getFollowingSize()){
                    Singleton.getInstance().getMostFollowings().clear();
                    Singleton.getInstance().getMostFollowings().add(f);
                    break;
                }
                if (f.getFollowingSize() == Singleton.getInstance().getMostFollowings().get(i).getFollowingSize()){
                    Singleton.getInstance().getMostFollowings().add(f);
                    break;
                }

            }
        }
    }
}
