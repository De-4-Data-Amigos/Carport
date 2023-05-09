package dat.backend.model.entities;

public class ItemEntry {

    private int id;
    private int ordre_id;
    private int productVariantId;

    public ItemEntry(int id, int ordre_id, int productVariantId) {
        this.id = id;
        this.ordre_id = ordre_id;
        this.productVariantId = productVariantId;
    }


    public int getId() {
        return id;
    }

    public int getOrdre_id() {
        return ordre_id;
    }

    public int getProductVariantId() {
        return productVariantId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrdre_id(int ordre_id) {
        this.ordre_id = ordre_id;
    }

    public void setProductVariantId(int productVariantId) {
        this.productVariantId = productVariantId;
    }



}
