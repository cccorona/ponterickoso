package mx.com.baseapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;



import mx.com.baseapplication.adapters.CharactersAdapter;
import mx.com.baseapplication.adapters.EpisodioAdapter;
import mx.com.baseapplication.adapters.PagerAdapter;
import mx.com.baseapplication.interfaces.ActionInterface;
import mx.com.baseapplication.model.ApiPageResponse;
import mx.com.baseapplication.model.Character;
import mx.com.baseapplication.model.GenericalError;
import mx.com.baseapplication.utils.AppUtils;
import mx.com.baseapplication.utils.Constants;
import mx.com.baseapplication.utils.Enums;
import mx.com.baseapplication.utils.NetHelper;

public class MainActivity extends BaseActivity implements ActionInterface {


    private RecyclerView list,pagerList;
    boolean isLoading = false;
    private ApiPageResponse lastPage;
    private CharactersAdapter charactersAdapter;
    private EditText searchView;
    private boolean isSearching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        fillData();
        initScrollListener();
        initSearch();
        setBackEnabled(false);
    }


    private void initUI(){
        list = findViewById(R.id.list);
        pagerList = findViewById(R.id.pagination_list);
        searchView = findViewById(R.id.search);




    }

    private void fillData(){
        isSearching = false;
        if(AppUtils.isNetworkAvailable(this)){
            getFirstPage();
        }
    }


    private void initSearch(){
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().length() == 0) {
                    isSearching = false;
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    charactersAdapter.resetData();
                }else{
                    isSearching = true;
                    charactersAdapter.getFilter().filter(s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void initScrollListener(){

        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading && !isSearching) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == charactersAdapter.getItemCount() - 3) {
                        if(AppUtils.isNetworkAvailable(MainActivity.this)){
                            loadMore();
                            isLoading = true;
                        }

                    }
                }


            }
        });

    }



    private void loadMoreWithPage(String page){
        if(AppUtils.isNetworkAvailable(MainActivity.this)){

            showProgress();
            NetHelper netHelper = new NetHelper(this);
            netHelper.setOnDataResultInterface(new NetHelper.OnDataResultInterface() {
                @Override
                public void OnResultOk(Object object) {
                    hideProgress();
                    ApiPageResponse apiPageResponse = (ApiPageResponse) object;
                    LayoutAnimationController controller =
                            AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_animation_right);
                    list.setLayoutAnimation(controller);
                    list.scheduleLayoutAnimation();
                    charactersAdapter = new CharactersAdapter(apiPageResponse.getResults(),MainActivity.this);
                    list.setAdapter(charactersAdapter);

                }

                @Override
                public void OnError(GenericalError error) {
                    hideProgress();

                }
            });
            netHelper.getRequestWithParams(ApiPageResponse.class,
                    "/character/?page="+page,null,null);

        }

    }





    private void loadMore(){
        //showProgress();

        NetHelper netHelper = new NetHelper(this);
        netHelper.setOnDataResultInterface(new NetHelper.OnDataResultInterface() {
            @Override
            public void OnResultOk(Object object) {
                hideProgress();
                lastPage  = (ApiPageResponse) object;
                LayoutAnimationController controller =
                        AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_animation_right);
                list.setLayoutAnimation(controller);
                list.scheduleLayoutAnimation();
                charactersAdapter.addMore(lastPage.getResults());
                isLoading = false;
            }

            @Override
            public void OnError(GenericalError error) {
                hideProgress();

            }
        });
        netHelper.getRequestWithParams(ApiPageResponse.class,
                lastPage.getInfo().getMethod(),null,null);
    }

    private void getFirstPage(){
          showProgress();
          NetHelper netHelper = new NetHelper(this);
          netHelper.setOnDataResultInterface(new NetHelper.OnDataResultInterface() {
              @Override
              public void OnResultOk(Object object) {
                  hideProgress();
                  lastPage  = (ApiPageResponse) object;
                  if(lastPage!=null && lastPage.getInfo()!=null){
                      pagerList.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                              RecyclerView.HORIZONTAL,false));
                      pagerList.setAdapter(new PagerAdapter(lastPage.getInfo().getPages(),
                              MainActivity.this));
                  }
                  LayoutAnimationController controller =
                          AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.layout_animation_right);
                  list.setLayoutAnimation(controller);
                  list.scheduleLayoutAnimation();
                  list.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                          RecyclerView.HORIZONTAL,false));
                  charactersAdapter = new CharactersAdapter(lastPage.getResults(),MainActivity.this);
                  list.setAdapter(charactersAdapter);

              }

              @Override
              public void OnError(GenericalError error) {
                  hideProgress();

              }
          });
          netHelper.getRequestWithParams(ApiPageResponse.class,
                  Constants.GET_CHARACTERS,null,null);
    }

    @Override
    public void OnErrorAction(GenericalError error) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void OnActionSelected(Enums.ACCIONES action, Object... params) {

        if(action == Enums.ACCIONES.SELECTED){
            Character character = (Character) params[0];
               if(character!=null){
                   TextView nameTitle = findViewById(R.id.detail_name);
                   nameTitle.setText(character.getName());
                   ImageView detailIcon = findViewById(R.id.detail_icon);
                   Glide.with(detailIcon.getContext()).load(character.getImage()).into(detailIcon);
                   TextView status = findViewById(R.id.status);
                   TextView specie = findViewById(R.id.specie);
                   TextView origen = findViewById(R.id.origin);
                   TextView location = findViewById(R.id.location);
                   TextView gender = findViewById(R.id.gender);
                   TextView creado = findViewById(R.id.creado);
                   creado.setText(getString(R.string.creado)+character.getCreated());

                   status.setText(getString(R.string.status)+ character.getStatus());
                   specie.setText(getString(R.string.specie) + character.getSpecies());
                   if(character.getOrigin()!=null){
                       origen.setText(getString(R.string.origen) + character.getOrigin().getName());
                   }
                   if(character.getLocation()!=null){
                       location.setText(getString(R.string.locacion) + character.getLocation().getName());
                   }

                   gender.setText(getString(R.string.genero) + character.getGender());


                   RecyclerView list_episodio = findViewById(R.id.list_episodio);
                   list_episodio.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                           RecyclerView.HORIZONTAL,false));
                   list_episodio.setAdapter(new EpisodioAdapter(character.getEpisode()));



               }
        }else if(action == Enums.ACCIONES.PAGER_SELECTED){
            int pageSelected = (int) params[0];
            loadMoreWithPage(""+pageSelected);
        }


    }
}
