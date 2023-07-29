package com.example.lab5_retrofit.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab5_retrofit.Api.Api;
import com.example.lab5_retrofit.R;
import com.example.lab5_retrofit.model.Receiver;
import com.example.lab5_retrofit.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    ArrayList<User> list;
    Context context;

    public UserAdapter(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = list.get(position);

        holder.tv_name.setText(user.getUsername());
        holder.tv_diachi.setText(user.getDiachi());
        holder.tv_email.setText(user.getEmail());

        Glide.with(context)
                .load(user.getImage())
                .into(holder.image);

        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = user.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa người yêu này?");

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Api.api.deleteUser(id).enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Toast.makeText(context, "Xoa thanh cong!!!", Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {

                            }
                        });
                    }
                });
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_update,null);
                builder.setView(view);
                AlertDialog dialog = builder.create();

                Button btn_luu,btn_huy;
                EditText ed_username,ed_diachi,ed_email,ed_quoctich,ed_linkanh;

                ed_diachi = view.findViewById(R.id.ed_diachi);
                ed_email = view.findViewById(R.id.ed_email);
                ed_username = view.findViewById(R.id.ed_username);
                ed_linkanh = view.findViewById(R.id.linkanh);
                btn_huy = view.findViewById(R.id.btn_huy);
                btn_luu = view.findViewById(R.id.btn_luu);

                String idUser = user.getId();
                ed_username.setText(user.getUsername());
                ed_email.setText(user.getEmail());
                ed_diachi.setText(user.getDiachi());
                ed_linkanh.setText(user.getImage());

                btn_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String username = ed_username.getText().toString().trim();
                        String diachi = ed_diachi.getText().toString().trim();
                        String email = ed_email.getText().toString().trim();
                        String linkanh = ed_linkanh.getText().toString().trim();

                        Api.api.updateUser(idUser, new User(username,email,diachi,linkanh)).enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                list.set(holder.getAdapterPosition(), new User(idUser,username,email,diachi,linkanh));
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {

                            }
                        });
                    }
                });

                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_email,tv_diachi,tv_id;
        ImageView image,image_edit,image_delete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_diachi = itemView.findViewById(R.id.tv_diachi);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_name = itemView.findViewById(R.id.tv_name);
            image = itemView.findViewById(R.id.img_image);
            image_delete = itemView.findViewById(R.id.image_delete);
            image_edit = itemView.findViewById(R.id.image_edit);
        }
    }
}
