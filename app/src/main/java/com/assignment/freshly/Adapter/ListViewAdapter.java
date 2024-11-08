package com.assignment.freshly.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.freshly.Activities.ProductDetailActivity;
import com.assignment.freshly.R;
import com.assignment.freshly.entity.Product;

import org.w3c.dom.Text;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Product> {

    private Context context;
    private List<Product> productList;

    public ListViewAdapter(Context context, List<Product> products) {
        super(context, 0, products);
        this.context = context;
        this.productList = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.image_icon);
        TextView title = convertView.findViewById(R.id.title);
        TextView description = convertView.findViewById(R.id.description);
        Button addToCart = convertView.findViewById(R.id.btn_add_to_cart);

        title.setText(productList.get(position).getTitle());
        description.setText(productList.get(position).getDescription());
        imageView.setImageResource(0);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, productList.get(position).getTitle() + " added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("productId", productList.get(position).getP_id());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
