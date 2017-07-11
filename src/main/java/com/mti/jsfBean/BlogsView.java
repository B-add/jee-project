package com.mti.jsfBean;

import com.mti.entities.Blog;
import com.mti.services.BlogWebServices;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Sabrine.Elbahri on 11/07/2017.
 */

@ManagedBean
@ViewScoped
public class BlogsView {
    private List<Blog> blogs;

   public void test() {

   }
}
