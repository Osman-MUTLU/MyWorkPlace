public class ScoreNode { //SingleLinkedList's Node
    private String name;
    private int score;
    private ScoreNode next;

    public ScoreNode(String name, int score) {
        this.name = name;
        this.score = score;
        this.next = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ScoreNode getNext() {
        return next;
    }

    public void setNext(ScoreNode next) {
        this.next = next;
    }
}
