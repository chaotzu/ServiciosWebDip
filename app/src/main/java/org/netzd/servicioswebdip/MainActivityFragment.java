package org.netzd.servicioswebdip;

import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.netzd.servicioswebdip.webservices.Entity;
import org.netzd.servicioswebdip.webservices.JSONParser;
import org.netzd.servicioswebdip.webservices.Petition;

import java.security.spec.ECField;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView videosRecyclerView = null;
    private List<Video> videos=null;

    public static MainActivityFragment newInstance(){
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        return mainActivityFragment;
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();

        StrictMode.setThreadPolicy(policy);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videosRecyclerView = (RecyclerView) view.findViewById(R.id.videoRecyclerView);
        JSONParser jsonParser = new JSONParser();
        try{
            videos = jsonParser.getVideos("http://www.omdbapi.com/?s=superman&apikey=2b28d307&r=json",new Petition((Entity.NONE)));//traer de la api los videos
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            videosRecyclerView.setHasFixedSize(true);
            videosRecyclerView.setLayoutManager(layoutManager);
            VideoAdapter videoAdapter = new VideoAdapter(videos);
            videosRecyclerView.setAdapter(videoAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
