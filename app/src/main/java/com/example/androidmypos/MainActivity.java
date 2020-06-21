package com.example.androidmypos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText  supplier, phone, address, description;
    private Button btn_supplier;
    private ProgressBar loading;

    private static String URL_SUPPLIER="http://192.168.1.10/php_apobase/in_supplier.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_supplier);

        loading= findViewById(R.id.loading);
        supplier= findViewById(R.id.supplier);
        phone= findViewById(R.id.phone);
        address= findViewById(R.id.address);
        description= findViewById(R.id.description);
        btn_supplier=findViewById(R.id.btn_supplier);

        btn_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Supplier();
            }
        });

    }
    private void Supplier(){
        loading.setVisibility(View.VISIBLE);

        final String supplier = this.supplier.getText().toString().trim();
        final String phone = this.phone.getText().toString().trim();
        final String address = this.address.getText().toString().trim();
        final String description = this.description.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SUPPLIER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(MainActivity.this, "Input Berhasil", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Input Gagal" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Input Gagal" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", supplier);
                params.put("phone", phone);
                params.put("address", address);
                params.put("description", description);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
