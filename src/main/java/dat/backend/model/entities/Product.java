package dat.backend.model.entities;

public class Product {

    private int id;
    private String name;
    private String description;
    private Unit unit;
    private float pricePrUnit;
    private ProductType type;
    private int productId;
    private float length;
    private float width;


    public Product(int id, String name, String description, Unit unit, float pricePrUnit, ProductType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.pricePrUnit = pricePrUnit;
        this.type = type;
    }

    public Product(String name, String description, Unit unit, float pricePrUnit, ProductType type, int id, int productId, float length, float width) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.pricePrUnit = pricePrUnit;
        this.type = type;
        this.id = id;
        this.productId = productId;
        this.length = length;
        this.width = width;
    }

    public int getProductId() {
        return productId;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public float getPricePrUnit() {
        return pricePrUnit;
    }

    public void setPricePrUnit(float pricePrUnit) {
        this.pricePrUnit = pricePrUnit;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
