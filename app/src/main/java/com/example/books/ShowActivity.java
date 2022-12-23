package com.example.books;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private AdapterMask adapterMask;

    ListView listView;
    EditText editText;
    Spinner spinner;
    Button button;
    Button button1;
    public static int keyId;
    public static int idGo = 0;

    private List<Mask> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        editText = findViewById(R.id.editTextTextPersonName);
        spinner = findViewById(R.id.spinner);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                spinner.setSelection(0);
            }
        });
        button1 = findViewById(R.id.idButtonAdd);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this, AddActivity.class));
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new GetProducts().execute();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                new GetProducts().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ListView listObjects = findViewById(R.id.idListView);
        adapterMask = new AdapterMask(ShowActivity.this, list);
        listObjects.setAdapter(adapterMask);
        listView = findViewById(R.id.idListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                keyId = (int) l;
                startActivity(new Intent(ShowActivity.this, ChangeActivity.class)); // можно метод го
            }

        });
        new GetProducts().execute();
    }

    private class GetProducts extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://ngknn.ru:5001/NGKNN/МамшеваЮС/Экзамен/api/Books/sortByBooks?typeOfSort=" + spinner.getSelectedItemPosition() +  "&nameProduct=" + editText.getText().toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } catch (Exception e) {
                Log.e("error", e.getMessage());
                return null;
            }


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                list.clear();
                JSONArray tempArray = new JSONArray(s);
                adapterMask.notifyDataSetChanged();
                for (int i = 0; i < tempArray.length(); i++) {
                    JSONObject jsonObject = tempArray.getJSONObject(i);
                    Mask tempProducts = new Mask(
                        jsonObject.getInt("ID"),
                            jsonObject.getString("Title"),
                            jsonObject.getInt("Cost"),
                            jsonObject.getInt("StockAvailability"),
                            jsonObject.getInt("AvailabilityInTheStore"),
                            jsonObject.getString("Description"),
                            jsonObject.getString("Rewiews"),
                            jsonObject.getString("Image")

                    );

                    list.add(tempProducts);
                    adapterMask.notifyDataSetInvalidated();

                }


            } catch (Exception ignore) {

            }
        }
    }

}