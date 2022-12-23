package com.example.books;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {


    Button Add;
    Button Back;
    EditText name;
    EditText price;
    EditText countSklad;
    EditText countMagazine;
    EditText opisanie;
    EditText otzivy;

    String nPicture;
    ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button add = findViewById(R.id.btnAdd);
        add.setOnClickListener(this);
        Button Back = findViewById(R.id.btnGoBack);
        EditText name = findViewById(R.id.idTitle);
        EditText price = findViewById(R.id.idCost);
        EditText countSklad = findViewById(R.id.idStockAvailability);
        EditText countMagazine = findViewById(R.id.idAvailabilityInTheStore);
        EditText opisanie = findViewById(R.id.idDescription);
        EditText otzivy = findViewById(R.id.idRewiews);
        picture = findViewById(R.id.nullPhoto);
        picture.setOnClickListener(this);


    }

    private void PostCreate(String Title, Integer Cost, Integer StockAvailability, Integer AvailabilityInTheStore, String Description, String Rewiews, String Image){
        try{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://ngknn.ru:5001/NGKNN/МамшеваЮС/Экзамен/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
            DataModal dataModal = new DataModal(0, Title, Cost, StockAvailability, AvailabilityInTheStore, Description, Rewiews, Image);
            Call<DataModal> call = retrofitApi.createPost(dataModal);
            call.enqueue(new Callback<DataModal>() {
                @Override
                public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(AddActivity.this, "Попробуйте еще раз, пожалуйста.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddActivity.this, "Товар успешно добавлен", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        price.setText("");
                        countSklad.setText("");
                        countMagazine.setText("");
                        opisanie.setText("");
                        otzivy.setText("");

                    }
                }

                @Override
                public void onFailure(Call<DataModal> call, Throwable t) {
                    Toast.makeText(AddActivity.this, "Произошла ошибка при добавлении", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e){

        }

    }
    public final ActivityResultLauncher<Intent> pickImg = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri uri = result.getData().getData();
                        try {
                            InputStream is = getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            picture.setImageBitmap(bitmap);
                            nPicture = encodeImage(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


    public static String encodeImage(Bitmap bitmap) {
        int prevW = 500;
        int prevH = bitmap.getHeight() * prevW / bitmap.getWidth();

        Bitmap b = Bitmap.createScaledBitmap(bitmap, prevW, prevH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return java.util.Base64.getEncoder().encodeToString(bytes);
        }
        return "";
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnAdd:{
                Button Back = findViewById(R.id.btnGoBack);
                EditText name = findViewById(R.id.idTitle);
                EditText price = findViewById(R.id.idCost);
                EditText countSklad = findViewById(R.id.idStockAvailability);
                EditText countMagazine = findViewById(R.id.idAvailabilityInTheStore);
                EditText opisanie = findViewById(R.id.idDescription);
                EditText otzivy = findViewById(R.id.idRewiews);
                if(name.getText().length() == 0 || price.getText().toString().length() == 0  || countSklad.getText().length() == 0  || countMagazine.getText().length() == 0  || opisanie.getText().length() == 0  || otzivy.getText().length() == 0 ){
                    Toast.makeText(this, "Одно или несколько полей не были заполнены", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    String Name = name.getText().toString();
                    Integer Price = Integer.valueOf(price.getText().toString());
                    Integer CountSklad =  Integer.valueOf(countSklad.getText().toString());
                    Integer CountMagazine = Integer.valueOf(countMagazine.getText().toString());
                    String Opisanie = opisanie.getText().toString();
                    String Otzivy = otzivy.getText().toString();
                    PostCreate(Name, Price, CountSklad, CountMagazine, Opisanie, Otzivy, nPicture );
                    startActivity(new Intent(AddActivity.this, ShowActivity.class));
                }
            }
            break;

            case R.id.nullPhoto:{

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImg.launch(intent);
            }
            break;

        }
    }
}