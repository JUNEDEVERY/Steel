package com.example.books;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterMask extends BaseAdapter {
    Context mContext;
    List<Mask> listview;

    public AdapterMask(Context mContext, List<Mask> listview) {
        this.mContext = mContext;
        this.listview = listview;
    }

    @Override
    public int getCount() {
        return listview.size();
    }

    @Override
    public Object getItem(int i) {
        return listview.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listview.get(i).getId();
    }


    private Bitmap getUserImager(String encodeIMG) {
        if (encodeIMG != null && !encodeIMG.equals("null")) {
            byte[] bytes = Base64.decode(encodeIMG, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else{
            return null;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(mContext, R.layout.item_layout, null);
        TextView Title = v.findViewById(R.id.idTitle);
        TextView Cost = v.findViewById(R.id.idCost2);
        TextView StockAvailability = v.findViewById(R.id.idStockAvailability);
        TextView AvailabilityInTheStore = v.findViewById(R.id.idAvailabilityInTheStore);

        ImageView Image = v.findViewById(R.id.idImageView);

        Mask mask = listview.get(i);

        Title.setText(mask.getTitle());

        Cost.setText(Integer.toString(mask.getCost()));



        if (mask.getStockAvailability() < 0 ){
            StockAvailability.setText("0");
        }
        else{
            StockAvailability.setText(Integer.toString(mask.getStockAvailability()));
        }

        if (mask.getAvailabilityInTheStore() < 0 ){
            AvailabilityInTheStore.setText("0");
        }
        else{
            AvailabilityInTheStore.setText(Integer.toString(mask.getAvailabilityInTheStore()));
        }


        if (mask.getImage().isEmpty()) {
            Image.setImageResource(R.drawable.nophoto);
        } else {
            Image.setImageBitmap(getUserImager(mask.getImage()));
        }
        if(mask.getStockAvailability() > 5){
            StockAvailability.setText("Много");
        }
        else{
            StockAvailability.setText(Integer.toString(mask.getStockAvailability()));
        }
        return v;
    }
}
