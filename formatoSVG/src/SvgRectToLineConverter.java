import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SvgRectToLineConverter {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document newDocument = builder.newDocument();

            Element rootElement = newDocument.createElement("svg");
            newDocument.appendChild(rootElement);

            Element rect = newDocument.createElement("rect");
            rect.setAttribute("x", "10");
            rect.setAttribute("y", "10");
            rect.setAttribute("width", "100");
            rect.setAttribute("height", "50");
            rootElement.appendChild(rect);


            addLinesForRectangle(rect, newDocument);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addLinesForRectangle(Element rect, Document newDocument) {
        String x = rect.getAttribute("x");
        String y = rect.getAttribute("y");
        String width = rect.getAttribute("width");
        String height = rect.getAttribute("height");


        if (isNumeric(x) && isNumeric(y) && isNumeric(width) && isNumeric(height)) {
            double xValue = Double.parseDouble(x);
            double yValue = Double.parseDouble(y);
            double widthValue = Double.parseDouble(width);
            double heightValue = Double.parseDouble(height);


            createLine(newDocument, xValue, yValue, xValue + widthValue, yValue);
            createLine(newDocument, xValue + widthValue, yValue, xValue + widthValue, yValue + heightValue); // Línea derecha
            createLine(newDocument, xValue + widthValue, yValue + heightValue, xValue, yValue + heightValue); // Línea inferior
            createLine(newDocument, xValue, yValue + heightValue, xValue, yValue);
        } else {
            System.out.println("Atributos no válidos en el rectángulo: " + rect);
        }
    }

    private static void createLine(Document doc, double x1, double y1, double x2, double y2) {
        Element line = doc.createElement("line");
        line.setAttribute("x1", String.valueOf(x1));
        line.setAttribute("y1", String.valueOf(y1));
        line.setAttribute("x2", String.valueOf(x2));
        line.setAttribute("y2", String.valueOf(y2));
        line.setAttribute("stroke", "black");
        doc.getDocumentElement().appendChild(line);
    }

    private static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}