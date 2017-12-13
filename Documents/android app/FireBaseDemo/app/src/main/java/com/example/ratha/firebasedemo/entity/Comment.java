package com.example.ratha.firebasedemo.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ratha on 12/12/2017.
 */

public class Comment {
    String id;
    String comment;

    public Comment(){}
    public Comment(String id,String comment){
        this.id=id;this.comment=comment;
    }
    public Map<String,Object> toMap(){
        Map<String ,Object> result=new HashMap<>();
        result.put("id",id);
        result.put("comment",comment);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
