package com.example.hellosqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtName, txtNumber, txtDate, txtId;
    Button btnInsert, btnSelect, btnUpdate, btnDelete;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtNumber = findViewById(R.id.txtNumber);
        txtDate = findViewById(R.id.txtDate);
        txtId = findViewById(R.id.txtId);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        helper = new DatabaseHelper(this);

        btnInsert.setOnClickListener(v -> {
            try{
                String name = txtName.getText().toString();
                String phone = txtNumber.getText().toString();
                String date = txtDate.getText().toString();
                User user = new User(name, phone, date);
                boolean result = helper.insertUser(user);
                if(result){
                    Toast.makeText(this, "Данные успешно добавлены", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Данные не были добавлены", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NullPointerException e){
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){

            }
        });

        btnSelect.setOnClickListener(v -> {
            ArrayList<User> users = helper.getAllUsers();

            StringBuilder stringBuilder = new StringBuilder();
            for (User user : users) {
                stringBuilder.append("Ключ: ").append(user.getId()).append("\n");
                stringBuilder.append("Имя: ").append(user.getName()).append("\n");
                stringBuilder.append("Номер: ").append(user.getPhone()).append("\n");
                stringBuilder.append("Дата рождения: ").append(user.getDate_of_birth()).append("\n\n");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Данные пользователей");
            builder.setMessage(stringBuilder.toString());
            builder.show();
        });

        btnUpdate.setOnClickListener(v -> {
            try{
                int id = Integer.parseInt(txtId.getText().toString());
                String name = txtName.getText().toString();
                String phone = txtNumber.getText().toString();
                String date = txtDate.getText().toString();
                User user = new User(id, name, phone, date);
                boolean result = helper.insertUser(user);
                if(result){
                    Toast.makeText(this, "Данные успешно обновлены", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Данные не были обновлены", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NullPointerException e){
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){

            }
        });

        btnDelete.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(txtId.getText().toString());
                boolean result = helper.deleteUser(id);
                if(result){
                    Toast.makeText(this, "Данные успешно удалены", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Данные не были удалены", Toast.LENGTH_SHORT).show();
                }


            }catch (Exception e){

            }
        });
    }
}