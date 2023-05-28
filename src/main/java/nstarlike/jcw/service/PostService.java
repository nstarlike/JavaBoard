package nstarlike.jcw.service;

import java.util.List;
import java.util.Map;

import nstarlike.jcw.model.Post;
import nstarlike.jcw.model.PostMap;

public interface PostService {
	public int write(Post post);
	public List<PostMap> listAll(Map<String, String> params);
	public Post getById(long id);
	public List<PostMap> listEntire(Map<String, String> params);
	public int update(Post post);
	public int hit(long id);
	public int delete(long id);
}
