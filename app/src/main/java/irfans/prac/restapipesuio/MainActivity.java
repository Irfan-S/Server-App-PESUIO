package irfans.prac.restapipesuio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    String URL_str = "https://pesuiorestful.herokuapp.com/";
    URL url;
    HttpsURLConnection myConn;
    InputStream inputStream;
    TextView status;
    TextView increments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = findViewById(R.id.status_view);
        increments = findViewById(R.id.count_view);
        try {
            url = new URL(URL_str);
            myConn =
                    (HttpsURLConnection) url.openConnection();
            if(myConn.getResponseCode()==200){
                // do nothing
            }else{
                Toast.makeText(this,"Connection failed",Toast.LENGTH_LONG).show();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void incrCounter( View v) throws IOException {
        URL_str = URL_str+"/increment";
        url = new URL(URL_str);

        String name = "my"+"name";
        myConn =
                (HttpsURLConnection) url.openConnection();
        inputStream = myConn.getInputStream();
        InputStreamReader is = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(is);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        status.setText(line);

    }

    public void viewCounter(View v) throws IOException {
        URL_str = URL_str+"/view";
        url = new URL(URL_str);
        myConn =
                (HttpsURLConnection) url.openConnection();
        inputStream = myConn.getInputStream();
        InputStreamReader is = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(is);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        increments.setText(line);
    }
}
