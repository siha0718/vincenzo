package com.example.vincenzo;

import java.io.Serializable;

public class cafe implements Serializable  {


        public String name, address, size, latitude, longitude;
        public int resId;
        public cafe() { }

        public String getName()  {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getAddress()  {
                return address;
            }
        public void setAddress(String address) {
        this.address = address;
    }

        public String getSize()  {
            return size;
        }
        public void setSize(String size) {
            this.size = size;
        }

        public String getLatitude()  {
        return latitude;
    }
        public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

        public String getLongitude() { return longitude;  }
        public void setLongitude(String longitude) {
        this.name = longitude;
    }

        public int getResId() { return resId; }
        public void setResId(int resId) {  this.resId = resId;  }


        }





