package nstarlike.jcw.dao;

import java.util.List;
import java.util.Map;

import nstarlike.jcw.model.Comment;

public interface CommentDao {
	public int create(Comment comment);
	public List<Comment> readAll(Map<String, String> params);
	public int delete(long id);
}
