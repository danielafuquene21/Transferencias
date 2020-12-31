package com.example.transfers.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.transfers.R;
import com.example.transfers.adapter.UserAdapterList;
import com.example.transfers.businessObject.DataBase;
import com.example.transfers.dataObject.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {


    DataBase dataBase;
    String nombreBD;

    FloatingActionButton btnAddUser;


    private GridLayoutManager glm;
    private UserAdapterList adapter;

    private RecyclerView recyclerViewSearchResults;
    private EditText editTextSearch;
    public List<User> listUser = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarVista();
        cargarDB();
        checkPermission();
        llenarListView();
    }

    private void checkPermission() {
        //permisos almacenamiento
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            checkPermissionStorage();
        }
    }
    private void checkPermissionStorage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if ( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE )) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2003);
            } else if (  ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)  ){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2003);
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2003);
            }
        }
    }

    private void cargarDB() {
        dataBase = new DataBase(getApplicationContext());
        nombreBD = dataBase.getDatabaseName();
    }

    private void cargarVista() {
        btnAddUser = (FloatingActionButton) findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(this);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        recyclerViewSearchResults = (RecyclerView) findViewById(R.id.recyclerViewSearchResults);
        glm = new GridLayoutManager(this, 1);
        recyclerViewSearchResults.setLayoutManager(glm);
        recyclerViewSearchResults.setAdapter(adapter);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable e) {
                filterList(e.toString());
            }
        });
    }


    private void llenarListView() {
        listUser = dataBase.mostrarUsuarios();
        adapter = new UserAdapterList(listUser, getApplicationContext(), dataBase);
        recyclerViewSearchResults.setAdapter(adapter);
    }

    private  void filterList (String text){
        ArrayList<User> filterNames= new ArrayList<>();
        for(User s : listUser)
            if(s.getNombrePersonaJuridica().toLowerCase().contains(text.toLowerCase()))
                filterNames.add(s);


            recyclerViewSearchResults.setAdapter(adapter);
            adapter.filterList(filterNames);

    }

    @Override
    protected void onResume() {
        super.onResume();
        llenarListView();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v == btnAddUser) {
            Intent intent = new Intent(this,AddUserActivity.class);
            startActivity(intent);
        }
    }
}
