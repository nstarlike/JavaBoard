package nstarlike.jcw.service;

import java.util.List;
import java.util.Map;

import nstarlike.jcw.model.Post;

public interface PostService {
	public int write(Post post);
	public List<Post> listAll(Map<String, String> params);
	public Post getById(long id);
	public int update(Post post);
	public int hit(long id);
	public int delete(long id);
}
