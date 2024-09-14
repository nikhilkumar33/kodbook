package com.kodbook.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.Post;
import com.kodbook.services.PostService;

@Controller
public class PhotoController 
{
	@Autowired
	PostService pserv;
	
	@PostMapping("/createpost")
	public String creaePost(@RequestParam("caption") String caption,@RequestParam("photo") MultipartFile photo)
	{
		Post post=new Post();
		post.setCaption(caption);
		try {
			post.setPhoto(photo.getBytes());
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
		}
		pserv.createPost(post);
		return "home";
		
	}
	@GetMapping("/opennewsfeed")
	public String showPosts(Model model)
	{
		List<Post> allposts=pserv.fetchAllPosts();
		model.addAttribute("allposts", allposts);
		return "home";
	}
}
