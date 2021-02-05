package com.wxuy.example;

import com.wxuy.example.entity.User;
import com.wxuy.example.entity.UserList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("local")
//@TestPropertySource("classpath:application-dev.properties")
class ExampleApplicationTests {
    @Autowired
    UserList userList;
    @Test
    void test1(){
        //list集合元素进行排序的几种方式
        List<User> userlist = userList.getUserlist();
        Collections.sort(userlist);

        Collections.sort(userlist,new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return -o1.getAge()+o2.getAge();
            }
        });
        //ctrl+shift+空格键 可以快捷生成 lambda表达式
        Collections.sort(userlist,(o1, o2) ->{
            return -o1.getAge()+o2.getAge();
        });
        //进一步简化
        Collections.sort(userlist,(o1, o2) ->  -o1.getAge()+o2.getAge());
        //简化 User 类实现了 Comparable<User> 接口 所以可以使用  list.sort
        userlist.sort((o1, o2) -> -o1.getAge()+o2.getAge());
        //简化 again
        //[方法引用]的格式是  类名::方法名
        // Function<T,R> T表示传入类型，R表示返回类型。比如表达式User.getAge(); 传入参数是User，返回值是User.getAge()，
        // 那么方法引用User::getAge就对应着Function<User,Integer>类型。
        userlist.sort(Comparator.comparing(User::getAge));

        //使用 stream流进行排序  要求类实现了 Compare 接口
        userlist = userlist.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
        //倒序  reversed
        userlist = userlist.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
        //按照多个字段排序
        userlist = userlist.stream().sorted(Comparator.comparing(User::getAge)
                .reversed()
                .thenComparing(User::getDescription).reversed()
        ).collect(Collectors.toList());

        for (User user : userlist) {
            System.out.println(user);
        }
    }
    @Test
    void contextLoads() {
    }

}
