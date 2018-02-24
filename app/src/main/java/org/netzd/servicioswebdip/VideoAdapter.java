package org.netzd.servicioswebdip;

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

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Alumno12 on 24/02/18.
 */


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
        holder.tituloTextView.setText(videos.get(position).getTitle());
        holder.tipoTextView.setText(videos.get(position).getType());
        holder.imagen.setText(videos.get(position).getPoster());
        //holder.imagen.setImageResource(videos.get(position).getPoster());

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }


    static class VideoViewHolder extends RecyclerView.ViewHolder{

        protected TextView tituloTextView=null;
        protected TextView tipoTextView=null;
        protected TextView imagen=null;
        //protected ImageView imagen=null;



        public VideoViewHolder(View itemView) {
            super(itemView);

            //tituloTextView=(TextView) itemView.findViewById(R.id.tituloTextView);
            //artistaTextView=(TextView) itemView.findViewById(R.id.artistaTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* if(cancion!=null && onClickCancionListener!=null)
                        onClickCancionListener.onClickCancion(cancion);*/
                }
            });
        }

        /*public void setCancion(Cancion cancion) {
            this.cancion = cancion;
        }

        public void setOnClickCancionListener(OnClickCancionListener onClickCancionListener) {
            this.onClickCancionListener = onClickCancionListener;
        }*/
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