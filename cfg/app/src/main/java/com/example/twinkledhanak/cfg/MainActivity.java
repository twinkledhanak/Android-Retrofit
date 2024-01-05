package com.example.twinkledhanak.cfg;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class MainActivity extends AppCompatActivity {
    String name;
    int age;
    EditText n , a;
    public static final String ROOT_URL = "http://www.codeforgood.16mb.com/";
    //List of type books this list will store type Book which is our data model
    private List<Person> person;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v) {
        n = (EditText) findViewById(R.id.name);
        a = (EditText) findViewById(R.id.age);
        name = n.getText().toString();
        age = Integer.parseInt(a.getText().toString());


        insertUser();
        getUser();
       }

    private void getUser() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        GetBackAPI api = adapter.create(GetBackAPI.class);

        //Defining the method
        api.getUser(new Callback<List<Person>>() {

            @Override
            public void success(List<Person> persons, retrofit.client.Response response) {
//Storing the data in our list
                person = persons;

                //Calling a method to show the list
                showList();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    //Our method to show list
    private void showList(){

        String detail = new String();

        //String array to store all the book names
        String[] items = new String[person.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<person.size(); i++){
            //Storing names to string array
            items[i] = person.get(i).getName();
            detail = detail + items[i];
        }
        textView = (TextView)findViewById(R.id.text);
        textView.setText(detail);

    }


    private void insertUser(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);

        //Defining the method insertuser of our interface
        api.insertUser(

                //Passing the values by getting it from editTexts
                n.getText().toString(),
                Integer.parseInt(a.getText().toString()),

                new Callback<retrofit.client.Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2)
                    {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;
                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                }

        );
    }

}

  /*  class postdata implements Runnable {

        Person p;

        public postdata(Person p) {
            this.p = p;

        }

        @Override
        public void run() {
            try {

                // URL url = new URL("http://server36.hostinger.in/codeforgood/insert.php");
                // URL url = new URL("http://www.google.com");
                //URL url = new URL("http://www.codeforgood.16mb.com/insert.php");
                URL url = new URL("http://www.codeforgood.16mb.com/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                //connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

                connection.setDoOutput(true);
                connection.setDoInput(true);
                Gson gson=new Gson();
                final String jsonstring =gson.toJson(p);

                PrintWriter out=new PrintWriter(connection.getOutputStream());
                out.println(jsonstring);
                out.flush();
                Log.d("json", jsonstring);


                final int statuscode = connection.getResponseCode();
                if (statuscode == 200) {
                    InputStream responseBody = connection.getInputStream();
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(responseBody, "UTF-8");
                    JsonReader jsonReader = new JsonReader(responseBodyReader);

                    jsonReader.beginObject(); // Start processing the JSON object
                    while (jsonReader.hasNext()) { // Loop through all keys
                        String key = jsonReader.nextName(); // Fetch the next key
                        if (key.equals("name") || key.equals("age")) { // Check if desired key
                            // Fetch the value as a String
                            String value = jsonReader.nextString();

                            // Do something with the value
                            // ...

                            break; // Break out of the loop
                        } else {
                            jsonReader.skipValue(); // Skip values of other keys
                        }
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "successfully on server", Toast.LENGTH_LONG).show();

                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "failure error" + statuscode, Toast.LENGTH_LONG).show();

                        }
                    });
                }


            } catch (MalformedURLException e) {
                Toast.makeText(MainActivity.this, " " + e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, " " + e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
    }
}

*/
