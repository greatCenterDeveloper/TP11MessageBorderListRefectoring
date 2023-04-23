package com.swj.tp11messageborderlistrefectoring;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.swj.tp11messageborderlistrefectoring.databinding.ActivityBorderItemBinding;

public class BorderItemActivity extends AppCompatActivity {

    ActivityBorderItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBorderItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnCancel.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(R.layout.dialog_cancel);
            builder.setPositiveButton("확인", (dialogInterface, i) -> {});
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        });

        binding.btnComplete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(R.layout.dialog_complete);
            builder.setNegativeButton("취소", (dialogInterface, i) -> {});
            builder.setPositiveButton("확인", (dialogInterface, i) -> {
                String name = binding.textInputEtName.getText().toString();
                String nickname = binding.textInputEtNickName.getText().toString();
                String title = binding.textInputEtTitle.getText().toString();
                String content = binding.textInputEtContent.getText().toString();

                Intent intent = getIntent();
                intent.putExtra("name", name);
                intent.putExtra("nickname", nickname);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                setResult(RESULT_OK, intent);
                finish();
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        });
    }
}