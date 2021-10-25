public class LetterNode {
    private LetterNode prev;
    private LetterNode next;
    private Object element;

    public LetterNode(char element) {
        this.element = element;
        prev = null;
        next = null;
    }

    public LetterNode getPrev() {
        return prev;
    }

    public void setPrev(LetterNode prev) {
        this.prev = prev;
    }

    public LetterNode getNext() {
        return next;
    }

    public void setNext(LetterNode next) {
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public void setData(char element) {
        this.element = element;
    }
}
