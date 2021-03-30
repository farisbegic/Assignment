package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static ArrayList<App> readFromFile(String s) {
        ArrayList<App> apps = new ArrayList<>();
        File file = new File(s);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
            String[] partsString = scan.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            /*
                Regex ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)" is doing the following:
                - The comma in the beginning splits the string normally
                (?:[^\"]*\"[^\"]*\") says to .split method to not split if this pattern is recognised, and
                the pattern is " ,"aaa" ", meaning it says to .split following: if it finds comma followed by " followed by text and " again, go through it without splitting
                *[^"]*$ means that after .split method went through previous expression without splitting, catch some possible characters after " until you reach comma or the end.
                It is kinda hard to write the explanation, if it's not clear, I can explain it better in class.
             */
            int partsSize = partsString.length;
            while (scan.hasNextLine()){
                String[] parts = scan.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (parts.length == partsSize){
                    apps.add(new App(parts[0],parts[1],checkRating(parts[2]),Integer.parseInt(parts[3]), parts[4], checkInstalls(parts[5]), parts[6], checkPrice(parts[7]), parts[8], parts[9], parts[10], parts[11], parts[12]));
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return apps;
    }

    private static double checkPrice(String part) {
        part = part.replaceAll("\\$", "");
        part = part.replaceAll("\"", "");
        return Double.parseDouble(part);
    }

    private static int checkInstalls(String part) {
        part = part.replaceAll("\\+", "");
        part = part.replaceAll("\"", "");
        part = part.replaceAll(",", "");
        return Integer.parseInt(part);
    }

    private static double checkRating(String part) {
        if (part.equals("NaN")) return 0;
        else return Double.parseDouble(part);
    }

    public static void main(String[] args) {
        ArrayList<App> apps = readFromFile("data/googleplaystore 2.csv");
        Map<String, ArrayList<App>> mapa = new HashMap<>();
        for (App it : apps) {
            if (it.getGenre().contains(";")){
                String[] parts = it.getGenre().split(";");
                if (mapa.containsKey(parts[0])){
                    mapa.get(parts[0]).add(it);
                } else{
                    mapa.put(parts[0], new ArrayList<>());
                    mapa.get(parts[0]).add(it);
                }
                if (mapa.containsKey(parts[1])){
                    mapa.get(parts[1]).add(it);
                } else{
                    mapa.put(parts[1], new ArrayList<>());
                    mapa.get(parts[1]).add(it);
                }
            } else {
                if (mapa.containsKey(it.getGenre())) {
                    mapa.get(it.getGenre()).add(it);
                } else {
                    mapa.put(it.getGenre(), new ArrayList<>());
                    mapa.get(it.getGenre()).add(it);
                }
            }
        }
        // TOP 10 APPS THAT EARNED MOST MONEY
        System.out.println("TOP 10 Apps that earned most money");
        Collections.sort(apps);
        for (int i=0 ; i<10 ; i++){
            System.out.println(i+1 + ". " + apps.get(i).getName() + " with revenue of  " + apps.get(i).getInstalls() * apps.get(i).getPrice());
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        // GENRE(S) WITH BEST RATING
        double sum=0;
        int totalReviews = 0;
        double maxRating = -1;
        int tempReviews = 0;
        for (String s : mapa.keySet()) {
            for (App a : mapa.get(s)) {
                sum+= a.getRating() * a.getReviews();
                totalReviews+= a.getReviews();
            }
            double avgRating = sum / totalReviews;
            if (maxRating < avgRating){
                maxRating = avgRating;
            }
            sum=0;
            totalReviews = 0;
        }
        // WAY TO HANDLE IF THERE ARE MULTIPLE GENRES WITH SAME MAX RATING
        for (String s : mapa.keySet()) {
            for (App a : mapa.get(s)) {
                sum += a.getRating() * a.getReviews();
                totalReviews+= a.getReviews();
            }
            double avgRating = sum / totalReviews;
            if (avgRating == maxRating)
                System.out.println(s + " with rating of " + maxRating);
            sum=0;
            totalReviews=0;
        }
    }
}
