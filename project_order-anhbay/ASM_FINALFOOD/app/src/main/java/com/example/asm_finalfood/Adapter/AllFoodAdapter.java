package com.example.asm_finalfood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.asm_finalfood.Model.CustomFilter;
import com.example.asm_finalfood.Model.Food;
import com.example.asm_finalfood.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.MyViewHolder> implements Filterable {

    public List<Food> foods;
    List<Food> foodFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public AllFoodAdapter(List<Food> foods, Context context, RecyclerViewClickListener listener) {
        this.foods = foods;
        this.foodFilter = foods;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mName.setText(foods.get(position).getName());
        holder.mPrice.setText(foods.get(position).getPrice());
        holder.mDate.setText(foods.get(position).getCreate_day());
        holder.mDescription.setText(foods.get(position).getDescription());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(foods.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture);

        final Boolean love = foods.get(position).getLove();

        if (love){
            holder.mLove.setImageResource(R.drawable.likeon);
        } else {
            holder.mLove.setImageResource(R.drawable.likeof);
        }

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilter((ArrayList<Food>) foodFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private ImageView mLove;
        private TextView mName, mPrice, mDate,mDescription;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.picture);
            mName = itemView.findViewById(R.id.name);
            mPrice = itemView.findViewById(R.id.price);
            mLove = itemView.findViewById(R.id.love);
            mDate = itemView.findViewById(R.id.date);
            mDescription = itemView.findViewById(R.id.description);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
            mLove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                case R.id.love:
                    mListener.onLoveClick(mLove, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
    }

}
