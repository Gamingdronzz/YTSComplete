package com.gamingdronzz.yts.Models;

/**
 * Created by balpreet on 3/26/2018.
 */

public class TorrentData {
    String url;
    String hash;
    String quality;
    String seeds;
    String peers;
    String size;
    String size_bytes;
    String date_uploaded;
    String date_uploaded_unix;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getSeeds() {
        return seeds;
    }

    public void setSeeds(String seeds) {
        this.seeds = seeds;
    }

    public String getPeers() {
        return peers;
    }

    public void setPeers(String peers) {
        this.peers = peers;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize_bytes() {
        return size_bytes;
    }

    public void setSize_bytes(String size_bytes) {
        this.size_bytes = size_bytes;
    }

    public String getDate_uploaded() {
        return date_uploaded;
    }

    public void setDate_uploaded(String date_uploaded) {
        this.date_uploaded = date_uploaded;
    }

    public String getDate_uploaded_unix() {
        return date_uploaded_unix;
    }

    public void setDate_uploaded_unix(String date_uploaded_unix) {
        this.date_uploaded_unix = date_uploaded_unix;
    }

    public TorrentData() {
    }
}
