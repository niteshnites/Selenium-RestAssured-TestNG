package xml;

import io.restassured.path.xml.XmlPath;

public class SimpleXml {
    public static void main(String[] args) {
        String stringResponse = "<employee>\n" +
                "    <name>Vimal Jaiswal</name>\n" +
                "    <salary>50000.0</salary>\n" +
                "</employee>";
        XmlPath xmlPath = new XmlPath(stringResponse);
        String name = xmlPath.get("employee.name");
        System.out.println(xmlPath.get("employee.salary").toString());
        System.out.println(name);
    }
}
