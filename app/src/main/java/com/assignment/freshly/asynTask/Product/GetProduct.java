package com.assignment.freshly.asynTask.Product;

import android.content.Context;
import android.os.AsyncTask;

import com.assignment.freshly.DatabaseConfig.DatabaseClient;
import com.assignment.freshly.dao.ProductDao;
import com.assignment.freshly.entity.Product;

public class GetProduct extends AsyncTask<Integer, Void, Product> {
    private Context context;
    private GetProductListener getProductListener;

    public GetProduct(Context context, GetProductListener getProductListener){
        this.context = context;
        this.getProductListener = getProductListener;
    }

    @Override
    protected Product doInBackground(Integer... integers) {
        ProductDao productDao = DatabaseClient.getInstance(context).freshlyDatabase.productDao();
        return productDao.getProductById(integers[0]);
    }

    @Override
    protected void onPostExecute(Product product){
        if(product!= null){
            getProductListener.onGetProductSuccess(product);
        }
        else{
            getProductListener.onGetProductFailure();
        }

    }

    public interface GetProductListener{
        void onGetProductSuccess(Product product);
        void onGetProductFailure();
    }
}
