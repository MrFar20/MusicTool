package team.sao.musictool.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import team.sao.musictool.R;
import team.sao.musictool.annotation.ViewID;
import team.sao.musictool.entity.Song;

import java.util.List;

import static team.sao.musictool.annotation.AnnotationProcesser.*;

/**
 * \* Author: MrWangx
 * \* Date: 2019/5/27
 * \* Time: 17:44
 * \* Description:
 **/
public class SongListViewAdapter extends BaseAdapter {

    @ViewID(R.id.tv_song_name)
    private TextView songName;
    @ViewID(R.id.tv_singer_album)
    private TextView singerAlbum;
    @ViewID(R.id.tv_time)
    private TextView time;
    @ViewID(R.id.ic_play)
    private ImageView play;
    @ViewID(R.id.ic_like)
    private ImageView like;
    @ViewID(R.id.ic_download)
    private ImageView download;

    private LayoutInflater inflater;
    private Context context;
    private List<Song> songs;

    public SongListViewAdapter(Context context, List<Song> songs) {
        this.context = context;
        this.songs = songs;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = songs.get(position);
        convertView = inflater.inflate(R.layout.songlist_item, null);
        inject(this, convertView);

        songName.setText(song.getName());
        singerAlbum.setText(song.getSinger() + "-" + song.getAlbumname());
        time.setText(song.getFormatTime());

        return convertView;
    }
}
