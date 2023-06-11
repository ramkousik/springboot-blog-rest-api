package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    public PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto) {

            Post post = mapToEntity(postDto);



            Post newPost = postRepository.save(post);

            PostDto postResponse = mapToDto(newPost);

            return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {

     List<Post> posts = postRepository.findAll();

     return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","id", id));
        postRepository.delete(post);
    }

    //convert Entity to Dto
    private PostDto mapToDto(Post post) {

        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    //convert Dto to Entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }
}
