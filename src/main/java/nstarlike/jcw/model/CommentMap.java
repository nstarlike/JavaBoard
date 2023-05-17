package nstarlike.jcw.model;

public class CommentMap extends Comment {
	private long total;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CommentMap [total=" + total + ", comment=" + super.toString() + "]";
	}
	
}
