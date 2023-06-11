package inventorysystem.ItemandFactory;

import inventorysystem.Connection.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class Item {
    private static Connection conn = SingletonConnection.getInstance().getCon();
    private int ID;
    private String Name;
    private int Quantity;
    private String EmpName;
    private float Price;
    private float UpdatedPrice;
    private LocalDateTime DateCreated;
    private LocalDateTime DateUpdated;
    private String UpdatedBy;

    // builder pattern to create objects of the class with a clean and easy to read code
    public static class Builder {
        private int ID;
        private String Name;
        private int Quantity;
        private String EmpName;
        private float Price;
        private float UpdatedPrice;
        private LocalDateTime DateCreated;
        private LocalDateTime DateUpdated;
        private String UpdatedBy;

        // constructor for the builder class
        public Builder() {
        }

        // method to set the ID of the item
        public Builder ID(int ID) {
            this.ID = ID;
            return this;
        }

        // method to set the name of the item
        public Builder Name(String Name) {
            this.Name = Name;
            return this;
        }

        // method to set the quantity of the item in stock
        public Builder Quantity(int Quantity) {
            this.Quantity = Quantity;
            return this;
        }

        public Builder EmpName(String EmpName) {
            this.EmpName = EmpName;
            return this;
        }
        
        public Builder Price(Float Price) {
            this.Price = Price;
            return this;
        }
        
        public Builder UpdatedPrice(Float UpdatedPrice) {
            this.UpdatedPrice = UpdatedPrice;
            return this;
        }

        public Builder DateCreated(LocalDateTime DateCreated) {
            this.DateCreated = DateCreated;
            return this;
        }
        
        public Builder DateUpdated(LocalDateTime DateUpdated) {
            this.DateUpdated = DateUpdated;
            return this;
        }
        
        public Builder UpdatedBy(String UpdatedBy) {
            this.UpdatedBy = UpdatedBy;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
    
    // constructor for the Item class
    private Item(Builder builder) {
        this.ID = builder.ID;          // sets the ID of the item to the value from the builder object
        this.Name = builder.Name;       // sets the name of the item to the value from the builder object
        this.Quantity = builder.Quantity;    // sets the quantity of the item to the value from the builder object
        this.EmpName = builder.EmpName;    // sets the name of the employee who added the item to the value from the builder object
        this.Price = builder.Price;
        this.DateCreated = builder.DateCreated;
        this.DateUpdated = builder.DateUpdated;
        this.UpdatedBy = builder.UpdatedBy;
        this.UpdatedPrice = builder.UpdatedPrice;
    }

    // getter methods for the Item class
    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getEmpName() {
        return EmpName;
    }
    
    public Float getPrice() {
        return Price;
    }
    
    public Float getUpdatedPrice() {
        return UpdatedPrice;
    }

    public LocalDateTime getDateCreated() {
        return DateCreated;
    }
    
    public LocalDateTime getDateUpdatedted() {
        return DateUpdated;
    }
    
    public String getUpdatedBy() {
        return UpdatedBy;
    }
    
}