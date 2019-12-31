package cn.idealismus.bean;

import lombok.Data;

import javax.jws.soap.SOAPBinding;

/**
 * Bean对象
 */
@Data
public class User {
    private String userName;
    private Integer age;
}
