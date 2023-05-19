package nstarlike.jcw.dao;

import java.util.List;

import nstarlike.jcw.model.Attachment;

public interface AttachmentDao {
	public int create(Attachment attachment);
	public Attachment readById(long id);
	public List<Attachment> readByPostId(long postId);
	public int delete(long id);
	public int deleteByPostId(long postId);
}
