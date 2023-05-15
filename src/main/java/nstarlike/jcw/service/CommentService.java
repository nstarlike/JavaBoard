package nstarlike.jcw.service;

import java.util.List;
import java.util.Map;

import nstarlike.jcw.model.Comment;

public interface CommentService {
	public int write(Comment comment);
	public List<Comment> listAll(Map<String, String> params);
	public Comment getById(long id);
	public int delete(long id);
}
