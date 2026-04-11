package com.oop.fmradiostation.Sabbir.radio_jockey;

public class Playlist {
    private ArrayList<Song> songs;

    public Playlist(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "songs=" + songs +
                '}';
    }
}
