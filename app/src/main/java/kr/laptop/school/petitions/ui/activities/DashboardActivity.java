package kr.laptop.school.petitions.ui.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

     ActivityDashboardBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        /*
        ActivityDashboardBinding bindinga = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_dashboard, null, false);
        bindinga.getRoot();*/
    }
}