package nstarlike.jcw.model;

public class Attachment {
	private long id;
	private long postId;
	private String filename;
	private String filepath;
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

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getRegistered() {
		return registered;
	}

	public void setRegistered(String registered) {
		this.registered = registered;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", postId=" + postId + ", filename=" + filename + ", filepath=" + filepath
				+ ", registered=" + registered + "]";
	}

}
