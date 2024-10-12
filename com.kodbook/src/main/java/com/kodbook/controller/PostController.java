package com.kodbook.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.Post;
import com.kodbook.entities.Users;
import com.kodbook.services.PostService;
import com.kodbook.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController 
{
	@Autowired
	PostService pserv;
	
	@Autowired
	UsersService userv;
	
	@PostMapping("/createpost")
	public String createPost(@RequestParam("caption") String caption,
	                         @RequestParam("photo") MultipartFile photo, Model model, HttpSession session) {
	    String username = (String) session.getAttribute("username");
	    Users user = userv.getUser(username);

	    Post post = new Post();

	    // Updating post object
	    post.setUser(user);
	    post.setCaption(caption);
	    try {
	        post.setPhoto(photo.getBytes());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    pserv.createPost(post);

	    // Updating user's object
	    List<Post> posts = user.getPosts();
	    if (posts == null) {
	        posts = new ArrayList<>();
	    }
	    if (!posts.contains(post)) {
	        posts.add(post); // Add post only if it's not already in the list
	    }
	    user.setPosts(posts);
	    userv.updateUser(user);

	    List<Post> allPosts = pserv.fetchAllPosts();
	    model.addAttribute("allposts", allPosts);
	    model.addAttribute("user", user);
	    return "home";
	}

	@GetMapping("/opennewsfeed")
	public String showPosts(Model model,HttpSession session )
	{
		if(session.getAttribute("username")==null)
			return "index";
		
		List<Post> allposts=pserv.fetchAllPosts();
		model.addAttribute("allposts", allposts);
		String username = (String) session.getAttribute("username");
	    Users user = userv.getUser(username);
		model.addAttribute("user", user);
		return "home";
	}
	
	@PostMapping("/likepost")
	public String likePost(@RequestParam Long id, Model model,HttpSession session) {
		
		Post post=pserv.getPost(id);
		post.setLikes(post.getLikes() + 1);
		pserv.updatePost(post);
		
		String username = (String) session.getAttribute("username");
	    Users user = userv.getUser(username);
		model.addAttribute("user", user);
		
		List<Post> allposts = pserv.fetchAllPosts();
		model.addAttribute("allposts", allposts);
		return "home";
	}
	
	@PostMapping("/addcomment")
	public String addComment(@RequestParam Long id,@RequestParam String comment, Model model,HttpSession session) {
		Post post= pserv.getPost(id);
		List<String> comments = post.getComments();
		//if the fetched comments is null then it will create an object to resolve the null pointer exception
		if(comments == null) {
			comments = new ArrayList<String>();
		}
		comments.add(comment);
		post.setComments(comments);
		pserv.updatePost(post);
		
		String username = (String) session.getAttribute("username");
	    Users user = userv.getUser(username);
		model.addAttribute("user", user);
					
		List<Post> allposts = pserv.fetchAllPosts();
		model.addAttribute("allposts", allposts);
		return "home";
	}
	
}
