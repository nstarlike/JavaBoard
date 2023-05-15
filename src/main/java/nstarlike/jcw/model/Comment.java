package nstarlike.jcw.model;

public class Comment {
	private long id;
	private long postId;
	private long writerId;
	private String content;
	private String written;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getWriterId() {
		return writerId;
	}

	public void setWriterId(long writerId) {
		this.writerId = writerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWritten() {
		return written;
	}

	public void setWritten(String written) {
		this.written = written;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", postId=" + postId + ", writerId=" + writerId + ", content=" + content
				+ ", written=" + written + "]";
	}

}
