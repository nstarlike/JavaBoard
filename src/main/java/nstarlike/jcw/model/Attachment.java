package nstarlike.jcw.model;

public class Attachment {
	private long id;
	private long postId;
	private String filename;
	private String path;
	private String registered;

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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRegistered() {
		return registered;
	}

	public void setRegistered(String registered) {
		this.registered = registered;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", postId=" + postId + ", filename=" + filename + ", path=" + path
				+ ", registered=" + registered + "]";
	}

}
