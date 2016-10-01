package com.gamila.zm.malmovieapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Zeinab Mohamed on 10/1/2016.
 */
public class GetMovieVideosResponse {

    /**
     * id : 278
     * results : [{"id":"533ec653c3a3685448000247","iso_639_1":
     * "en","iso_3166_1":"US","key":"WawU4ouldxU","name":"Trailer
     * Hd","site":"YouTube","size":720,"type":"Trailer"},
     * {"id":"5604db5ac3a3685c56000ead","iso_639_1":"en","iso_3166_1"
     * :"US","key":"9qqfMvKxBa0",
     * "name":"Peter Fonda On Hope And The Shawshank Redemption","site":"YouTube",
     * "size":360,"type":"Featurette"}]
     */

    private long id;
    /**
     * id : 533ec653c3a3685448000247
     * iso_639_1 : en
     * iso_3166_1 : US
     * key : WawU4ouldxU
     * name : Trailer Hd
     * site : YouTube
     * size : 720
     * type : Trailer
     */

    private ArrayList<VideoInfo> results;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<VideoInfo> getResults() {
        return results;
    }

    public void setResults(ArrayList<VideoInfo> results) {
        this.results = results;
    }

    public static class VideoInfo implements Serializable {
        private String id;
        private String iso_639_1;
        private String iso_3166_1;
        private String key;
        private String name;
        private String site;
        private long size;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
