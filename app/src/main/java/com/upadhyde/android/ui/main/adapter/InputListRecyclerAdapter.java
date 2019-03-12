package com.upadhyde.android.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upadhyde.android.databinding.AdapterInputListBinding;
import com.upadhyde.android.db.Input;

import java.util.List;

public class InputListRecyclerAdapter extends RecyclerView.Adapter<InputListRecyclerAdapter.InputListRecyclerViewHolder> {

    private List<Input> inputs;
    private InputItemClick inputItemClick;

    public InputListRecyclerAdapter(List<Input> inputList, InputItemClick inputItemClick) {
        this.inputs = inputList;
        this.inputItemClick = inputItemClick;
    }


    @NonNull
    @Override
    public InputListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        AdapterInputListBinding adapterInputListBinding = AdapterInputListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new InputListRecyclerAdapter.InputListRecyclerViewHolder(adapterInputListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull InputListRecyclerViewHolder holder, int position) {
        holder.adapterInputListBinding.tvDescription.setText(inputs.get(position).getDescription());
        holder.adapterInputListBinding.tvPlantNoData.setText(String.valueOf(inputs.get(position).getPlantId()));
        holder.adapterInputListBinding.tvMaterialNoData.setText(String.valueOf(inputs.get(position).getMaterialNo()));
        holder.adapterInputListBinding.tvBatchNoData.setText(String.valueOf(inputs.get(position).getBatchItemNo()));
        holder.adapterInputListBinding.tvDeliveryNoData.setText(String.valueOf(inputs.get(position).getDeliveryNo()));
        holder.adapterInputListBinding.tvDeliveryItemNoData.setText(String.valueOf(inputs.get(position).getDeliveryItemNo()));
        holder.adapterInputListBinding.tvCityData.setText(inputs.get(position).getCustomerCity());
        holder.adapterInputListBinding.tvFif0BatchNoData.setText(String.valueOf(inputs.get(position).getFefoBatchNo()));
    }

    @Override
    public int getItemCount() {
        return inputs != null ? inputs.size() : 0;
    }

    class InputListRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AdapterInputListBinding adapterInputListBinding;

        InputListRecyclerViewHolder(AdapterInputListBinding adapterInputListBinding) {
            super(adapterInputListBinding.getRoot());
            this.adapterInputListBinding = adapterInputListBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            inputItemClick.itemClick();
        }
    }

    public interface InputItemClick {
        void itemClick();
    }

}
