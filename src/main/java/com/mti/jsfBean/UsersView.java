package com.mti.jsfBean;

import javax.faces.bean.ManagedBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Zibe on 11/07/2017.
 */
@ManagedBean
public class UsersView {

    public void addUser(String username, String mail, String password)  {

        try {
            String encodedMail = URLEncoder.encode(mail, "UTF-8");
            String encodedUsername = URLEncoder.encode(username, "UTF-8");
            String encodedPwd = URLEncoder.encode(password, "UTF-8");
            // This is the data that is going to be send to itcuties.com via POST request
            // 'e' parameter contains data to echo
            String postData = "email=" + encodedMail + "&password=" + encodedPwd + "&username=" + encodedUsername;

            // Connect to google.com
            URL url = new URL("http://localhost:8080/jee-project/api/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(postData.length()));

            // Write data
            OutputStream os = connection.getOutputStream();
            os.write(postData.getBytes());

            // Read response
            StringBuilder responseSB = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ( (line = br.readLine()) != null)
                responseSB.append(line);

            // Close streams
            br.close();
            os.close();

            responseSB.toString();
        }
        catch (Exception e)
        {
        }
    }
}
