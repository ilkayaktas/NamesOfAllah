package com.mobss.islamic.namesofAllah.controller.db.initializer.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mobss.islamic.namesofAllah.model.json.JsonIsim;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ilkay on 22/12/2016.
 */

public class JsonIsimlerParser implements IFileParser {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public JsonIsim[] parseFile(File file) {
        try {

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // read json file
            JsonNode rootNode = mapper.readTree(file);

            // get total number of questions
            int sizeOfQuestions = rootNode.path("Response").path("Count").asInt();

            // create array for return value
            JsonIsim[] jsonKategoriList = new JsonIsim[sizeOfQuestions];

            // read all questions
            for (int i = 0; i < sizeOfQuestions; i++) {
                String kategoriText = rootNode.path("Response").path("Result").path("result").get(i).toString();

                JsonIsim jsjsonKategorin
                        = mapper.readValue(kategoriText, JsonIsim.class);

                jsonKategoriList[i] = jsjsonKategorin;

            }

            return jsonKategoriList;

        } catch (IOException e) {
            e.printStackTrace();            return null;
        }
    }

    @Override
    public JsonIsim[] parseInputStream(InputStream inputStream) {
        try {

            // read json
            String json = readFromInputStream(inputStream);

            // create json reader
            JSONArray jsonArray = new JSONArray(json);

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // create array for return value
            JsonIsim[] jsonIsimler = new JsonIsim[jsonArray.length()];

            // read all questions
            for (int i = 0; i < jsonArray.length(); i++) {
                String kategoriText = jsonArray.get(i).toString();

                JsonIsim jsonIsim = mapper.readValue(kategoriText, JsonIsim.class);

                jsonIsimler[i] = jsonIsim;

            }

            return jsonIsimler;

        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String readFromInputStream(InputStream inputStream) throws IOException {
        // get size of json stream
        int size = inputStream.available();

        // create buffer
        byte[] buffer = new byte[size];

        // read stream
        inputStream.read(buffer);

        // close stream
        inputStream.close();

        return new String(buffer, "UTF-8");
    }
}
