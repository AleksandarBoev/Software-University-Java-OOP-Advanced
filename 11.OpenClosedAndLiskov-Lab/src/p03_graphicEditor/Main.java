package p03_graphicEditor;

import p03_graphicEditor.shapes.Circle;
import p03_graphicEditor.shapes.MyCustomShape;
import p03_graphicEditor.shapes.Rectangle;
import p03_graphicEditor.shapes.Shape;

public class Main {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();

        graphicEditor.drawShape(new MyCustomShape());
    }
}
