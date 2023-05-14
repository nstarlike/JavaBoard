package nstarlike.jcw.dao;

import java.util.List;
import java.util.Map;

import nstarlike.jcw.model.Post;

public interface PostDao {
	public int create(Post post);
	public List<Post> readAll(Map<String, String> params);
	public Post readById(long id);
	public int update(Post post);
	public int hit(long id);
	public int delete(long id);
}
