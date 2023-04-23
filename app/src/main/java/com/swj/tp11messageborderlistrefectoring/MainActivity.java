package com.swj.tp11messageborderlistrefectoring;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.snackbar.Snackbar;
import com.swj.tp11messageborderlistrefectoring.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<BorderItem> borderItems = new ArrayList<>();
    BorderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        adapter = new BorderAdapter(MainActivity.this, borderItems);
        binding.recyclerview.setAdapter(adapter);

        binding.appcompatbtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, BorderItemActivity.class);
            resultLauncher.launch(intent);
        });

        setSupportActionBar(binding.toolbar);
    }

    ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode() == RESULT_OK) {
                                Intent intent = result.getData();
                                String name = intent.getStringExtra("name");
                                String nickname = intent.getStringExtra("nickname");
                                String title = intent.getStringExtra("title");
                                String content = intent.getStringExtra("content");

                                borderItems.add(0, new BorderItem(name, nickname, title, content));
                                adapter.notifyItemInserted(0);
                                binding.recyclerview.scrollToPosition(0);
                            } else if(result.getResultCode() == RESULT_CANCELED) {
                                Snackbar.make(binding.recyclerview, "취소했습니다.", Snackbar.LENGTH_INDEFINITE)
                                        .setAction("OK", view -> {}).show();
                            }
                        }
                    }
            );
}