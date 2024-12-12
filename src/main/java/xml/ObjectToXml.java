package xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class ObjectToXml {
    public static void main(String[] args) throws Exception {
        File xml_file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\employee.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Employee employee = new Employee(1, "Vimal Jaiswal", 60000);
        marshaller.marshal(employee, xml_file);
    }
}
