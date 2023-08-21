package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {

    TextView t1;
    Button fetch,submitButton;
    String url;

    WebSocket webSocket;
EditText e1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView) findViewById(R.id.datatext);
        e1=(EditText)findViewById(R.id.e1);
        submitButton=(Button)findViewById(R.id.buttonsubmit);
        fetch=(Button)findViewById(R.id.fetchButton);
        Log.d("umang","In main");


        instantiatewebsocket();

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://opentoolforge.com/public/api/signal/getvalue/HMI.Brightness";
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String data = null;
                        Log.d("umang","on response");

                        try {
                            JSONArray family = response.getJSONArray("signals");
                            JSONObject sonObject = family.getJSONObject(0);
                            data = sonObject.getString("value");


                            Log.d("umang","value  data =>"+data);
                        } catch (JSONException e) {
                            Log.d("umang","on json exception"+e.toString());
                        }
                        Log.d("umang","value =>"+data);

                        t1.setText(data);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("umang","on error"+error);
                    }
                });
                Volley.newRequestQueue(MainActivity.this).add(request);
            }
        });



submitButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        webSocket.send("sending my message");

        String valuedata=e1.getText().toString();

        Log.d("umang","Enter value is =>"+valuedata);


        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("value", valuedata);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        url = "https://opentoolforge.com/public/api/signal/setvalue/HMI.Brightness";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("umang","on error"+error);
            }
        });
        Volley.newRequestQueue(MainActivity.this).add(request);











    }
});
    }

    private void instantiatewebsocket() {
        OkHttpClient client=new OkHttpClient();
        webscoketclassLisner webscoketclassLisner=new webscoketclassLisner(this);
        okhttp3.Request request=new okhttp3.Request.Builder().url("ws://internshipboat.com:8080").build();

         webSocket=client.newWebSocket(request,webscoketclassLisner);
        Toast.makeText(this,"tring",Toast.LENGTH_SHORT).show();

    }

    class webscoketclassLisner extends WebSocketListener{

        public MainActivity activity;
        public webscoketclassLisner(MainActivity activity){
               this.activity=activity;
        }
        @Override
        public void onOpen(WebSocket webSocket, okhttp3.Response response) {
            super.onOpen(webSocket, response);

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,"connection stablise",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);


            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,"onMessage received",Toast.LENGTH_SHORT).show();
                }
            });
            JSONObject jsonObject=new JSONObject();

            try {
                jsonObject.put("message",text);
                jsonObject.put("byserver",true);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        }


        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, okhttp3.Response response) {
            super.onFailure(webSocket, t, response);
        }
    }


}