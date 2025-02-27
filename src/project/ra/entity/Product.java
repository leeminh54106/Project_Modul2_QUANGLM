package project.ra.entity;

import project.ra.feature.impl.CategoryImpl;
import project.ra.feature.impl.FeatureAll;
import project.ra.feature.impl.ProductImpl;
import project.ra.utils.Color;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Product implements Serializable {
    private int productId;
    private String productName;
    private String sku;
    private String description;
    private float price;
    private int quantity;
    private String image;
    private Category category;
    private Date created;
    private Date updated;
    private boolean status;

    public Product() {
    }

    public Product(Category category, Date created, String description, String image, float price, int productId, String productName, int quantity, String sku, boolean status, Date updated) {
        this.category = category;
        this.created = created;
        this.description = description;
        this.image = image;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.sku = sku;
        this.status = status;
        this.updated = updated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void inputProduct(Scanner sc) {
        if (CategoryImpl.categoryList.isEmpty()) {
            return;
        }
        this.productId = autoProductId();
        this.productName = inputProductName(sc);
        this.description = inputDescription(sc);
        this.price = inputPrice(sc);
        this.quantity = inputQuantity(sc);
        this.category = takeCategory(sc);
        this.created = new Date();
        this.sku = autoSku();
        this.status = true;
    }

    public String autoSku() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public Category takeCategory(Scanner sc) {
        for (Category ca : CategoryImpl.categoryList) {
            System.out.println(Color.CYAN + "+------------------------+------------------------------+");
            System.out.printf("| Mã danh mục: %-10d| Tên danh mục: %-15s| \n",
                    ca.getCategoryId(), ca.getCategoryName());
        }
        System.out.println(Color.CYAN + "+------------------------+------------------------------+" + Color.RESET);
        System.out.println("Nhập mã danh mục để thêm sản phẩm:");
        do {
            int choice = FeatureAll.inputNumber(sc);
            int cateId = findCategoryIndexById(choice);
            if (cateId >= 0) {
                return CategoryImpl.categoryList.get(cateId);
            } else {
                System.err.println("Mã danh mục không đúng,vui lòng nhập lại!");
            }
        } while (true);
    }

    public int findCategoryIndexById(int cateId) {
        for (int i = 0; i < CategoryImpl.categoryList.size(); i++) {
            if (CategoryImpl.categoryList.get(i).getCategoryId() == cateId) {
                return i;
            }
        }
        return -1;
    }


    public int inputQuantity(Scanner sc) {
        System.out.println("Nhập số lượng sản phẩm:");
        do {
            String quantity = sc.nextLine();
            if (quantity.trim().isEmpty()) {
                System.err.println("Số lượng không được để trống,vui lòng nhập lại!");
            } else {
                if (Integer.parseInt(quantity) < 0) {
                    System.err.println("Số lượng phải >= 0, vui lòng nhập lại!");
                } else {
                    return Integer.parseInt(quantity);
                }
            }
        } while (true);
    }

    public float inputPrice(Scanner sc) {
        System.out.println("Nhập giá sản phẩm:");
        do {
            String price = sc.nextLine();
            if (price.trim().isEmpty()) {
                System.err.println("Giá không được để trống, vui lòng nhập lại!");
            } else {
                if (Float.parseFloat(price) < 0) {
                    System.err.println("Giá phải > 0, vui lòng nhập lại!");
                } else {
                    return Float.parseFloat(price);
                }
            }
        } while (true);

    }

    public String inputDescription(Scanner sc) {
        System.out.println("Nhập mô tả:");
        return sc.nextLine();
    }

    public String inputProductName(Scanner sc) {
        System.out.println("Nhập tên sản phẩm:");
        do {
            String name = sc.nextLine();
            if (name.trim().isEmpty()) {
                System.err.println("Không được để trống!");
            } else {
                boolean isExist = false;
                for (Product p : ProductImpl.productList) {
                    if (p.getProductName().equals(name)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại,vui lòng nhập lại!");
                } else {
                    return name;
                }
            }
        } while (true);
    }

    public int autoProductId() {
        int max = 0;
        for (Product p : ProductImpl.productList) {
            if (p.getProductId() > max) {
                max = p.getProductId();
            }
        }
        return max + 1;
    }

    public void displayProduct() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String borderColor = Color.BLUE;
        String colColor = borderColor + "+----------------------------------------------+------------------------------+--------------------------------------+---------------------------------+";
        System.out.println(colColor);
        System.out.printf("| Mã sản phẩm: %-32d| Tên sản phẩm: %-15s| Mô tả: %-30s| Danh mục: %-22s|\n",
                this.productId, this.productName, this.description, this.category.getCategoryName());
        System.out.printf("| SKU: %-40s| Tồn kho: %-20d| Thời gian: %-26s| Giá: %-27.0f|\n",
                this.sku, this.quantity, sdf.format(this.created), this.price);
    }
}
