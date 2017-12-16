package kr.laptop.school.petitions.ui.fragments.enroll;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.FragmentSetContentBinding;
import kr.laptop.school.petitions.libs.utils.VerifyUtil;
import kr.laptop.school.petitions.ui.activities.EnrollActivity;

public class SetContentFragment extends Fragment {

    public String title;

    public static SetContentFragment newInstance(String title) {
        SetContentFragment fragment = new SetContentFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    public SetContentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentSetContentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_content, container, false);
        binding.h1.setText(title);
        binding.content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textMonitor.setText(binding.content.getText().length() + "자");
            }
        });

        binding.nextButton.setOnClickListener(v -> {
            if (!VerifyUtil.verifyString(binding.content.getText().toString())) return;
            ((EnrollActivity) getActivity()).update(SetContentFragment.class, binding.content.getText().toString());
        });
        return binding.getRoot();
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
