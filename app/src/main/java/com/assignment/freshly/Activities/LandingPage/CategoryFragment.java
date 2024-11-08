package com.assignment.freshly.Activities.LandingPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.assignment.freshly.Adapter.ListViewAdapter;
import com.assignment.freshly.R;
import com.assignment.freshly.asynTask.Product.GetProductByCateogoryAndVendor;
import com.assignment.freshly.entity.Product;

import java.util.List;

public class CategoryFragment extends Fragment {

    ListView productList;
    TextView noProducts;
    TextView title;
    private String categoryName;

    public static CategoryFragment newInstance(String categoryName) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("category_name", categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryName = getArguments().getString("category_name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View productView = inflater.inflate(R.layout.fragment_vegetables, container, false);
        productList = productView.findViewById(R.id.product_list_view);
        title = productView.findViewById(R.id.product_category);
        title.setText(categoryName.toUpperCase());
        noProducts = productView.findViewById(R.id.No_products_found);

        loadProductList();

        return productView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProductList();
    }

    private void loadProductList() {
        new GetProductByCateogoryAndVendor(getContext(), new GetProductByCateogoryAndVendor.returnCategoryProduct() {
            @Override
            public void onSuccess(List<Product> products) {
                if (products != null && !products.isEmpty()) {
                    ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), products);
                    productList.setAdapter(listViewAdapter);
                    noProducts.setVisibility(View.GONE);
                } else {
                    noProducts.setText("No " + categoryName + "s found !!");
                    noProducts.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure() {
                noProducts.setText("Failed to load " + categoryName + "s.");
            }
        }).execute(1, categoryName);
    }
}
