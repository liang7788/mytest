package edu.soft1.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String username;
    String pwd;
    int age;//年龄
    @DateTimeFormat(pattern = "yyyy-MM-dd")//类型转换
    Date birthday;//日期类型
    //自定义对象类型
    Address address;//自定义类型
}
