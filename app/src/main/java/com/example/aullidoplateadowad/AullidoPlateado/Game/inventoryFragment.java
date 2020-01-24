package com.example.aullidoplateadowad.AullidoPlateado.Game;


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aullidoplateadowad.AullidoPlateado.DB.Item;
import com.example.aullidoplateadowad.AullidoPlateado.PrincipalViewModel;
import com.example.aullidoplateadowad.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class inventoryFragment extends Fragment {

    PrincipalViewModel principalViewModel;
    ItemsAdapter itemsAdapter;

    public inventoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_inventory, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        principalViewModel = ViewModelProviders.of(requireActivity()).get(PrincipalViewModel.class);

        RecyclerView itemsRecyclerView = view.findViewById(R.id.item_list);

        itemsAdapter = new ItemsAdapter();
        itemsRecyclerView.setAdapter(itemsAdapter);


        principalViewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                itemsAdapter.estblecerListaItems(items);
            }
        });
    }

    class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>{

        List<Item> items;

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_inventory, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {

            final Item item = items.get(position);

            holder.nombreTextView.setText(item.getItemName());
            holder.descriptionTextView.setText(item.getDescription());

            Glide.with(requireContext()).load(item.getImage()).into(holder.itemImageView);
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        Glide.with(requireContext()).load(item.getImage()).into(holder.itemImageViewDetail);
                        holder.quantityTextView.setText(item.getQuantity() + " " + item.getItemName());
                    }else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                        Glide.with(requireContext()).clear(holder.itemImageViewDetail);
                        holder.quantityTextView.setText("");
                    }
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        public void estblecerListaItems(List<Item> items){
            this.items = items;
            notifyDataSetChanged();
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView nombreTextView, descriptionTextView, quantityTextView;
            ImageView itemImageView, itemImageViewDetail;

            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                nombreTextView = itemView.findViewById(R.id.item_Name);
                descriptionTextView = itemView.findViewById(R.id.item_Description);
                itemImageView = itemView.findViewById(R.id.item_Image);
                itemImageViewDetail = itemView.findViewById(R.id.item_Image_Detail);
                quantityTextView = itemView.findViewById(R.id.item_Quantity);
            }
        }
    }
}
