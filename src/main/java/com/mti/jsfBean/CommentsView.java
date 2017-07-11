package com.mti.jsfBean;

import com.mti.entities.Comment;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sabrine.Elbahri on 11/07/2017.
 */

@ManagedBean
@ViewScoped
public class CommentsView {
    private ArrayList<Comment> comments;

    @PostConstruct
    public void test() {
        try {
            Client client = ClientBuilder.newClient();

            WebTarget resource = client.target("http://localhost:8080/jee-project/api/comments");

            Invocation.Builder request = resource.request();
            request.accept(MediaType.APPLICATION_JSON);

            Response response = request.get();

            ArrayList<Comment> jsonResponse = response.readEntity(ArrayList.class);
            System.out.println(jsonResponse);

            if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
                comments = jsonResponse;
            } else {
                System.out.println("ERROR! " + response.getStatus());
                System.out.println(response);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<Comment> getComments() {
        return comments;
    }
    

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
