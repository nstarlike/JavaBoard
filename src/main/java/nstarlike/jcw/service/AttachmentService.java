package nstarlike.jcw.service;

import java.util.List;

import nstarlike.jcw.model.Attachment;

public interface AttachmentService {
	public int attach(Attachment attachment);
	public Attachment getById(long id);
	public List<Attachment> getByPostId(long postId);
	public int delete(long id);
	public int deleteByPostId(long postId);
}
