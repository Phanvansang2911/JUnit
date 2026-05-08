package a_Introductory;

public class Vector2D {
    public Integer x, y;
    
    Vector2D(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    
    Vector2D(Point p1, Point p2) {
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
    }
    
    // FIXED: Sửa (y * v.y) thay vì (y * v.x)
    public int dotProduct(Vector2D v) {
        return (x * v.x) + (y * v.y);
    }
    
    // FIXED: Trực giao khi dotProduct == 0, không phải -1
    public boolean isOrthogonalTo(Vector2D v) {
        return (dotProduct(v) == 0);
    }
}