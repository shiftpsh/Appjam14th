package kr.laptop.school.petitions.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.laptop.school.petitions.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InterestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InterestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterestFragment extends Fragment {

    private String parameter;

    public InterestFragment() {
    }

    public static InterestFragment newInstance(String parameter) {
        InterestFragment fragment = new InterestFragment();
        Bundle args = new Bundle();
        args.putString("parameter", parameter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parameter = getArguments().getString("parameter");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interest, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
