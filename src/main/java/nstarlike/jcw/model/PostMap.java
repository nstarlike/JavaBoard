package nstarlike.jcw.model;

public class PostMap extends Post {
	private String writerName;
	private long total;

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PostMap [writerName=" + writerName + ", total=" + total + ", post=" + super.toString() + "]";
	}

}
