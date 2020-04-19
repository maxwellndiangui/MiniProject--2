package sample;

import java.util.*;

public class MusicExchangeCenter {
    private ArrayList<User> users;
    private ArrayList<Song> downloadedSongs;
    private HashMap<String, Float> royalties;

    public MusicExchangeCenter() {
        users = new ArrayList<>();
        downloadedSongs = new ArrayList<>();
        royalties = new HashMap<>();
    }

    public ArrayList<User> onlineUsers() {
        ArrayList<User> change = new ArrayList<User>();
            for (User use:users)
                if (use.isOnline())
                    change.add(use);
            return change;
        }

    public ArrayList<Song> allAvailableSongs() {
        ArrayList<Song> change = new ArrayList<>();
        for (User use:onlineUsers())
            change.addAll(use.getSonglist());
        return change;
    }

    public User userWithName(String s) {
        for (User use:users)
            if (use.getUserName().equals(s))
                return use;
        return null;
    }

    public void registerUser(User x) {
        if (userWithName(x.getUserName()) == null)
            users.add(x);
    }

    public ArrayList<Song> availableSongsByArtist(String artist) { //something needs to be fixed with the artists detector
        ArrayList<Song> change = new ArrayList<>();
        for (Song use:allAvailableSongs())
            if (use.getArtist().equals(artist))
                change.add(use);
        return change;
    }

    public Song getSong(String title, String ownerName){
        for (User use:onlineUsers())
            for (Song songNew:use.getSonglist())
                if (songNew.getTitle().equals(title) && songNew.getOwner().getUserName().equals(ownerName)) {
                    if (royalties.containsKey(songNew.getTitle()))
                        royalties.put(songNew.getTitle(), (float) (royalties.get(songNew.getArtist()) + 0.25));
                    else
                        royalties.put(songNew.getArtist(), 0.25f);
                    downloadedSongs.add(songNew);
                    for (Song ds:uniqueDownloads())
                        if (ds.getTitle().equals(songNew.getTitle())) {
                            ds.setDownloads(ds.getDownloads() + 1);
                        }
                    return songNew;
                }
        return null;
    }

    public void displayRoyalties(){
        System.out.println("Amount   Artist");
        for (String roy:royalties.keySet())
            System.out.println((royalties.get(roy)) + "   " + roy);
    }

    public TreeSet<Song> uniqueDownloads(){
        return (new TreeSet<>(downloadedSongs));
    }

    private ArrayList<Pair<Integer,Song>> updateDlo(){
        ArrayList<Pair<Integer, Song>> dlo = new ArrayList<>();
        for (Song use:uniqueDownloads())
            dlo.add(new Pair<>(use.getDownloads(), use));
        return dlo;
    }

    public ArrayList<Pair<Integer, Song>> songsByPopularity(){
        ArrayList<Pair<Integer, Song>> pop = updateDlo();
        Collections.sort(pop, (p1, p2) -> -(p1.getKey() - p2.getKey()));
        return pop;
    }

        public String toString() {
        return "Music Exchange Center ("+onlineUsers().size()+" users on line, "+allAvailableSongs().size()+" songs available)";
    }

        public ArrayList<User> getUsers() {
        return users;
    }
        public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

        public HashMap<String, Float> getRoyalties() {
        return royalties;
    }

        public ArrayList<Song> getDownloadedSongs() {
        return downloadedSongs;
    }
}
