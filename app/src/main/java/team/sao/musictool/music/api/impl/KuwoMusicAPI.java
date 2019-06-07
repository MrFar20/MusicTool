package team.sao.musictool.music.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import team.sao.musictool.music.api.MusicAPI;
import team.sao.musictool.music.entity.Song;
import team.sao.musictool.music.entity.SongList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static team.sao.musictool.music.config.MusicAPIConfig.*;

/**
 * \* Author: MrWangx
 * \* Date: 2019/6/6
 * \* Time: 2:50
 * \* Description:
 **/
public class KuwoMusicAPI implements MusicAPI {

    public static final String MUSIC_TYPE = MUSIC_TYPE_KUWO;

    @Override
    public List<Song> searchSong(String keyword, int pagenum, int pagesize) {
        JSONArray songs_result = null;
        try {
            songs_result = getSongSearchJson(keyword, pagenum, pagesize).getJSONArray("data");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<Song> songs = new ArrayList<>();
        for (Object o : songs_result) {
            try {
                JSONObject song_jo = (JSONObject) o;
                String name = song_jo.getString("SONGNAME");
                String songid = song_jo.getString("MUSICRID").replaceAll("MUSIC_", "");
                String singer = song_jo.getString("ARTIST");
                String albumid = song_jo.getString("ALBUMID");
                String albumname = "暂无信息";
                String imgurl = getSongImgURL(songid);
                String alia = "";
                Integer duration = song_jo.getInteger("DURATION");
                songs.add(new Song(MUSIC_TYPE, name, songid, singer, albumid, albumname, imgurl, alia, duration));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return songs;
    }

    @Override
    public List<Song> getSongsFromSongList(String songlistid) {
        return null;
    }

    @Override
    public List<SongList> searchSongList(String keyword, int pagenum, int pagesize) {
        return null;
    }

    @Override
    public JSONObject getSongListJson(String songlistid) {
        return null;
    }


    @Override
    public JSONObject getSongSearchJson(String keyword, int pagenum, int pagesize) throws IOException {
        return JSON.parseObject(Jsoup.connect(getSongSearchURL(keyword, pagenum, pagesize))
                .header("Content-type", "application/x-www-form-urlencoded")
                .method(Connection.Method.GET)
                .ignoreContentType(true)
                .execute()
                .body())
                ;
    }

    @Override
    public JSONObject getSongListSearchJson(String keyword, int pagenum, int pagesize) {
        return null;
    }

    @Override
    public String getSongListSearchURL(String keyword, int pagenum, int pagesize) {
        return null;
    }

    @Override
    public String getSongListURL(String songlistid) {
        return null;
    }

    @Override
    public String getLyricURL(String songid) {
        return LYRIC_URL(MUSIC_TYPE, songid);
    }


    @Override
    public String getSongImgURL(String songid) {
        return SONG_IMG_URL(MUSIC_TYPE, songid);
    }


    @Override
    public String getSongPlayURL(String songid) {
        return SONG_PLAY_URL(MUSIC_TYPE, songid);
    }


    @Override
    public String getSongSearchURL(String keyword, int pagenum, int pagesize) {
        return SEARCH_URL(MUSIC_TYPE, keyword, SEARCH_TYPE_SONG, pagenum, pagesize);
    }
}