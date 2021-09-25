package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class FindMax implements Runnable {
    private ArrayList<Follower> followers;
    private Object obj = new Object();

    public FindMax(ArrayList<Follower> followers) {
        this.followers = followers;
    }

    @Override
    public void run() {
        for (Follower f : this.followers) {
            synchronized (obj){
                Singleton.getInstance().check(f);
            }
        }

    }
}
