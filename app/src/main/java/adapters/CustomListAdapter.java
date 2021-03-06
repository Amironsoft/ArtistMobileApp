package adapters;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri.*;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

import ru.artistmobile.R;
import artist.Artist;

public class CustomListAdapter extends ArrayAdapter<Artist> {
    private ArrayList<Artist> artists_list;
    private final Activity context;
    private boolean isNetworkOnlineNow;

    public CustomListAdapter(Activity context, ArrayList<Artist> artists_list, boolean isNetworkOnlineNow) {
        super(context, R.layout.my_list_item, artists_list);
        this.context=context;
        this.artists_list=artists_list;
        this.isNetworkOnlineNow=isNetworkOnlineNow;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_list_item, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt1 = (TextView) rowView.findViewById(R.id.textView1);
        TextView extratxt2 = (TextView) rowView.findViewById(R.id.textView2);
        Artist curArtist=artists_list.get(position);
        txtTitle.setText(curArtist.name);
        int curImg=0;
        if (isNetworkOnlineNow){
                //imageView.setImageURI(android.net.Uri.parse(curArtist.cover.small))// ;
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.stub_image)
                    .showImageForEmptyUri(R.drawable.image_for_empty_url)
                    .resetViewBeforeLoading()
                    .cacheInMemory()
                    .cacheOnDisc()
                    .build();
            ImageLoader.getInstance().displayImage(curArtist.cover.small, imageView,options);
        }else{
            curImg = context.getResources().getIdentifier("id"+curArtist.id , "drawable", context.getPackageName());
            imageView.setImageResource(curImg);
        }
        extratxt1.setText(curArtist.getGenresAsString());
        extratxt2.setText(curArtist.getTracksAndAlbumsAsString(context));
        return rowView;
    };

    public ArrayList<Artist> getData(){
        return artists_list;
    }
}