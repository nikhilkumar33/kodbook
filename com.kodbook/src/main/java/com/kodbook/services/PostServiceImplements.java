package com.kodbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Post;
import com.kodbook.repositories.PostRepository;

@Service
public class PostServiceImplements implements PostService
{
	@Autowired
	PostRepository prepo;

	@Override
	public void createPost(Post post) {
		prepo.save(post);
	}

	@Override
	public List<Post> fetchAllPosts() {
		List<Post> allposts=prepo.findAll();
		return allposts;
	}
}
