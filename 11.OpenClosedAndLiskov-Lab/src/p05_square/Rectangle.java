package p05_square;

class Rectangle extends BaseQuadratricShape{
    public Rectangle() {
    }

    public Rectangle(int height, int width) {
        super.height = height;
        super.width = width;
    }

    @Override
    public int getArea() {
        return super.height * super.width;
    }
}
