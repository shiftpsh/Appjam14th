package kr.laptop.school.petitions.ui.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import kr.laptop.school.petitions.R;
import kr.laptop.school.petitions.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    public ActivityDashboardBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
    }
}
