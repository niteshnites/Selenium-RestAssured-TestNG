package xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class XMLToObject {
    public static void main(String[] args) throws Exception {
        File xml_file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\employee.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
        Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();

        Employee employee = (Employee) jaxbUnMarshaller.unmarshal(xml_file);
        System.out.println(employee.getId()+" "+employee.getName()+" "+employee.getSalary());
    }
}
