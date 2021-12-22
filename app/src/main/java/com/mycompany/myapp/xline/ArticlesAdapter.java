package com.mycompany.myapp.xline;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>
{

    private Context mContext;
    private List<Explore_Articles> articlesList;
  ArticlesAdapter.OnGridClickedListener Listener;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView article_title, article_body;
        public ImageView thumbnail;
        public CardView cardView;
        ImageView explore_image;
        public RelativeLayout relinnerCalculator;

        public MyViewHolder(View view) {
            super(view);
            article_title = (TextView) view.findViewById(R.id.article_number);
            article_body  = (TextView) view.findViewById(R.id.article_body);
            explore_image = (ImageView)view.findViewById(R.id.explore_image);
//                otherThings = (TextView) view.findViewById(R.id.count);
//            thumbnail = (ImageView) view.findViewById(R.id.innerholderimage);
//            relinnerCalculator = (RelativeLayout)view.findViewById(R.id.relinnnercalculator);
//                overflow = (ImageView) view.findViewById(R.id.overflow);
                cardView = (CardView)view.findViewById(R.id.explore_card);
        }
    }


    public ArticlesAdapter(Activity activity, Context mContext, List<Explore_Articles> articlesList) {
        this.mContext = mContext;
        this.articlesList = articlesList;
    }

    @Override
    public ArticlesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_explore, parent, false);

        return new ArticlesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ArticlesAdapter.MyViewHolder holder, final int position) {
        final Explore_Articles articles = articlesList.get(position);
        holder.article_title.setText(articles.getArticleTitle());
        holder.article_body.setText(articles.getArticleBody());

//            holder.otherThings.setText(album.getOtherThing() );

        // loading album cover using Glide library
//            Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener.OnGridClicked(position,holder.article_title,holder.article_body,holder.explore_image);
            }
        });
    }

/**
 * Showing popup menu when tapping on 3 dots
 //         */
//        private void showPopupMenu(View view) {
//            // inflate menu
//            PopupMenu popup = new PopupMenu(mContext, view);
//            MenuInflater inflater = popup.getMenuInflater();
//            inflater.inflate(R.menu.menu_calculator, popup.getMenu());
//            popup.setOnMenuItemClickListener(new com.mycompany.myapp.xline.CalculatorAdapter.MyMenuItemClickListener());
//            popup.show();
//        }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public List<Explore_Articles> getArticlesList() {
        return articlesList;
    }

    public interface OnGridClickedListener{
        public void OnGridClicked(int position,View viewa, View viewb, View viewc );
    }
    public void setOnItemClickedListener (ArticlesAdapter.OnGridClickedListener listener){
        this.Listener = listener;
    }

    public void setArticlesList(List<Explore_Articles> articlesList) {
        this.articlesList = articlesList;
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        activity.startPostponedEnterTransition();
                        return true;
                    }
                });
    }
}


