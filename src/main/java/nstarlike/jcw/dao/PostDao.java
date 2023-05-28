package nstarlike.jcw.dao;

import java.util.List;
import java.util.Map;

import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;

public interface PostDao {
	public int create(Post post);
	public List<PostMap> readAll(Map<String, String> params);
	public Post readById(long id);
	public List<PostMap> readEntire(Map<String, String> params);
	public int update(Post post);
	public int hit(long id);
	public int delete(long id);
}
