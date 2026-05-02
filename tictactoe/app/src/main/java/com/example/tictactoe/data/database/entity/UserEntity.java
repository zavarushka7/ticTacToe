package com.example.tictactoe.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "user_uuid")
    private String userUuid;

    @ColumnInfo(name = "login")
    private String login;

    @ColumnInfo(name = "password")
    private String password;


    public UserEntity() {}

    @Ignore
    public UserEntity(String userUuid, String login, String password) {
        this.userUuid = userUuid;
        this.login = login;
        this.password = password;
    }

    public Long getId() { return id; }
    public String getUserUuid() { return userUuid; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }


    public void setId(Long id) { this.id = id; }
    public void setUserUuid(String userUuid) { this.userUuid = userUuid; }
    public void setLogin(String login) { this.login = login; }
    public void setPassword(String password) { this.password = password; }
}