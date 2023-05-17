package nstarlike.jcw.model;

public class PostMap extends Post {
	private long total;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "PostMap [total=" + total + "post=" + super.toString() + "]";
	}

}
