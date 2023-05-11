package dat.backend.model.entities;

public class ProductVariant {
private int id;
private int productId;
private float length;
private float width;

    public ProductVariant(int id, int productId, float length, float width) {
        this.id = id;
        this.productId = productId;
        this.length = length;
        this.width = width;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
