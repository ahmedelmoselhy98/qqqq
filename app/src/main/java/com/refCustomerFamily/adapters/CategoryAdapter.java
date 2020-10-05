package com.refCustomerFamily.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.refCustomerFamily.R;
import com.refCustomerFamily.activities_fragments.activity_family.FamilyActivity;
import com.refCustomerFamily.databinding.ItemCategoryBinding;
import com.refCustomerFamily.models.FamilyCategory;
import com.refCustomerFamily.models.MarketCatogryModel;
import com.refCustomerFamily.models.UserModel;
import com.refCustomerFamily.preferences.Preferences;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterVH> {

    private List<FamilyCategory> list;
    private Context context;
    private LayoutInflater inflater;
    private String lang;
    private FamilyActivity activity;
    int i = 0;

    public CategoryAdapter(List<FamilyCategory> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        Paper.init(context);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        activity = (FamilyActivity) context;
    }

    @NonNull
    @Override
    public CategoryAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false);
        return new CategoryAdapterVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapterVH holder, int position) {
        holder.binding.setModel(list.get(position));
        holder.binding.setLang(lang);
        holder.itemView.setOnClickListener(view -> {
            i = position;
            FamilyCategory familyCategory = list.get(holder.getAdapterPosition());
            activity.showFamilyProducts(familyCategory);
            notifyDataSetChanged();

        });

        if (i == position) {
            holder.binding.name.setBackground(context.getResources().getDrawable(R.drawable.main_category_bg_1));
            holder.binding.name.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.binding.name.setBackground(context.getResources().getDrawable(R.drawable.main_category_bg));
            holder.binding.name.setTextColor(context.getResources().getColor(R.color.gray12));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CategoryAdapterVH extends RecyclerView.ViewHolder {
        public ItemCategoryBinding binding;

        public CategoryAdapterVH(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
