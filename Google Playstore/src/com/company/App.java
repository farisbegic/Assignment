package com.company;

public class App implements Comparable<App> {
    private String name;
    private String category;
    private double rating;
    private int reviews;
    private String size;
    private int installs;
    private String type;
    private double price;
    private String contentRating;
    private String genre;
    private String date;
    private String currentVersion;
    private String androidVersion;

    public App(String name, String category, double rating, int reviews, String size, int installs, String type, double price, String contentRating, String genre, String date, String currentVersion, String androidVersion) {
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.reviews = reviews;
        this.size = size;
        this.installs = installs;
        this.type = type;
        this.price = price;
        this.contentRating = contentRating;
        this.genre = genre;
        this.date = date;
        this.currentVersion = currentVersion;
        this.androidVersion = androidVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getInstalls() {
        return installs;
    }

    public void setInstalls(int installs) {
        this.installs = installs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    @Override
    public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                ", reviews=" + reviews +
                ", size='" + size + '\'' +
                ", installs=" + installs +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", contentRating='" + contentRating + '\'' +
                ", genre='" + genre + '\'' +
                ", date='" + date + '\'' +
                ", currentVersion='" + currentVersion + '\'' +
                ", androidVersion='" + androidVersion + '\'' +
                '}';
    }

    @Override
    public int compareTo(App o) {
        if ((getInstalls()*getPrice()) == (o.getInstalls()*o.getPrice())){
            return 0;
        }
        if ((getInstalls()*getPrice()) > (o.getInstalls()*o.getPrice())){
            return -1;
        }
        else{
            return 1;
        }
    }
}
