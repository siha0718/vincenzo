package com.example.vincenzo;

import java.io.Serializable;

public class cafe implements Serializable  {


        public String name, address;
        public Double size, latitude, longitude, rush_ratio;
        public int rush_level;
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

        public Double getSize()  {
            return size;
        }
        public void setSize(Double size) {
            this.size = size;
        }

        public Double getLatitude()  {
            return latitude;
        }
        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() { return longitude;  }
        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public int getRushLevel() { return rush_level; }
        public void setRushLevel(int rush_level) {  this.rush_level = rush_level;  }

        public Double getRushRatio()  {
            return rush_ratio;
        }
        public void setRushRatio(Double rush_ratio) {
            this.rush_ratio = rush_ratio;
        }

        
        }





