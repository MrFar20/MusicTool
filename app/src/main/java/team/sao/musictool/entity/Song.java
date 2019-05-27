package team.sao.musictool.entity;

import com.alibaba.fastjson.annotation.JSONField;
import team.sao.musictool.config.MusicType;
import team.sao.musictool.util.NetEaseMusicUtil;

/****
 * @author:MrWangx
 * @description
 * @Date 2018/12/23 3:33
 *****/
public class Song {

    private int musicType;      //音乐类型
    private String name;        //歌名
    private String songid;     //歌曲mid
    @JSONField(serialize = false)
    private String downloadUrl;        //下载的相对地址
    private String singer;      //歌手
    private Integer albumid;     //专辑id
    private String albumname;   //专辑名
    private String subtitle;    //专辑副标题
    private String time;        //时长

    public Song() {}

    public Song(int musicType, String name, String songid, String downloadUrl, String singer, Integer albumid, String albumname, String subtitle, String time) {
        this.musicType = musicType;
        this.name = name;
        this.songid = songid;
        this.downloadUrl = downloadUrl;
        this.singer = singer;
        this.albumid = albumid;
        this.albumname = albumname;
        this.subtitle = subtitle;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", songid='" + songid + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", singer='" + singer + '\'' +
                ", albumid=" + albumid +
                ", albumname='" + albumname + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @JSONField(serialize = false)
    public String getFormatTime() {
        if (time != null) {
            try {
                int t = Integer.parseInt(time);
                return String.format("%02d:%02d", t / 60, t % 60);
            } catch (Exception e) {
                return time;
            }
        }
        return time;
    }

    public int getMusicType() {
        return musicType;
    }

    public void setMusicType(int musicType) {
        this.musicType = musicType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getDownloadUrl() {
        if (musicType == MusicType.QQ_MUSIC) {
            return downloadUrl;
        } else if (musicType == MusicType.NETEASE_MUSIC) {
            return (downloadUrl == null ? downloadUrl = NetEaseMusicUtil.getSongPlayUrl(songid) : downloadUrl);
        }
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Integer getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
