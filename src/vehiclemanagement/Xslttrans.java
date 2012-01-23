/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclemanagement;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Sarantis
 */
public class Xslttrans {
    
    public String Transformation(String xdoc)
    {
        try {
            StringReader reader = new StringReader(xdoc);
            StringWriter writer = new StringWriter();
            
            // create a transform factory instance
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            // create a transformer for the stylesheet
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("./src./vehiclemanagement./veh2dc.xsl")));
                        
            
            // transform the source XML
            transformer.transform(new javax.xml.transform.stream.StreamSource(reader), new javax.xml.transform.stream.StreamResult(writer));
                        
            String result = writer.toString();
            System.out.println(result);
            return result;
    }   catch (TransformerException ex) {
            Logger.getLogger(Xslttrans.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
