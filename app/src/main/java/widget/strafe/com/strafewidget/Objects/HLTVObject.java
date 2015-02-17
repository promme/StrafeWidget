package widget.strafe.com.strafewidget.Objects;

import org.json.JSONObject;

/**
 * Created by olofberg on 15-02-17.
 */
public class HLTVObject {
    private String title;
    private String link;
    private String author;
    private String publishedDate;
    private String contentSnippet;
    private String content;
    private String categories;

    public HLTVObject(){

    }

    public HLTVObject(JSONObject jsonObj){

        this.title = jsonObj.optString("title", "No title available");
        this.author = jsonObj.optString("author", "No author available");
        this.categories = jsonObj.optString("categories", "No categories available");
        this.contentSnippet = jsonObj.optString("contentSnippet", "No contentSnippet available");
        this.content = jsonObj.optString("content", "No content available");
        this.link = jsonObj.optString("link", "No link available");
        this.publishedDate = jsonObj.optString("publishedDate", "No publishDate available");

    }



    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSnippet() {

        return contentSnippet;
    }

    public void setContentSnippet(String contentSnippet) {
        this.contentSnippet = contentSnippet;
    }

    public String getPublishedDate() {

        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

