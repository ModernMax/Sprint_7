package dto;
public class DeleteCourier {
    private int id;

    public DeleteCourier(int id) {
        this.id = id;
    }

    public DeleteCourier() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}