package p05_square;

public class Square extends BaseQuadratricShape {
    public Square() {
    }

    public Square(int side) {
        super.height = side;
    }

    @Override
    public int getHeight() {
        return super.width;
    }

    @Override
    public void setHeight(int side) {
        super.width = height;
    }

    @Override
    public int getArea() {
        return super.height * super.height;
    }
}
