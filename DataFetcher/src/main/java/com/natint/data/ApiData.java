package com.natint.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by skn on 09/10/2015.
 */
public class ApiData implements IData {

    @SerializedName("postId")
    Integer postId;
    @SerializedName("id")
    Integer id;
    @SerializedName("name")
    String name;
    @SerializedName("email")
    String email;
    @SerializedName("body")
    String body;

    public ApiData(Integer postId, Integer id, String email, String name, String body) {
        this.postId = postId;
        this.id = id;
        this.email = email;
        this.name = name;
        this.body = body;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiData apiData = (ApiData) o;

        if (postId != null ? !postId.equals(apiData.postId) : apiData.postId != null) return false;
        if (id != null ? !id.equals(apiData.id) : apiData.id != null) return false;
        if (name != null ? !name.equals(apiData.name) : apiData.name != null) return false;
        if (email != null ? !email.equals(apiData.email) : apiData.email != null) return false;
        return !(body != null ? !body.equals(apiData.body) : apiData.body != null);

    }

    @Override
    public int hashCode() {
        int result = postId != null ? postId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApiData{" +
                "postId=" + postId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
