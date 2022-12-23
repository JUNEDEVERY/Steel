package com.example.books;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener {


    EditText title;
    EditText idCost;
    EditText idDescription;
    EditText idAvailabilityInTheStore;
    EditText idStockAvailability;
    EditText idRewiews;
    Button btnGoBack;
    Button btnAddChange;
    Button idBtnDelete;
    Button btnDeletePhoto;


    String nPicture;
    ImageView picture;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bitmap bitmap = null;
                    ImageView imageView = (ImageView) findViewById(R.id.nullPhoto);
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Uri selectedImage = result.getData().getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imageView.setImageBitmap(null);
                        imageView.setImageBitmap(bitmap);
                        TextView deletePicture = findViewById(R.id.btnAddChange);
                        deletePicture.setVisibility(View.VISIBLE);
                        nPicture = BitMapToString(bitmap);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_change);

        title = findViewById(R.id.idTitle);
        idCost = findViewById(R.id.idCost);
        idDescription = findViewById(R.id.idDescription);
        idAvailabilityInTheStore = findViewById(R.id.idAvailabilityInTheStore);
        idStockAvailability = findViewById(R.id.idStockAvailability);
        idRewiews = findViewById(R.id.idRewiews);
        picture = findViewById(R.id.nullPhoto);



        btnAddChange = findViewById(R.id.btnAddChange);

        btnDeletePhoto = findViewById(R.id.btnDeletePhoto);


        btnGoBack = findViewById(R.id.btnGoBack);

        idBtnDelete = findViewById(R.id.idBtnDelete);
        btnAddChange.setOnClickListener(this);
        idBtnDelete.setOnClickListener(this);
       // picture.setOnClickListener(this);
        /* btnAddChange.setOnClickListener(this);*/

        showAll();
    }

    private void showAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/МамшеваЮС/Экзамен/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<DataModal> call = retrofitApi.getData(ShowActivity.keyId);
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                title.setText(response.body().getTitle());
                idCost.setText(String.valueOf(response.body().getCost()));
                idAvailabilityInTheStore.setText(String.valueOf(response.body().getAvailabilityInTheStore()));
                idStockAvailability.setText(String.valueOf(response.body().getStockAvailability()));
                idDescription.setText(response.body().getDescription());
                nPicture = response.body().getImage();


                if (response.body().getImage() == null) {
                    picture.setImageResource(R.drawable.nullphoto);
                    btnDeletePhoto.setVisibility(View.INVISIBLE);

                } else {
                    Bitmap bitmap = StringToBitMap(response.body().getImage());
                    picture.setImageBitmap(bitmap);
                    btnDeletePhoto.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                Toast.makeText(ChangeActivity.this, "При выводе данных произошла ошибка", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitMap(String encodingImage) {
        try {

            byte[] encodeByte = Base64.decode(encodingImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;

        } catch (Exception e) {
            e.getMessage();
            return null;

        }
    }
    public void buttonDeletePhoto(View v) {
    ImageView picture = (ImageView) findViewById(R.id.nullPhoto);
    picture.setImageBitmap(null);
    nPicture = null;
    TextView deletePicture = findViewById(R.id.btnDeletePhoto);
    picture.setImageResource(R.drawable.nophoto);
    deletePicture.setVisibility(View.INVISIBLE);

    }

    public void nonePicture(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        someActivityResultLauncher.launch(photoPickerIntent);
    }
    private void getChangeRow(String Title, Integer Cost, Integer StockAvailability, Integer AvailabilityInTheStore, String Description, String Rewiews, String Image){
        idBtnDelete.setVisibility(View.INVISIBLE);
        btnDeletePhoto.setVisibility(View.INVISIBLE);
        btnAddChange.setVisibility(View.INVISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ngknn.ru:5001/NGKNN/МамшеваЮС/Экзамен/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        DataModal modal = new DataModal(ShowActivity.keyId, Title, Cost, StockAvailability ,AvailabilityInTheStore, Description, Rewiews,  Image);
        Call<DataModal> call = retrofitApi.updateData(ShowActivity.keyId, modal);
        call.enqueue(new Callback<DataModal>(){

            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ChangeActivity.this, "В процессе изменения данных произошла ошибка", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ChangeActivity.this, "Данные успешно изменены", Toast.LENGTH_SHORT).show();
                idBtnDelete.setVisibility(View.VISIBLE);
                btnAddChange.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                Toast.makeText(ChangeActivity.this, "При изменении записи возникла ошибка: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddChange:{
                String idtitle = title.getText().toString();
                Integer cost = Integer.valueOf(idCost.getText().toString());
                Integer StockAvailability = Integer.valueOf(idStockAvailability.getText().toString());
                Integer AvailabilityInTheStore = Integer.valueOf(idAvailabilityInTheStore.getText().toString());
                String Description = idDescription.getText().toString();
                String Rewiews = idRewiews.getText().toString();

                getChangeRow(idtitle, cost, StockAvailability, AvailabilityInTheStore, Description, Rewiews, nPicture);
                new Handler().postDelayed(() -> startActivity(
                        new Intent(ChangeActivity.this, ShowActivity.class)), 1000);
            }
            break;

            case R.id.idImageView:{

                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImg.launch(intent);
                break;
            }
            case R.id.idBtnDelete:{
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://ngknn.ru:5001/NGKNN/МамшеваЮС/Экзамен/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
                Call call = retrofitApi.deleteData(ShowActivity.keyId);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(ChangeActivity.this, "При удалении прозашла ошибка", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        Toast.makeText(ChangeActivity.this, "Удаление произошло успешно", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ChangeActivity.this, ShowActivity.class));


                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(ChangeActivity.this, "При удалении данных произошла ошибочка", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


    }

}
