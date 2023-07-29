package com.example.lab5_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab5_retrofit.Api.Api;
import com.example.lab5_retrofit.adapter.UserAdapter;
import com.example.lab5_retrofit.model.Receiver;
import com.example.lab5_retrofit.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn_hienthi,btn_them;
    RecyclerView rcv;
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcv.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcv.addItemDecoration(itemDecoration);

        list = new ArrayList<>();
        hienthi();
    }
    private void anhxa() {
        btn_them = findViewById(R.id.btn_them);
        rcv= findViewById(R.id.rcv);

//        btn_hienthi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hienthi();
//            }
//        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,add_edit_user.class));
            }
        });
    }
    private void hienthi(){
        Api.api.getDanhsach().enqueue(new Callback<Receiver>() {
            @Override
            public void onResponse(Call<Receiver> call, Response<Receiver> response) {
                if (response.body() != null){
                    list = response.body().getData();
                    UserAdapter adapter = new UserAdapter(list, getBaseContext());
                    rcv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Receiver> call, Throwable t) {

            }
        });
    }
}