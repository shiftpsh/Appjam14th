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
import kr.laptop.school.petitions.databinding.FragmentSetTitleBinding;
import kr.laptop.school.petitions.libs.utils.VerifyUtil;
import kr.laptop.school.petitions.ui.activities.EnrollActivity;
import kr.laptop.school.petitions.ui.fragments.InterestFragment;

public class SetTitleFragment extends Fragment {



    public SetTitleFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentSetTitleBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_set_title, container, false);

        binding.title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                binding.textMonitor.setText(binding.title.getText().length()+"자");
            }
        });

        binding.nextButton.setOnClickListener(v -> {
            if (!VerifyUtil.verifyString(binding.title.getText().toString())) return;
            ((EnrollActivity) getActivity()).update(SetTitleFragment.class, binding.title.getText().toString());
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
