/*
 * Copyright (c) 2015 By Timothy Zhang
 */

package com.zts1993.gse.util;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.zts1993.gse.bean.IndexNotify;
import com.zts1993.gse.db.mongodb.MongoDB;
import com.zts1993.gse.db.redis.RedisQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.bson.Document;

import java.util.Iterator;

/**
 * Created by TianShuo on 2015/4/14.
 */
public class ReGenerateIndexNotifyQueue {

    private static final Logger logger = LogManager.getLogger("GenIndexNotifyQueue");

    public static void main(String[] args) {

        RedisQueue redisQueue = new RedisQueue("IndexNotifyQueue");


        MongoCollection<Document> html_detail = MongoDB.getMongoCollection("html", "html_detail");
        FindIterable<Document> it = html_detail.find();

        Iterator<Document> iterator = it.iterator();
        while (iterator.hasNext()) {
            try {
                Document doc = iterator.next();

                String url = doc.get("url").toString();
                String title = "";
                String hash_key = doc.get("docId").toString();
                String storage_type = "mongofs";
                String page_encoding = doc.get("encoding").toString();
                String queue_time = "";

                IndexNotify indexNotify = new IndexNotify(url, title, hash_key, storage_type, page_encoding, queue_time);
                String item = JSON.toJSONString(indexNotify);

                logger.info("Pushing url:" + url);

                redisQueue.push(item);
            } catch (Exception e) {
                logger.info(e.getMessage());
                e.printStackTrace();
            }


        }


    }
}
