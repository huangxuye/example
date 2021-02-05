package com.wxuy.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable,Cloneable,Comparable<User> {
/*    @NotEmpty 被注释的字符串的不能为 null 也不能为空
    @NotBlank 被注释的字符串非 null，并且必须包含一个非空白字符
    @Null 被注释的元素必须为 null
    @NotNull 被注释的元素必须不为 null
    @AssertTrue 被注释的元素必须为 true
    @AssertFalse 被注释的元素必须为 false
    @Pattern(regex=,flag=)被注释的元素必须符合指定的正则表达式
    @Email 被注释的元素必须是 Email 格式。
    @Min(value)被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    @Max(value)被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    @DecimalMin(value)被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    @Size(max=, min=)被注释的元素的大小必须在指定的范围内
    @Digits (integer, fraction)被注释的元素必须是一个数字，其值必须在可接受的范围内
    @Past被注释的元素必须是一个过去的日期
    @Future 被注释的元素必须是一个将来的日期*/
    private int id;
    private String user_name;
    private String description;
    private String password;
    private int age;
    private String sex;
    private int grade;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(User o) {
        return this.getDescription().compareTo(o.getDescription());
//        return o.getAge()-this.getAge();
    }
}
