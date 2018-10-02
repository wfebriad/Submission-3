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
import id.web.wfebriadi.submissionkamus.adapter.EnglishAdapter;
import id.web.wfebriadi.submissionkamus.database.KamusHelper;
import id.web.wfebriadi.submissionkamus.model.EnglishModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends Fragment {

    RecyclerView recyclerView;
    EnglishAdapter englishAdapter;
    KamusHelper kamusHelper;
    SearchView searchView;
    View rootView;

    public EnglishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_english, container, false);

        searchView = (SearchView)view.findViewById(R.id.search);
        searchView.setFocusable(true);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        rootView = view.findViewById(R.id.fragment_english);

        englishAdapter = new EnglishAdapter(getContext());
        recyclerView.setAdapter(englishAdapter);
        kamusHelper = new KamusHelper(getContext());
        kamusHelper.open();

        englishAdapter = new EnglishAdapter(getContext());
        recyclerView.setAdapter(englishAdapter);

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
                englishAdapter = new EnglishAdapter(getContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(englishAdapter);

                kamusHelper.open();
                ArrayList<EnglishModel> englishModels = kamusHelper.getDataByNameEnglish(newText);

                kamusHelper.close();
                englishAdapter.addItem(englishModels);
                return false;
            }
        });
        return view;
    }
    public void onResume(){
        super.onResume();
        searchView.setQuery("", false);
        rootView.requestFocus();
    }

}
