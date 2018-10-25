package murphy.christopher.bakingapp.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import murphy.christopher.bakingapp.utils.Constants;

@Parcel(Parcel.Serialization.BEAN)
public class Ingredients {

    @SerializedName("quantity")
    private double quantity;

    @SerializedName("measure")
    private String measurement;

    @SerializedName("ingredient")
    private String ingredient;

    public Ingredients(){
        this.ingredient = "";
        this.measurement = "";
        this.quantity = 0.0;
    }

    @ParcelConstructor
    public Ingredients(double quantity, String measurement, String ingredient){
        this.ingredient = ingredient;
        this.measurement = measurement;
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String toString(){
        return quantity + Constants.SPACE + measurement + Constants.SPACE + ingredient + Constants.NEWLINE;
    }
}
