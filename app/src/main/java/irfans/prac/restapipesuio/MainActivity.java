package irfans.prac.restapipesuio;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    String URL_str = "https://pesuiorestful.herokuapp.com/";
    HttpsURLConnection myConn;
    InputStream inputStream;
    TextView status;
    TextView increments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        increments = findViewById(R.id.count_view);

    }

    public void incrCounter( View v) throws IOException {
        Downloader task = new Downloader();
        task.execute(URL_str+"/increment");

    }

    public void viewCounter(View v) throws IOException {
        Downloader task = new Downloader();
        task.execute(URL_str+"/view");
    }
    public class Downloader extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            //Log.d(TAG, "Entered doInBackground for Dowlaoder");
            try
            {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                //Log.d(TAG, "Fetching Downloader URL:" + url);

                while(data!=-1)
                {
                    char current = (char)data;
                    result = result + current;
                    data = reader.read();
                }
                Log.d("irf", "result is " + result);
                return result;
            }
            catch(Exception e)
            {
                //dialogHandler.hideDialog();

                e.printStackTrace();
                Log.d("irf", "Error met"+e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            increments.setText(s);
            //dialogHandler.hideDialog();
            Log.d("irf", "Execution successful");
        }
    }
}
