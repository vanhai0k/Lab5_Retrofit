package com.example.lab5_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab5_retrofit.Api.Api;
import com.example.lab5_retrofit.adapter.UserAdapter;
import com.example.lab5_retrofit.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_edit_user extends AppCompatActivity {

    List<User> list;
    UserAdapter adapter;
    EditText ed_username,ed_diachi,ed_email,ed_quoctich,ed_linkanh;
    TextView tv_tb;
    Button btn_luu,btn_huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);

        anhxa();
        list = new ArrayList<>();

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(add_edit_user.this, MainActivity.class));
            }
        });

    }
    private void postData(){
        String username = ed_username.getText().toString().trim();
        String diachi = ed_diachi.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String linkanh = ed_linkanh.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(diachi)|| TextUtils.isEmpty(email) || TextUtils.isEmpty(linkanh)){
            Toast.makeText(this, "Vui long khong de trong", Toast.LENGTH_SHORT).show();
            return;
        }

        Api.api.postData(new User(username,email,diachi,linkanh))
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(add_edit_user.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                        ed_username.setText("");
                        ed_diachi.setText("");
                        ed_email.setText("");
                        ed_linkanh.setText("");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(add_edit_user.this, "Call API Fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void anhxa() {
        ed_diachi = findViewById(R.id.ed_diachi);
        ed_email = findViewById(R.id.ed_email);
        ed_username = findViewById(R.id.ed_username);
        ed_linkanh = findViewById(R.id.linkanh);
        btn_huy = findViewById(R.id.btn_huy);
        btn_luu = findViewById(R.id.btn_luu);
        tv_tb = findViewById(R.id.tv_tb);
    }
}