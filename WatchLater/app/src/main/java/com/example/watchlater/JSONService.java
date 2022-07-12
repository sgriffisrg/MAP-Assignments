package com.example.watchlater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONService {

    public ArrayList<Shows> getShows(String JSON){
        ArrayList<Shows> showList = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(JSON);
            System.out.println(jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++){
                String genreList;
                Double rates = 0.0;
                String ended = "running";
                int runtime = 0;
                JSONArray genreArray = null;
                JSONObject network;
                JSONObject country;
                JSONObject image;
                JSONObject rating = null;
                Country ctry = null;
                Network net = null;
                Image img = null;
                JSONObject entireObj = jsonArray.getJSONObject(i);
                if(!entireObj.isNull("genres"))
                    genreArray = entireObj.getJSONArray("genres");

                if(!entireObj.isNull("network")){
                    network = entireObj.getJSONObject("network");
                    if(network.getJSONObject("country") != null){
                        country = network.getJSONObject("country");
                        ctry = new Country(country.getString("name"), country.getString("code"), country.getString("timezone"));
                    }
                    else
                        ctry = new Country("","","");

                    net = new Network(network.getString("name"), ctry, network.getString("officialSite"));
                }
                else{
                    ctry = new Country("","","");
                    net = new Network("", ctry, "");
                }

                if(!entireObj.isNull("image")) {
                    image = entireObj.getJSONObject("image");
                    img = new Image(image.getString("medium"), image.getString("original"));
                }
                else
                    img = new Image("", "icon.png");

                if(!entireObj.isNull("rating"))
                    rating = entireObj.getJSONObject("rating");

                String genres;
                if(genreArray != null && genreArray.length() > 0) {
                    genres = genreArray.get(0) + " ";
                    for (int j = 1; j < genreArray.length(); j++) {
                        genres += genreArray.get(j) + " ";
                    }
                }
                else
                    genres = "";

                if(!entireObj.isNull("runtime"))
                    runtime = entireObj.getInt("runtime");

                if(!entireObj.isNull("ended"))
                    ended = entireObj.getString("ended");

                if(rating != null)
                    rates = rating.isNull("average") ? 0 : rating.getDouble("average");

                showList.add(new Shows(entireObj.getInt("id"), entireObj.getString("name"), genres, entireObj.getString("status"), runtime,
                        entireObj.getString("premiered"), ended, net.name, img.original, entireObj.getString("summary"),
                        rates, ctry.name));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return showList;
    }

    public ArrayList<Shows> getSearchedShows(String JSON){
        ArrayList<Shows> showList = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(JSON);
            System.out.println(jsonArray.length());
            for(int i = 0; i < jsonArray.length(); i++){
                String genreList;
                Double rates = 0.0;
                String ended = "running";
                int runtime = 0;
                JSONArray genreArray = null;
                JSONObject network;
                JSONObject country;
                JSONObject image;
                JSONObject rating = null;
                Country ctry = null;
                Network net = null;
                Image img = null;
                JSONObject entireObj = jsonArray.getJSONObject(i).getJSONObject("show");
                if(!entireObj.isNull("genres"))
                    genreArray = entireObj.getJSONArray("genres");

                if(!entireObj.isNull("network")){
                    network = entireObj.getJSONObject("network");
                    if(network.getJSONObject("country") != null){
                        country = network.getJSONObject("country");
                        ctry = new Country(country.getString("name"), country.getString("code"), country.getString("timezone"));
                    }
                    else
                        ctry = new Country("","","");

                    net = new Network(network.getString("name"), ctry, network.getString("officialSite"));
                }
                else{
                    ctry = new Country("","","");
                    net = new Network("", ctry, "");
                }

                if(!entireObj.isNull("image")) {
                    image = entireObj.getJSONObject("image");
                    img = new Image(image.getString("medium"), image.getString("original"));
                }
                else
                    img = new Image("", "icon.png");

                if(!entireObj.isNull("rating"))
                    rating = entireObj.getJSONObject("rating");

                String genres;
                if(genreArray != null && genreArray.length() > 0) {
                    genres = genreArray.get(0) + " ";
                    for (int j = 1; j < genreArray.length(); j++) {
                        genres += genreArray.get(j) + " ";
                    }
                }
                else
                    genres = "";

                if(!entireObj.isNull("runtime"))
                    runtime = entireObj.getInt("runtime");

                if(!entireObj.isNull("ended"))
                    ended = entireObj.getString("ended");

                if(rating != null)
                    rates = rating.isNull("average") ? 0 : rating.getDouble("average");

                showList.add(new Shows(entireObj.getInt("id"), entireObj.getString("name"), genres, entireObj.getString("status"), runtime,
                        entireObj.getString("premiered"), ended, net.name, img.original, entireObj.getString("summary"),
                        rates, ctry.name));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return showList;
    }


}
