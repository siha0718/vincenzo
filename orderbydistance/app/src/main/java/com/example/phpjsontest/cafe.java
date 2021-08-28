package com.example.phpjsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class cafe implements Serializable  {


        public String name;
        public cafe() { }

        public String getName()  {
                return name;
            }
        public void setName(String name) {
        this.name = name;
    }



    
        }





