package com.saurav.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saurav.models.Post;
import com.saurav.models.User;
import com.saurav.repository.PostRepository;
import com.saurav.repository.UserRepository;
@Service
public class PostServiceImplimentation implements PostService {
     @Autowired
	PostRepository postRepository;
     @Autowired
     UserService userService;
     @Autowired
     UserRepository userRepository;
	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		
		// TODO Auto-generated method stub
		
		User user = userService.findUserById(userId);
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("You can't delete another user post");
		}
		postRepository.delete(post);
		return "Post Deleted Succesfully";
		
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		// TODO Auto-generated method stub
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Post> opt = postRepository.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("Post not found with id "+ postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
				
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
			
		}
		else {
			user.getSavedPost().add(post);
		}
		 userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
		post.getLiked().add(user);
		}
		return postRepository.save(post);
		
		
	}

}
