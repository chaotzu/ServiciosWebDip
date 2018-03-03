package org.netzd.servicioswebdip;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.netzd.servicioswebdip.webservices.Entity;
import org.netzd.servicioswebdip.webservices.JSONParser;
import org.netzd.servicioswebdip.webservices.Petition;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alumno12 on 24/02/18.
 */



public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos=null;

    public VideoAdapter(List<Video> contacts) {
        if(contacts==null){
            this.videos=new ArrayList<>();
        }else {
            this.videos = contacts;
        }
    }




    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new VideoViewHolder(item);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        //holder.thumbnailImageView.setImageResource(videos.get(position).getPoster());
        holder.titleTextView.setText(videos.get(position).getTitle());
        holder.typeTextView.setText(videos.get(position).getType());

        BitmapFactory.Options bmOptions;
        bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;

        Bitmap bm = LoadImage(videos.get(position).getPoster(), bmOptions);
        holder.thumbnailImageView.setImageBitmap(bm);


    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder{

        protected ImageView thumbnailImageView=null;
        protected TextView titleTextView=null;
        protected TextView typeTextView=null;

        public VideoViewHolder(View itemView) {
            super(itemView);
            thumbnailImageView=(ImageView) itemView.findViewById(R.id.thumbnailImageView);
            titleTextView=(TextView) itemView.findViewById(R.id.titleTextView);
            typeTextView=(TextView) itemView.findViewById(R.id.typeTextView);
        }
    }


    private Bitmap LoadImage(String URL, BitmapFactory.Options options)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            JSONParser rss = new JSONParser();
            //leo la cadena de bytes de la conexion
            in = rss.createConnection(URL,new Petition(Entity.NONE)).getInputStream();
            //convierte en archivo bitmap
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}


/*
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter
        .VideoViewHolder>{

    private List<Video> videos;


    public VideoAdapter(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent,false);
        return new VideoViewHolder(item);
    }


    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        holder.titleTextView.setText(videos.get(position).getTitle());
        holder.typeTextView.setText(videos.get(position).getType());
        BitmapFactory.Options bmOptions;
        bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;
        Bitmap bm = LoadImage(videos.get(position).getPoster(),bmOptions);
        holder.thumbnailImageView.setImageBitmap(bm);

        // holder.imagen.setText(videos.get(position).getPoster());


        //holder.imagen.setImageResource(videos.get(position).getPoster());

    }

    private Bitmap LoadImage(String URL, BitmapFactory.Options options)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            JSONParser rss = new JSONParser();
            in = rss.createConnection(URL,new Petition(Entity.NONE)).getInputStream();
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    static class VideoViewHolder extends RecyclerView.ViewHolder{


        protected TextView titleTextView=null;
        protected TextView typeTextView=null;
        protected ImageView thumbnailImageView=null;
        //protected ImageView imagen=null;



        public VideoViewHolder(View itemView) {
            super(itemView);

            //tituloTextView=(TextView) itemView.findViewById(R.id.tituloTextView);
            //artistaTextView=(TextView) itemView.findViewById(R.id.artistaTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* if(cancion!=null && onClickCancionListener!=null)
                        onClickCancionListener.onClickCancion(cancion);*//*
                }
            });
        }

        /*public void setCancion(Cancion cancion) {
            this.cancion = cancion;
        }

        public void setOnClickCancionListener(OnClickCancionListener onClickCancionListener) {
            this.onClickCancionListener = onClickCancionListener;
        }*//*
    }
}







        /*
        extends RecyclerView.Adapter<ProductocomosehaceAdaptador.ProdComoseHaceViewHolder> {

    private ArrayList<ProductocomosehaceBean> bean;
    //Acá va la APIKEY, no lo pongo solo para no mostrarlo.
    private static String API_KEY = "MyApiKey";


    public ProductocomosehaceAdaptador(ArrayList<ProductocomosehaceBean> prod){
        this.bean = prod;
    }


    @Override
    public ProdComoseHaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_recycler_comosehace,parent,false);

        return new ProdComoseHaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProdComoseHaceViewHolder holder, int position) {
        //holder.imagen.setImageUrl(bean.get(position).getImagen());
        //Picasso.with(context).load(bean.get(position).getImagen()).into(holder.imagen);

        final int i = position;

        holder.descrip.setText(bean.get(position).getDescripcion());
        Log.i("Link", bean.get(position).getLink());
        Log.i("Imagen",bean.get(position).getImagen());


        //poner videos..

        YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView) holder.view.findViewById(R.id.youtubeThumb);
        youTubeThumbnailView.initialize(API_KEY, new YouTubeThumbnailView.OnInitializedListener(){


            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(bean.get(i).getLink());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();

                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        Log.e("ErrorReason", errorReason.toString());

                    }
                });

            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    public static class ProdComoseHaceViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        //SmartImageView imagen;
        AutofitTextView descrip;
        YouTubeThumbnailView view;



        public ProdComoseHaceViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.CardViewComoSeHace);
            //imagen = (SmartImageView) itemView.findViewById(R.id.SmartImageProductoComoSeHace);
            descrip = (AutofitTextView) itemView.findViewById(R.id.txtComoSeHace);
            view = (YouTubeThumbnailView) itemView.findViewById(R.id.youtubeThumb);

            Typeface type = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Roboto-Medium.ttf");
            descrip.setTypeface(type);

        }
    }
}*/