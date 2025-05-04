package com.TarreVizla.JobPortal.repository;

import com.TarreVizla.JobPortal.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SearchRepository {
    List<Post> findByKeyword(String keyword);
}
