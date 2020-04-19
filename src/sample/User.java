package sample;

import java.util.ArrayList;

public class User {
  private String userName;
  private boolean online;
  private ArrayList<Song> Songlist; // arr.get(i)  arr[i]   arr.length  arr.size()
  private Song song, ss;
  private int totalTime;
  private ArrayList<MusicExchangeCenter> reqSong;

  public User(String u) {
    userName = u;
    online = false;
    Songlist = new ArrayList<>();
    song = new Song();
    totalTime = 0;
  }


  public void addSong(Song song) { //Add something here to do with owner
    song.setOwner(this);
    Songlist.add(song);
  }

  public void totalSongTime() {
    for (int i = 0; i == Songlist.size(); i++) {
      totalTime += Songlist.get(i).getDuration();
    }
  }

  public void register(MusicExchangeCenter m) {
    m.registerUser(this);
  }

  public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m){
    ArrayList<String> csl = new ArrayList<>();
    int i = 0;
    csl.add("");
    for (Song com:m.allAvailableSongs()){
      csl.add(++i + "     " + com.getTitle() + "     " + com.getArtist() + "     " + com.getMinutes() + "     " + com.getSeconds() + "      " + com.getOwner().userName);
    }
    return csl;
  }

  public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist){
    ArrayList<String> sla = new ArrayList<>();
    int j = 0;
    sla.add("");
    for (Song art:m.availableSongsByArtist(artist)){
      sla.add(++j + "     " + art.getTitle() + "     " + art.getArtist() + "     " + art.getMinutes() + "     " + art.getSeconds() + "      " + art.getOwner().userName);
    }
    return sla;
  }

  public void downloadSong(MusicExchangeCenter m, String title, String ownerName){
    Song play = m.getSong(title, ownerName);
    if (play != null) {
      Song toAdd = new Song(play.getTitle(), play.getArtist(), play.getMinutes(), play.getSeconds());
      toAdd.setOwner(this);
      getSonglist().add(toAdd);
    }
  }

  public String toString()  {
    String s = "" + userName + ": "+ Songlist.size()+ " songs (";
    if (!online) s += "not ";
    return s + "online)";
  }

  public String getUserName() {
    return userName;
  }

  public boolean isOnline() {
    return online;
  }

  public void logoff() {
    online = false;
  }

  public void logon() {
    online = true;
  }

  public boolean getLogon() {
    return isOnline();
  }

  public ArrayList<Song> getSonglist() {
    return Songlist;
  }

  public void setLogon(Boolean on) {
    this.online = on;
  }
}
