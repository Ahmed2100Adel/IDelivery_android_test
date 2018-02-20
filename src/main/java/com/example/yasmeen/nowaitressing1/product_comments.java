package com.example.yasmeen.nowaitressing1;

import java.lang.ref.PhantomReference;
import java.net.URI;
import java.util.Queue;

/**
 * Created by NgocTri on 11/15/2015.
 */
public class product_comments {
    private String id;
    private String textOfComment;

    //Constructor

    public product_comments(String id,String textOfComment) {
        this.id = id;
        this.textOfComment = textOfComment;

    }

    //Setter, getter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTextOfComment() {
        return textOfComment;
    }

    public void setTextOfComment(String textOfComment) {
        this.textOfComment = textOfComment;
    }

}
