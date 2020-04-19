package com.example.emailrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.emailrecyclerview.adapters.emailAdapter;
import com.example.emailrecyclerview.model.MailModel;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {
    List<MailModel> items;
    List<MailModel> filtelist;//các sự kiện liên quan đến search, favorite, xóa
    emailAdapter adapter;
    boolean statusFavorite=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filtelist=new ArrayList<>();
        items=new ArrayList<>();
        items.add(new MailModel("Edutila.com","$19 Only (First 10 spots) - Bestselling hiihi","Are you looking to Lean Web Des...","1:30 PM"));
        items.add(new MailModel("Google","Cảnh báo bảo mật","Thiết bị mới đăng nhập vào tài khoản của bạn","1:30 PM"));
        items.add(new MailModel("Zoom","Please activate your Zoom account","Welcome to Zooom! To activate your account","2:30 PM"));
        items.add(new MailModel("The Qt Company","Qt Account email verification needed","Hi, Thank you for creating a Qt Account","12:30 PM"));
        items.add(new MailModel("Noreply","Cisco - Account Created","Welcome to Cisco your account has been successfully create","9:30 AM"));
        Faker faker=new Faker();
        for(int i=0;i<20;i++)
        {
            items.add(new MailModel(faker.name.name(),faker.lorem.sentence(),faker.lorem.paragraph(),"10:50 PM"));
        }

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new emailAdapter(items);
        recyclerView.setAdapter(adapter);
        //xóa 1 phần tử
        ItemTouchHelper.SimpleCallback callback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                if(statusFavorite) {
                    MailModel item=filtelist.get(pos);
                    if(items.contains(item)) {
                        items.remove(item);
                    }
                    filtelist.remove(pos);
                }
                else
                {
                    items.remove(pos);
                }
                adapter.notifyItemRemoved(pos);

            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        //Tìm kiếm
        EditText txtSearch=findViewById(R.id.txt_search);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString().trim());
            }
        });
        Button ds_favorite=findViewById(R.id.ds_favorite);
        ds_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusFavorite=!statusFavorite;
                if(statusFavorite)
                {
                    filtelist.clear();
                    for(MailModel item: items)
                    {
                        if(item.isIsfavorite()) filtelist.add(item);
                    }
                    adapter.filterAdapterList(filtelist);
                }
                else
                {
                    adapter.filterAdapterList(items);
                }
            }
        });

    }
    //
    public void filter(String search)
    {
        filtelist.clear();
        for(MailModel item:items)
        {
            if(item.getFullname().toLowerCase().contains(search.toLowerCase())||item.getContent().toLowerCase().contains(search.toLowerCase())||item.getSubject().toLowerCase().contains(search.toLowerCase()))
            {
                filtelist.add(item);
            }
        }
        adapter.filterAdapterList(filtelist);
    }

}
