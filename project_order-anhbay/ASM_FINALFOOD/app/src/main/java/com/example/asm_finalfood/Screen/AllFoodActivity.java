package com.example.asm_finalfood.Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asm_finalfood.Adapter.AllFoodAdapter;
import com.example.asm_finalfood.Constans.ApiInterface;
import com.example.asm_finalfood.Constans.Constants;
import com.example.asm_finalfood.Model.Food;
import com.example.asm_finalfood.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFoodActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AllFoodAdapter allFoodAdapter;
    private List<Food> foodList;
    ApiInterface apiInterface;
    AllFoodAdapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_food);

        apiInterface = Constants.getApiClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new AllFoodAdapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {

                Intent intent = new Intent(AllFoodActivity.this, EditorActivity.class);
                intent.putExtra("id", foodList.get(position).getId());
                intent.putExtra("name", foodList.get(position).getName());
                intent.putExtra("price", foodList.get(position).getPrice());
                intent.putExtra("picture", foodList.get(position).getPicture());
                intent.putExtra("description",foodList.get(position).getDescription());
                intent.putExtra("create_day", foodList.get(position).getCreate_day());
                startActivity(intent);

            }

            @Override
            public void onLoveClick(View view, int position) {

                final int id = foodList.get(position).getId();
                final Boolean love = foodList.get(position).getLove();
                final ImageView mLove = view.findViewById(R.id.love);

                if (love){
                    mLove.setImageResource(R.drawable.likeof);
                    foodList.get(position).setLove(false);
                    updateLove("update_love", id, false);
                    allFoodAdapter.notifyDataSetChanged();
                } else {
                    mLove.setImageResource(R.drawable.likeon);
                    foodList.get(position).setLove(true);
                    updateLove("update_love", id, true);
                    allFoodAdapter.notifyDataSetChanged();
                }

            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllFoodActivity.this, EditorActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Food...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                allFoodAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                allFoodAdapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getFood(){

        Call<List<Food>> call = apiInterface.getFood();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                progressBar.setVisibility(View.GONE);
                foodList = response.body();
                Log.i(AllFoodActivity.class.getSimpleName(), response.body().toString());
                Log.d("All Products: ", response.toString());
                allFoodAdapter = new AllFoodAdapter(foodList, AllFoodActivity.this, listener);
                recyclerView.setAdapter(allFoodAdapter);
                allFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(AllFoodActivity.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateLove(final String key, final int id, final Boolean love){

        Call<Food> call = apiInterface.updateLove(key, id, love);

        call.enqueue(new Callback<Food>() {
            @Override
            public void onResponse(Call<Food> call, Response<Food> response) {

                Log.i(AllFoodActivity.class.getSimpleName(), "Response "+response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    Toast.makeText(AllFoodActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AllFoodActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {
                Toast.makeText(AllFoodActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFood();
    }

}
