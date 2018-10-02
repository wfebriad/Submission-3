package id.web.wfebriadi.submissionkamus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.web.wfebriadi.submissionkamus.R;
import id.web.wfebriadi.submissionkamus.model.EnglishModel;

public class EnglishAdapter extends RecyclerView.Adapter<EnglishAdapter.EnglishHolder> {

    private ArrayList<EnglishModel> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public EnglishAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public EnglishAdapter.EnglishHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail, parent, false);
        return new EnglishHolder(view);
    }
    public void addItem(ArrayList<EnglishModel> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull EnglishAdapter.EnglishHolder holder, int position) {
        holder.tvWord.setText(mData.get(position).getWord());
        holder.tvTranslate.setText(mData.get(position).getTranslate());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public int getItemViewType(int position){
        return 0;
    }
    public long getItemId(int position){
        return position;
    }

    public static class EnglishHolder extends RecyclerView.ViewHolder {

        private TextView tvWord;
        private TextView tvTranslate;

        public EnglishHolder(View itemView){
            super(itemView);
            tvWord = (TextView)itemView.findViewById(R.id.word);
            tvTranslate = (TextView)itemView.findViewById(R.id.translate);
        }
    }
}
