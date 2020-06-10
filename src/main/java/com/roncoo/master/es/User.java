package com.roncoo.master.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/6/9 18:08
 */
@Document(indexName = "website", type = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -945375752355051786L;
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String userName;
    @Field(type = FieldType.Integer)
    private int age;
    @Field(type = FieldType.Text)
    private String email;
    @Field(type = FieldType.Date)
    private Date birthday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
