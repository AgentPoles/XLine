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

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyViewHolder> {
private Context mContext;
OnGridClickedListener Listener;
private List<Conversations> conversationsList;

        Activity activity;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView texta, textb, timea , timeb, titlea, titleb;
//    public ImageView thumbnail;
    public CardView cardView;
    public RelativeLayout rela, relb;

    public MyViewHolder(View view) {
        super(view);
        texta = (TextView) view.findViewById(R.id.texta);
        textb = (TextView)view.findViewById(R.id.textb);
        titlea = (TextView)view.findViewById(R.id.titlea);
        titleb = (TextView)view.findViewById(R.id.titleb);
        timea = (TextView)view.findViewById(R.id.timeViewa);
        timeb = (TextView)view.findViewById(R.id.timeViewb);
       rela= (RelativeLayout)view.findViewById(R.id.rela);
        relb = (RelativeLayout)view.findViewById(R.id.relb);
//                overflow = (ImageView) view.findViewById(R.id.overflow);
//                cardView = (CardView)view.findViewById(R.id.card_view);
    }
}


    public ConversationAdapter(Activity activity, Context mContext, List<Conversations> conversationsList) {
        this.mContext = mContext;
        this.conversationsList = conversationsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conversation_holder, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
                 holder.setIsRecyclable(false);
        final Conversations conversations = conversationsList.get(position);
        if(conversations.isAnn()) {
            holder.relb.setVisibility(View.GONE);
            holder.texta.setText(conversations.getText());
            holder.timea.setText(conversations.getTime());
            if(conversations.getTitle()==null||conversations.getTitle().equals("")){
                holder.titlea.setVisibility(View.GONE);
            }
            else {
                holder.titlea.setText(conversations.getTitle());
            }
        }
        else {
            holder.rela.setVisibility(View.GONE);
            holder.textb.setText(conversations.getText());
            holder.timeb.setText(conversations.getTime());
            if(conversations.getTitle()==null||conversations.getTitle().equals("")){
                holder.titleb.setVisibility(View.GONE);
            }
            else {
                holder.titleb.setText(conversations.getTitle());
            }
        }
//        holder.textViewforinner.setText(album.getValue());

//            holder.otherThings.setText(album.getOtherThing() );

        // loading album cover using Glide library
//            Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);


        holder.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener.OnGridClicked(position);
            }
        });
        holder.relb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener.OnGridClicked(position);
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
        return conversationsList.size();
    }

public interface OnGridClickedListener{
    public void OnGridClicked(int position);
}
    public void setOnItemClickedListener (OnGridClickedListener listener){
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




