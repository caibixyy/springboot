package com.xyy.cache.controller;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.xyy.cache.bean.mongojpa.Emps;
import com.xyy.cache.bean.mongojpa.MongoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Condition;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class MongoController {
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 向mongoDB里插入数据
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add1(Model model){
        // Failed to convert value of type 'java.lang.String' to required type 'org.bson.types.ObjectId
        MongoEntity d =new MongoEntity();
        d.setBy("徐国亮");
        d.setDescription("zuolianxi");
        d.setLikes(500);
        d.setTags("hellowolrd");
        d.setTitle("shanchushuju");
        d.setUrl("www.baidu.com");
        model.addAttribute("mongoEntity",d);
        mongoTemplate.save(d);
        return "success";
    }

    /**
     * 查询mongoDB是否有MongoEntity的数据
     * @param model
     * @return
     */
    @GetMapping("/list")
    @Cacheable(cacheNames = "mongo")
    public List add(Model model){
        List<MongoEntity> departmentList = mongoTemplate.findAll(MongoEntity.class);
        for (MongoEntity department :departmentList) {
            System.out.println(department);
        }
        model.addAttribute("departmentList",departmentList);
        return departmentList ;
    }

    @ResponseBody
    @GetMapping("/getUser")
    public List findAll(){
        List<Emps> departmentList = mongoTemplate.findAll(Emps.class);
        if(CollectionUtils.isEmpty(departmentList)){
            List list=new ArrayList();
            list.add("没有查询到数据");
            return list;
        }else {
            return departmentList ;
        }
    }

    //一. 常用查询1. 查询一条数据:(多用于保存时判断db中是否已有当前数据,这里 is  精确匹配,模糊匹配 使用 regex...)
    @ResponseBody
    @GetMapping("/findOne")
    public List findOne() {
        System.out.println("进入查询一条数据方法");
        return mongoTemplate.find(new Query(Criteria.where("ename").is("吴用")),Emps.class);
    }

    //查询多条数据: 属于分级查询,limit不指定就查所有，skip表示跳过的行数，不指定就不跳过
    //mongoTemplate.find(new Query().limit(rows).skip((page-1)*rows),Emps.class)
    //pageindex显示的当前页，pageSize，显示的记录数
    @ResponseBody
    @GetMapping("/getPagedUser")
    public PageImpl<Emps> getPagedUser(int page, int rows) {
        System.out.println("查询多条数据: 属于分级查询");
        Query query=new Query();
        //每页五个
        Pageable pageable=new PageRequest(page,rows);
        query.with(pageable);
        //按sal排序
        query.with(new Sort(Sort.Direction.DESC,"sal"));
        //查询总数
        Long count=mongoTemplate.count(query,Emps.class,"emp");
        List<Emps> emps=mongoTemplate.find(query,Emps.class);
        return (PageImpl<Emps>)PageableExecutionUtils.getPage(emps,pageable,()->count);
    }

    //模糊查询
    @ResponseBody
    @GetMapping("/like")
    public List like() {
        Query query = new Query(new Criteria().where("job").regex(Pattern.compile("^.*.职.*$",Pattern.CASE_INSENSITIVE)));
        return mongoTemplate.find(query,Emps.class);
    }

    //多条件查询
    @ResponseBody
    @GetMapping("/manyCase")
    public List manyCase() {
        Query query = new Query();
        query.addCriteria(Criteria.where("depno").is(20));
        query.addCriteria(Criteria.where("sal").gte(1200));
        return mongoTemplate.find(query,Emps.class);
    }

    /*
     * project:列出所有本次查询的字段，包括查询条件的字段和需要搜索的字段；
     * match:搜索条件criteria
     * unwind：某一个字段是集合，将该字段分解成数组
     * group：分组的字段，以及聚合相关查询
     *      sum：求和(同sql查询)
     *      count：数量(同sql查询)
     *      as:别名(同sql查询)
     *      addToSet：将符合的字段值添加到一个集合或数组中
     * sort：排序
     * skip&limit：分页查询
     */
    @ResponseBody
    @GetMapping("/agreegation")
    public List agreegation() {
        //统计部门人数
        Aggregation aggregation=Aggregation.newAggregation(Aggregation.group("depno").count().as("部门人数"));
        AggregationResults results=mongoTemplate.aggregate(aggregation,"emp", BasicDBObject.class);
        return results.getMappedResults();
    }
    //or
    /*@ResponseBody
    @GetMapping("/orOperator")
    public List orOperator() {
        Query query=new Query(Criteria.where("sal").gt(1500)
        .orOperator(Criteria.where("empno")).is(7782));
        return mongoTemplate.find(query,Emps.class);
    }*/
}
