package com.mycompany.myapp.xline;

import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.Scene;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class First_Explore extends AppCompatActivity implements ArticlesAdapter.OnGridClickedListener {

    List<Explore_Articles> explore_articlesList = new ArrayList<Explore_Articles>();
    RecyclerView explore_recycler;
    ArticlesAdapter articlesAdapter;
    public static String ARTICLE_TITLE = "Articles_title";
    public static String ARTICLES_BODY = "firsttime";
    ImageView explore_image;
    CardView cardView_nesis;
    RelativeLayout rel_questions;
    public static String USER_EMAIL = "userEmail";
    PopupMenu popup ;
    TextView articles_title;
    TextView articles_body;
    ImageView go_back;
    ImageView menu_item;
    ViewGroup sceneRoot;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor myEditor;
    FloatingActionButton askYourQuestions;
    Scene rootScene, first_scene, second_scene;
//    public static String PROJECT_NAME = "projectname"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_inner);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.green_kindof));
        sceneRoot = (ViewGroup)findViewById(R.id.gen_frame_layout );
        explore_recycler = (RecyclerView) findViewById(R.id.learn_inner);
        explore_image = (ImageView)findViewById(R.id.explore_image);
        articles_body = (TextView)findViewById(R.id.article_body);
        articles_title = (TextView)findViewById(R.id.article_number);
        menu_item = (ImageView)findViewById(R.id.explore_menu);
        cardView_nesis = (CardView)findViewById(R.id.card_view_nesis);
        rel_questions = (RelativeLayout)findViewById(R.id.rel_question);
        go_back = (ImageView)findViewById(R.id.rel_question_back);
        explore_articlesList = new ArrayList<>();

        askYourQuestions = (FloatingActionButton)findViewById(R.id.ask_the_questions);
//        explore_articlesList.add(new Explore_Articles("article one",getResources().getString(R.string.doubleandsingle)));
//        explore_articlesList.add(new Explore_Articles("article two",getResources().getString(R.string.insulators)));
//        explore_articlesList.add(new Explore_Articles("article three",getResources().getString(R.string.support_structure)));
        articlesAdapter = new ArticlesAdapter(this, this, explore_articlesList);
        articlesAdapter.setOnItemClickedListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        explore_recycler.setLayoutManager(mLayoutManager);
        explore_recycler.setItemAnimator(new DefaultItemAnimator());
        explore_recycler.setAdapter(articlesAdapter);
        menu_item.setOnClickListener(ShowpopmenuListener);
        go_back.setOnClickListener(rel_questionsListener);
        askYourQuestions.setOnClickListener(QuestionListener);
        mySharedPreferences =  getSharedPreferences("savedname", Context.MODE_PRIVATE);
        myEditor = mySharedPreferences.edit();
  firebaseAuth = FirebaseAuth.getInstance();
  firebaseUser = firebaseAuth.getCurrentUser();
        setUpExplore_Articles();
    }

    @Override
    public void OnGridClicked(int position, View viewa, View viewb, View viewc) {
        Explore_Articles explore_articles = articlesAdapter.getArticlesList().get(position);
       startExploreActivity(explore_articles,viewa,viewb,viewc);
    }
    public void startExploreActivity(Explore_Articles explore_articles, View viewa, View viewb, View viewc){
        Intent intent = new Intent(this, Explore.class);
        intent.putExtra(ARTICLE_TITLE,explore_articles.getArticleTitle());
        intent.putExtra(ARTICLES_BODY, explore_articles.getArticleBody());
        Pair<View, String> p1 = Pair.create((View)viewa, "article_title");
        Pair<View, String> p2 = Pair.create((View)viewb, "article_body");
        Pair<View, String> p3 = Pair.create((View)viewc, "nerc_" + "image");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2,p3);
        startActivity(intent, options.toBundle());
    }
    View.OnClickListener ShowpopmenuListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          showPopupMenu(v,calcProcessor.getInstance().getExplore_Articles());
        }
    };

View.OnClickListener rel_questionsListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
  rel_questions.setVisibility(View.GONE);
  cardView_nesis.setVisibility(View.VISIBLE);
  askYourQuestions.setVisibility(View.VISIBLE);
    }
};
    private void showPopupMenu(View view,List<Explore_Articles> explore_articles) {
        // inflate menu
        popup = new PopupMenu(this, view);
        for(int i=0;i<explore_articles.size();i++){
            popup.getMenu().add(R.id.groupid,explore_articles.get(i).getArticle_id(),i,explore_articles.get(i).getArticleTitle());
        }

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_calculator, popup.getMenu());
        popup.setOnMenuItemClickListener(new First_Explore.MyMenuItemClickListener());
        popup.show();
    }



    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.ask_question:
                    cardView_nesis.setVisibility(View.GONE);
                    rel_questions.setVisibility(View.VISIBLE);
                    askYourQuestions.setVisibility(View.GONE);
                    break;
                default:
//

                    if(menuItem.getItemId()>0) {
                        openExplore(articlesAdapter.getArticlesList().get(menuItem.getItemId() - 1));
                    }
                    return false;

            }
            return false;
        }
    }
    public  void OperateMenu(){

    }
    public void openExplore(Explore_Articles explore_articles){
        Intent intent = new Intent(this, Explore.class);
        intent.putExtra(ARTICLE_TITLE,explore_articles.getArticleTitle());
        intent.putExtra(ARTICLES_BODY, explore_articles.getArticleBody());
        startActivity(intent);
    }

    public void setUpExplore_Articles(){
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        LiveData<List<Info>> infos = mDataBase.infoDao().LoadAllInfo();
        viewModel.getExplore_articles().observe(this, new Observer<List<Explore_Articles>>() {
                    @Override
                    public void onChanged(@Nullable List<Explore_Articles> explore_articles) {
//                Processors.getInstance().setInfod(infos);
                        List<Explore_Articles> anexplore_Articles = new ArrayList<Explore_Articles>();
                        anexplore_Articles.clear();
                        if (explore_articles.size() > 0) {
                            for (int i = 0; i < explore_articles.size(); i++) {
//                            if (questions.get(i).getMclass().equals(name)) {
//                    if (questions.get(i).isAnswered()) {
//                                    questions.get(i).setRead(Spreferences.ReadState(mysharedpreference, questions.get(i).getQueid(), myeditor, questions.get(i).getNoanswer(), questions.get(i).getNocomment()));
                                Collections.sort(explore_articles, new Comparator<Explore_Articles>() {
                                    @Override
                                    public int compare(Explore_Articles t0, Explore_Articles t1) {
                                        return (int) (t0.getArticle_id() - t1.getArticle_id());
                                    }
                                });


                            }
                            calcProcessor.getInstance().setExplore_Articles(explore_articles);
                            articlesAdapter.setArticlesList(explore_articles);
                            articlesAdapter.notifyDataSetChanged();
//                        infoAdapter.setInfoLists(anquestions);

//                    Information.InfoListsand = Processors.getInstance().getInfod();
//                    Information.infoAdapterand.notifyDataSetChanged();
                        }

                    }

                }
        );

    }

    View.OnClickListener QuestionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(firebaseAuth.getCurrentUser()!=null) {
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(First_Explore.this, askYourQuestions, "people");
                Intent intent = new Intent(First_Explore.this, AskQuestion.class);
                startActivity(intent, options.toBundle());
            }
            else {
                Intent intent = new Intent(First_Explore.this, LoginActivity.class);
                startActivity(intent);
                }

        }
    };

}
