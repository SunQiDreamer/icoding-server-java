package com.sq.ic.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/string/add")
    @ApiOperation("添加string")
    public String addString(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
        return "ok";
    }

    @GetMapping("/string/{key}")
    @ApiOperation("根据key获取string")
    public String getString(@PathVariable String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @PostMapping("/hash/add")
    @ApiOperation("添加hash")
    public String addHash(String hashKey, String no, String age) {
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("no", no);
        hashMap.put("age", age);
        hashOperations.putAll(hashKey, hashMap);
        return "ok";
    }

    @PostMapping("/hash/update/{hash}/{key}")
    @ApiOperation("更新hash")
    public String update(@PathVariable String hash, @PathVariable String key, String value) {
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        hashOperations.put(hash, key, value);
        return "ok";
    }

    @GetMapping("/hash/{key}")
    @ApiOperation("根据hash key获取hash")
    public Map<Object, Object> getHash(@PathVariable String key) {
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    @GetMapping("/hash/{hash}/{key}")
    @ApiOperation("根据hash key和key获取field")
    public String getHashField(@PathVariable String key, @PathVariable String hash) {
        HashOperations<String, Object, Object> hashOperations = stringRedisTemplate.opsForHash();
        return (String) hashOperations.get(hash, key);
    }

    @PostMapping("/list/add")
    @ApiOperation("list add")
    public String addList(String key) {
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        listOperations.leftPushAll(key, "1", "2");
        return "ok";
    }

    @GetMapping("/list")
    @ApiOperation("list")
    public String list(String key) {
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        List<String> list = listOperations.range(key, 0, -1);
        if (list != null) return list.toString();
        return "[]";
    }

    @PostMapping("/set/add")
    @ApiOperation("set add")
    public String addSet(String key, String value) {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        if (!setOperations.isMember(key, value)) {
            setOperations.add(key, value);
        }
        return "ok";
    }

    @GetMapping("/set/{key}")
    @ApiOperation("set add")
    public Set<String> sets(@PathVariable String key) {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        return setOperations.members(key);
    }

    @PostMapping("/zset/add")
    @ApiOperation("set add")
    public String addZSet(String key, String value, double score) {
        ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
        zSetOperations.add(key, value, score);
        return "ok";
    }

    @GetMapping("/zset/{key}")
    @ApiOperation("set list")
    public Set<String> zsets(@PathVariable String key) {
        ZSetOperations<String, String> setOperations = stringRedisTemplate.opsForZSet();
        return setOperations.range(key, 0, -1);
    }

}
