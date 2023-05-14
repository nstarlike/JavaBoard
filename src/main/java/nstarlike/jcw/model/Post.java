package nstarlike.jcw.model;

public class Post {
	private long id;
	private long writerId;
	private String title;
	private String content;
	private String written;
	private String updated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWriterId() {
		return writerId;
	}

	public void setWriterId(long writerId) {
		this.writerId = writerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=" + id + ", ");
		sb.append("writerId=" + writerId + ", ");
		sb.append("title=" + title + ", ");
		sb.append("content=" + content + ", ");
		sb.append("written=" + written + ", ");
		sb.append("updated=" + updated + ", ");
		return sb.toString();
	}

}
