package com.mobss.namesofAllah.model.json;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by ilkay on 23/12/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonIsim implements Serializable {
    public int sira;
    public String isim;
    public String aciklama;
    public String resim;

}
