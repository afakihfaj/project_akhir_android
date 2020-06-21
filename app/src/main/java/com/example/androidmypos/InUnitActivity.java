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

public class InUnitActivity extends AppCompatActivity {

    private EditText unit;
    private Button btn_unit;
    private ProgressBar loading;

    private static String URL_UNIT="http://192.168.1.10/php_apobase/in_unit.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_unit);

        loading= findViewById(R.id.loading);
        unit= findViewById(R.id.unit);
        btn_unit=findViewById(R.id.btn_unit);

        btn_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Unit();
            }
        });

    }
    private void Unit(){
        loading.setVisibility(View.VISIBLE);

        final String unit = this.unit.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UNIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(InUnitActivity.this, "Input Berhasil", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(InUnitActivity.this, "Input Gagal" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InUnitActivity.this, "Input Gagal" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", unit);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
