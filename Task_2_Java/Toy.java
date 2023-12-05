package Task_2_Java;


import java.util.Objects;

public class Toy implements Comparable<Toy> {

    private int toyId;
    private String toyTitle;
    private int toyFrequency;
     
    public Toy(int toyId, String toyTitle, int toyFrequency) {
        this.toyId = toyId;
        this.toyTitle = toyTitle;
        this.toyFrequency = toyFrequency;
    }

    @Override
    public String toString() {
        return "toy ID: " + toyId+ ", Toy Title: " + toyTitle + ", Toy Frequency: " + toyFrequency;
    }
    
    public int getToyId() {
        return toyId;
    }

    public String getToyTitle() {
        return toyTitle;
    }

    public void setToyFrequency(int toyFrequency) {
        this.toyFrequency = toyFrequency;
    }
    
    public int getToyFrequency() {
        return toyFrequency;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
            Toy toy = (Toy) o;
            return toyTitle.equals(toy.toyTitle);
        }
    

    public int hashCode() {
        return Objects.hash(toyTitle);
    }

    @Override
    public int compareTo(Toy o) {
        return Integer.compare(this.toyFrequency, o.toyFrequency);
    }
}