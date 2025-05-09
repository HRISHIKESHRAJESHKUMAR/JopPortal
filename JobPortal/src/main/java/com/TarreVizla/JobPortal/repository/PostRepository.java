package com.TarreVizla.JobPortal.repository;

import com.TarreVizla.JobPortal.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
