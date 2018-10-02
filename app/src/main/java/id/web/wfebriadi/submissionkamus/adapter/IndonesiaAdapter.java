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
import id.web.wfebriadi.submissionkamus.model.IndonesiaModel;

public class IndonesiaAdapter extends RecyclerView.Adapter<IndonesiaAdapter.IndonesiaHolder> {

    private ArrayList<IndonesiaModel> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public IndonesiaAdapter(Context context){
        this.context = context;
        mInflater  = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public IndonesiaAdapter.IndonesiaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail, parent, false);
        return new IndonesiaHolder(view);
    }
    public void addItem(ArrayList<IndonesiaModel> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull IndonesiaAdapter.IndonesiaHolder holder, int position) {
        holder.tvWord.setText(mData.get(position).getWord());
        holder.tvTranslate.setText(mData.get(position).getTranslate());
    }
    public int getItemViewType(int position){
        return 0;
    }
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class IndonesiaHolder extends RecyclerView.ViewHolder {
        private TextView tvWord;
        private TextView tvTranslate;

        public IndonesiaHolder(View itemView){
            super(itemView);
            tvWord = (TextView)itemView.findViewById(R.id.word);
            tvTranslate = (TextView)itemView.findViewById(R.id.translate);
        }
    }
}
