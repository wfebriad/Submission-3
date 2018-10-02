package id.web.wfebriadi.submissionkamus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.web.wfebriadi.submissionkamus.R;
import id.web.wfebriadi.submissionkamus.adapter.IndonesiaAdapter;
import id.web.wfebriadi.submissionkamus.database.KamusHelper;
import id.web.wfebriadi.submissionkamus.model.IndonesiaModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndonesiaFragment extends Fragment {

    RecyclerView recyclerView;
    IndonesiaAdapter indonesiaAdapter;
    KamusHelper kamusHelper;
    SearchView searchView;
    View rootView;

    public IndonesiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_indonesia, container, false);

        searchView = (SearchView) view.findViewById(R.id.search);
        searchView.setFocusable(true);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        rootView = view.findViewById(R.id.fragment_indonesia);

        indonesiaAdapter = new IndonesiaAdapter(getContext());
        recyclerView.setAdapter(indonesiaAdapter);
        kamusHelper= new KamusHelper(getContext());
        kamusHelper.open();

        indonesiaAdapter = new IndonesiaAdapter(getContext());
        recyclerView.setAdapter(indonesiaAdapter);

        kamusHelper = new KamusHelper(getContext());
        kamusHelper.open();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                kamusHelper = new KamusHelper(getContext());
                indonesiaAdapter = new IndonesiaAdapter(getContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(indonesiaAdapter);

                kamusHelper.open();

                ArrayList<IndonesiaModel> bhsEngModels = kamusHelper.getDataByNameIndonesia(newText);

                kamusHelper.close();
                indonesiaAdapter.addItem(bhsEngModels);

                return false;
            }
        });
        return view;
    }
    public void onResume() {
        super.onResume();
        searchView.setQuery("", false);
        rootView.requestFocus();
    }

}
