package com.kong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    private int id;
    private String username;
    private String password;
    private int sex;
    private String phone_num;
    private String email;
    private String introduction;

}
