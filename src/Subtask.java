public class Subtask extends Task {

    private Epic father;

    public Subtask(String title, String description, int id) {
        super(title, description, id);
    }

    public Epic getFather() {
        return father;
    }

    public void setFather(Epic father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "\n\t name= " + name +
                "\n\t description= " + description +
                "\n\t id= " + id +
                "\n\t status= " + status +
                "\n\t}";
    }
}