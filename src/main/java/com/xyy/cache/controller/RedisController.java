package com.xyy.cache.controller;

import com.xyy.cache.bean.mysqljpa.OfClass;
import com.xyy.cache.service.UserService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Controller
public class RedisController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserService userService;

    //字符串
    @ResponseBody
    @GetMapping("/redisValue")
    public String redisValue(String testValue){

        //redisTemplate.opsForValue().set(key,value);                                  添加
        //redisTemplate.opsForValue().set(key,value,7200, TimeUnit.SECONDS);           添加并设置过期时间
        //redisTemplate.opsForValue().increment(user_count,1);                         自增
        //redisTemplate.opsForValue().getAndSet(key,newValue);                          获取并重新设置新值
        //redisTemplate.opsForValue().append(key,hello);                                在value的末尾拼接上hello
        // redisTemplate.opsForValue().size(key);                                       获取字符串的长度
        if(redisTemplate.hasKey(testValue)){
            System.out.println("存在key，将从redis里获取");
            return redisTemplate.opsForValue().get("testValue").toString();
        }else{
            System.out.println("不存在，查数据库，查出结果放入缓存，在返回");
            redisTemplate.opsForValue().set(testValue,"test1");
            return redisTemplate.opsForValue().get(testValue).toString();
        }

    }
    //哈希
    @ResponseBody
    @GetMapping("/redisHashMap")
    public Map redisHashMap(String testValue){
        //添加单个
        redisTemplate.opsForHash().put("testhash","id","001");
        //添加多个
        HashMap map = new HashMap<>();
        map.put("name","小明");
        map.put("sex","男");
        redisTemplate.opsForHash().putAll("testhash",map);
        //键集合
        Set<String> keys=redisTemplate.opsForHash().keys("testhash");
        System.out.println("keys"+keys);
        //value集合
        List values=redisTemplate.opsForHash().values("testhash");
        System.out.println("values"+values);
        //遍历map,Cursor：游标
        /*Cursor<Map.Entry<Object,Object>> entryCursor=redisTemplate.opsForHash().scan("scan", ScanOptions.NONE);
        while(entryCursor.hasNext()){
            Map.Entry<Object,Object> entry=entryCursor.next();
            System.out.println("键："+entry.getKey()+"值:"+entry.getValue());
        }*/
        //获取整个map
        return redisTemplate.opsForHash().entries("testhash");

    }
    //集合
    @ResponseBody
    @GetMapping("/redisSet")
    public Set<String> setRedis(String set){
        String[] ste=new String[]{"123","456","789","45"};
        redisTemplate.opsForSet().add(set,ste);
        //移除一个或多个
        ste=new String[]{"123"};
        redisTemplate.opsForSet().remove(set,ste);
        //遍历
        Cursor<String> entryCursor=redisTemplate.opsForSet().scan("scan", ScanOptions.NONE);
        while(entryCursor.hasNext()){
            System.out.println("set:"+entryCursor.next());
        }
        return redisTemplate.opsForSet().members(set);
    }

    //list列表
    @ResponseBody
    @GetMapping("/redisList")
    public List<String> redisList(){
       //表头插入一个
        //redisTemplate.opsForList().leftPush("left-list","java");
        //表头插入多个
        //String[] arr=new String[]{"JS","HTML","C#","C++"};
        //redisTemplate.opsForList().leftPushAll("left-list",arr);
        //表尾插入一个
        //redisTemplate.opsForList().rightPush("right-list","java");
        //表尾插入多个
        //redisTemplate.opsForList().rightPushAll("right-list",arr);
        //设置位置
        //redisTemplate.opsForList().set("right-list",0,"第一个");
        //删除：count>0:删除等于从头到尾移动的值的元素。
        //count<0:删除等于从尾到头移动的值的元素。
        //count=0:删除等于value的所有元素
        redisTemplate.opsForList().remove("right-list",1,"JS");
        return redisTemplate.opsForList().range("left-list",1,-1);
    }
    //有序集合
    @ResponseBody
    @GetMapping("/redisZset")
    public List<String> redisZset(){
        //表头插入一个
        //redisTemplate.opsForList().leftPush("left-list","java");
        //表头插入多个
        //String[] arr=new String[]{"JS","HTML","C#","C++"};
        //redisTemplate.opsForList().leftPushAll("left-list",arr);
        //表尾插入一个
        //redisTemplate.opsForList().rightPush("right-list","java");
        //表尾插入多个
        //redisTemplate.opsForList().rightPushAll("right-list",arr);
        //设置位置
        //redisTemplate.opsForList().set("right-list",0,"第一个");
        //删除：count>0:删除等于从头到尾移动的值的元素。
        //count<0:删除等于从尾到头移动的值的元素。
        //count=0:删除等于value的所有元素
        redisTemplate.opsForList().remove("right-list",1,"JS");
        return redisTemplate.opsForList().range("left-list",1,-1);
    }


}
