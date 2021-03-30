package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Follower> followers = new ArrayList<>();
        ArrayList<String> streamers = new ArrayList<>();
        File followersFile = new File("inputData/followers.txt");
        try {
            Scanner scan = new Scanner(followersFile);
            while (scan.hasNextLine()){
                String[] parts = scan.nextLine().split(", ");
                Follower temp = new Follower(parts[0].trim(), Integer.parseInt(parts[1]));
                followers.add(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File streamerFile = new File("inputData/streamer.txt");
        try {
            Scanner scan = new Scanner(streamerFile);
            while (scan.hasNextLine()){
                streamers.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File followingsFile = new File("inputData/followings.txt");
        try {
            Scanner scan = new Scanner(followingsFile);
            while (scan.hasNextLine()){
                String[] parts = scan.nextLine().split(", ");
                for (int i=0 ; i<followers.size() ; i++){
                    if (followers.get(i).getName().equals(parts[0])){
                        if (streamers.contains(parts[1].trim())){
                            followers.get(i).addFollowing(parts[1].trim());
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File output1 = new File("outputData/MultipleFollowsOfOneStreamer.txt");
        try {
            FileWriter writer = new FileWriter(output1.getPath());
            for (Follower f : followers) {
                if (f.isFollowingStreamerMultiple()){
                    writer.write(f.getName() + f.getFollowings() + "\n");
                }
            } writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File output2 = new File("outputData/MadeAtLeastOneFollow.txt");
        try {
            FileWriter writer = new FileWriter(output2.getPath());
            for (Follower f : followers) {
                f.removeDuplicates();
                if(f.getFollowings().size()>=1){
                    writer.write(f.getName() + "\n");
                }
            } writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File output3 = new File("outputData/WrongCalculation.txt");
        try {
            FileWriter writer = new FileWriter(output3.getPath());
            for (Follower f : followers) {
                if (f.getFollowings().size() != f.getFollowingSize()){
                    writer.write(f.getName() + "\n");
                    f.setFollowingSize(f.getFollowings().size());
                }
            } writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int pivot = (int) Math.ceil(followers.size()/2);
        ArrayList<Follower> firstPart = new ArrayList<>(followers.subList(0,pivot));
        ArrayList<Follower> secondPart = new ArrayList<>(followers.subList(pivot, followers.size()));

        FindMax fmFirstPart = new FindMax(firstPart);
        FindMax fmSecondPart = new FindMax(secondPart);

        Thread t1 = new Thread(fmFirstPart);
        Thread t2 = new Thread(fmSecondPart);

        Follower temp = new Follower();
        Singleton.getInstance().getMostFollowings().add(temp);


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        File output4 = new File("outputData/UserWithMaxFollowings.txt");
        try {
            FileWriter writer = new FileWriter(output4.getPath());
            for (int i=0 ; i<Singleton.getInstance().getMostFollowings().size() ; i++){
                writer.write(Singleton.getInstance().getMostFollowings().get(i).getName() + ", " + Singleton.getInstance().getMostFollowings().get(i).getFollowings() + "\n");
            } writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // OUTPUT ORIGINAL FILES AGAIN
        File outputFollowers = new File("outputData/followers.txt");
        try {
            FileWriter writer = new FileWriter(outputFollowers.getPath());
            for (Follower f : followers) {
                writer.write(f.getName() + ", " + f.getFollowingSize() + "\n");
            } writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outputStreamers = new File("outputData/streamer.txt");
        try {
            FileWriter writer = new FileWriter(outputStreamers.getPath());
            for (String s : streamers) {
                writer.write(s + "\n");
            } writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outputFollowings = new File("outputData/followings.txt");
        try {
            FileWriter writer = new FileWriter(outputFollowings.getPath());
            for (int i=0 ; i<followers.size() ; i++){
                for (int j=0 ; j<followers.get(i).getFollowingSize() ; j++){
                    writer.write(followers.get(i).getName() + ", " + followers.get(i).getFollowings().get(j) + "\n");
                }
            } writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
