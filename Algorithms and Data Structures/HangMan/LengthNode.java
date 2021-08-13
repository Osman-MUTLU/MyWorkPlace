
public class LengthNode {
    private int length;
    private LengthNode down;
    private CountryNode right;

    public LengthNode(int dataToAdd) {
        this.length = dataToAdd;
        this.down = null;
        this.right = null;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public LengthNode getDown() {
        return down;
    }

    public void setDown(LengthNode down) {
        this.down = down;
    }

    public CountryNode getRight() {
        return right;
    }

    public void setRight(CountryNode right) {
        this.right = right;
    }
}