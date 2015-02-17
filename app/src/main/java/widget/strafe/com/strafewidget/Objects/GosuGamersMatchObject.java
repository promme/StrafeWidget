package widget.strafe.com.strafewidget.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GosuGamersMatchObject implements Serializable{
    private String name;
    private String url;
    private String time;

    private String Team1_Name;
    private String Team1_sName;
    private String Team1_Country;
    private String Team1_url;

    private String Team2_sName;
    private String Team2_Country;
    private String Team2_url;
    private String Team2_Name;

    private String map;


    public GosuGamersMatchObject(JSONObject jsonObj) throws JSONException {
        this.name = jsonObj.optString("name", "no league available");
        this.url = jsonObj.optString("url", "no URL available");
        this.time = jsonObj.optString("time", "no time availabe");
        this.map = jsonObj.optString("map", "no map available");

        this.Team1_Country = jsonObj.getJSONArray("teams").getJSONObject(0).optString("country", "no country availalbe");
        this.Team1_Name = jsonObj.getJSONArray("teams").getJSONObject(0).optString("name", "no name availalbe");
        this.Team1_sName = jsonObj.getJSONArray("teams").getJSONObject(0).optString("sname", "no sname availalbe");
        this.Team1_url = jsonObj.getJSONArray("teams").getJSONObject(0).optString("url", "no url availalbe");


        this.Team2_Country = jsonObj.getJSONArray("teams").getJSONObject(1).optString("country", "no country availalbe");
        this.Team2_Name = jsonObj.getJSONArray("teams").getJSONObject(1).optString("name", "no name availalbe");
        this.Team2_sName = jsonObj.getJSONArray("teams").getJSONObject(1).optString("sname", "no sname availalbe");
        this.Team2_url = jsonObj.getJSONArray("teams").getJSONObject(1).optString("url", "no url availalbe");
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeam1_Name() {
        return Team1_Name;
    }

    public void setTeam1_Name(String team1_Name) {
        Team1_Name = team1_Name;
    }

    public String getTeam1_sName() {
        return Team1_sName;
    }

    public void setTeam1_sName(String team1_sName) {
        Team1_sName = team1_sName;
    }

    public String getTeam1_Country() {
        return Team1_Country;
    }

    public void setTeam1_Country(String team1_Country) {
        Team1_Country = team1_Country;
    }

    public String getTeam1_url() {
        return Team1_url;
    }

    public void setTeam1_url(String team1_url) {
        Team1_url = team1_url;
    }

    public String getTeam2_Name() {
        return Team2_Name;
    }

    public void setTeam2_Name(String team2_Name) {
        Team2_Name = team2_Name;
    }

    public String getTeam2_sName() {
        return Team2_sName;
    }

    public void setTeam2_sName(String team2_sName) {
        Team2_sName = team2_sName;
    }

    public String getTeam2_Country() {
        return Team2_Country;
    }

    public void setTeam2_Country(String team2_Country) {
        Team2_Country = team2_Country;
    }

    public String getTeam2_url() {
        return Team2_url;
    }

    public void setTeam2_url(String team2_url) {
        Team2_url = team2_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }








}
