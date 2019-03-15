package com.upadhyde.android.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.upadhyde.android.databinding.AdapterOutListBinding;
import com.upadhyde.android.db.table.Input;
import com.upadhyde.android.db.table.Output;

import java.util.List;

public class OutputListRecyclerViewAdapter extends RecyclerView.Adapter<OutputListRecyclerViewAdapter.OutListRecyclerViewHolder> {

    private List<Output> outputs;
    private Input input;

    public OutputListRecyclerViewAdapter(List<Output> outputList, Input input) {
        this.outputs = outputList;
        this.input = input;
    }

    @NonNull
    @Override
    public OutListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        AdapterOutListBinding adapterInputListBinding = AdapterOutListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new OutputListRecyclerViewAdapter.OutListRecyclerViewHolder(adapterInputListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OutListRecyclerViewHolder holder, int position) {
        holder.adapterOutListBinding.tvDescription.setText(input.getDescription());
        holder.adapterOutListBinding.tvItemData.setText(String.valueOf(outputs.get(position).getBatchItemNo()));
        holder.adapterOutListBinding.tvBatchNoData.setText(String.valueOf(outputs.get(position).getActualBatch()));
        holder.adapterOutListBinding.tvFif0BatchNoData.setText(String.valueOf(outputs.get(position).getFefoBatchNo()));
    }

    @Override
    public int getItemCount() {
        return outputs != null ? outputs.size() : 0;
    }

    public void updateDataSet(Output item){
        outputs.add(item);
        notifyDataSetChanged();
    }


    class OutListRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AdapterOutListBinding adapterOutListBinding;

        OutListRecyclerViewHolder(AdapterOutListBinding adapterOutListBinding) {
            super(adapterOutListBinding.getRoot());
            this.adapterOutListBinding = adapterOutListBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // to do check use of this 
        }
    }

}

