package sample;

public class Song implements Comparable <Song>{
  private String title;
  private String artist;
  private int duration,downloads;
  private User owner;

  public Song()  {
    this("", "", 0, 0);
  }

  public Song(String t, String a, int m, int s)  {
    owner = null;
    title = t;
    artist = a;
    duration = m * 60 + s;
    downloads = 0;
  }

  public int compareTo(Song song){
    return title.compareTo(song.title);
  }

  public String getTitle() {
    return title;

  }
  public String getArtist() {
    return artist;

  }
  public int getDuration() {
    return duration;

  }
  public User getOwner(){
    return owner;
  }

  public int getMinutes() {
    return duration / 60;
  }

  public int getSeconds() {
    return duration % 60;
  }

  public int getDownloads() {
    return downloads;
  }
  public void setDownloads(int downloads) {
    this.downloads = downloads;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public String toString()  {
    return("\"" + title + "\" by " + artist + " " + (duration / 60) + ":" + (duration % 60));
  }
}