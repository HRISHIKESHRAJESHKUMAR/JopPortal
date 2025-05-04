package com.TarreVizla.JobPortal.repository;

import com.TarreVizla.JobPortal.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository{

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter mongoConverter;

    @Override
    public List<Post> findByKeyword(String keyword) {
        List<Post> posts = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("job_portal_db");
        MongoCollection<Document> collection = database.getCollection("job_post");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "default").append("text", new Document("query", keyword)
                                .append("path", Arrays.asList("techs", "profile", "desc")))),
                new Document("$sort", new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(document -> posts.add(mongoConverter.read(Post.class,document)));
        return posts;
    }
}
