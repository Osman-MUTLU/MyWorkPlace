public class CountryNode {
    private String name;
    private CountryNode next;

    public CountryNode(String dataToAdd) {
        this.name = dataToAdd;
        this.next = null;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public CountryNode getNext() {
        return next;
    }

    public void setNext(CountryNode next) {
        this.next = next;
    }
}
