package com.example.demo.repo;

import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostReprository extends CrudRepository<Post,Long> {
}
