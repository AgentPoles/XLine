package com.mycompany.myapp.xline;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CalculatorAdapter extends  RecyclerView.Adapter<CalculatorAdapter.MyViewHolder> {
    private Context mContext;
    private List<CalculatorElements> albumList;
    CalculatorAdapter.OnGridClickedListener Listener;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView toBefound, otherThings;
        public ImageView thumbnail, overflow;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            toBefound = (TextView) view.findViewById(R.id.title);
            otherThings = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            cardView = (CardView)view.findViewById(R.id.card_view);
        }
    }


    public CalculatorAdapter(Activity activity, Context mContext, List<CalculatorElements> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calculate_entry_holder, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CalculatorElements album = albumList.get(position);
        holder.toBefound.setSelected(true);
        holder.toBefound.setText(album.getToBeFound());

        holder.otherThings.setText(album.getOtherThing() );

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener.OnGridClicked(position,holder.thumbnail,holder.toBefound,album.getThumbnail(),album.getToBeFound(),album.getWorkingCalculations());
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_calculator, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

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
        return albumList.size();
    }

    public interface OnGridClickedListener{
        public void OnGridClicked(int position, View viewA, View viewB, int thumbnailid, String tobefound, ArrayList<workingCalculation> workingCalculations);
    }
    public void setOnItemClickedListener (CalculatorAdapter.OnGridClickedListener listener){
        this.Listener = listener;
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

