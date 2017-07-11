package com.mti.jsfBean;

import com.mti.entities.Blog;
import com.mti.entities.User;
import com.mti.services.BlogWebServices;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sabrine.Elbahri on 11/07/2017.
 */

@ManagedBean
@ViewScoped
public class BlogsView {

    private ArrayList<Blog> blogs;

    @PostConstruct
    public void test() {
        try {
            Client client = ClientBuilder.newClient();

            WebTarget resource = client.target("http://localhost:8080/jee-project/api/blogs");

            Invocation.Builder request = resource.request();
            request.accept(MediaType.APPLICATION_JSON);

            Response response = request.get();

            ArrayList<Blog> jsonResponse = response.readEntity(ArrayList.class);
            System.out.println(jsonResponse);

            if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
                blogs = jsonResponse;
            } else {
                System.out.println("ERROR! " + response.getStatus());
                System.out.println(response);
            }

            //System.out.print(result.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(ArrayList<Blog> blogs) {
        this.blogs = blogs;
    }


}
