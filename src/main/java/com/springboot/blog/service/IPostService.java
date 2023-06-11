package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;

import java.util.List;

public interface IPostService {
    PostDto createPost(PostDto postDto);

    List<PostDto>getAllPosts();

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePost(Long id);
}
