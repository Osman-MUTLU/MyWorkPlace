public class CircularNode {
    private Object data;
    private CircularNode link;

    public CircularNode(Object data) {
        this.data = data;
        link = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CircularNode getLink() {
        return link;
    }

    public void setLink(CircularNode link) {
        this.link = link;
    }
}
