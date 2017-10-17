package PO53.KuznecovAA.wdad.learn.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

/**
 * Created by U_LIVT35404 on 28.09.2017.
 */
public class XmlTask {

    private Document document;
    private String path;

    public XmlTask() throws IOException, SAXException, ParserConfigurationException {
        this("src/PO53/KuznecovAA/wdad/learn/xml/test.xml");
    }

    public XmlTask(String path) throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        document = dBuilder.parse(fXmlFile);
        this.path = path;
    }

    private void updateDocument() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(domSource, streamResult);
    }


    public void setPrivileges(String noteTitle, User user, int newRights) throws TransformerException {
        NodeList noteList = document.getElementsByTagName("note");
        NodeList usersList = document.getElementsByTagName("user");
        // NodeList noteList;
        NodeList userList;
        NodeList userNode;
        NamedNodeMap noteAttributes;
        String newStringRights="";
        boolean isOwnerFounded;
        if(newRights == 3){newStringRights="R"; }
        if(newRights == 1){newStringRights="RW";}
        for (int i = 0; i < noteList.getLength(); i++) {
            userList = ((Element) noteList.item(i)).getElementsByTagName("title");
            for (int j = 0; j < userList.getLength(); j++) {
                userNode = userList.item(j).getChildNodes();
                isOwnerFounded = false;
                for (int k = 0; k < userNode.getLength(); k++) {
                    if (("title".equals(userNode.item(k).getNodeName())) &&
                            userNode.item(k).getTextContent().equals(noteTitle)) {
                        isOwnerFounded = true;
                        for (int e = 0; e < usersList.getLength(); e++) {
                            noteAttributes = usersList.item(e).getAttributes();
                            if(newRights!=0)
                                if ((noteAttributes.getNamedItem("user").getNodeValue().equals(user))) {

                                    noteAttributes.getNamedItem("rights").setNodeValue(newStringRights);

                                }
                                else{
                                    noteAttributes.removeNamedItem("user");
                                }


                        }
                    }
                }
            }

        }

        updateDocument();
    }

    public void updateNode(User owner, String title, String newText) throws TransformerException {
        NodeList ownerList = document.getElementsByTagName("owner");
        NodeList titleList;
        NamedNodeMap titleNodes;
        NamedNodeMap titleAttributes;
        boolean isOwnerFounded;
        for (int i = 0; i < ownerList.getLength(); i++) {
            titleAttributes = ownerList.item(i).getAttributes();
            if (titleAttributes.getNamedItem("name").getNodeValue().equals(owner))  {
                titleList = ((Element) ownerList.item(i)).getElementsByTagName("title");
                for (int j = 0; j < titleList.getLength(); j++) {
                    titleNodes = titleList.item(j).getAttributes();
                    isOwnerFounded = false;
                    for (int k = 0; k < titleNodes.getLength(); k++) {
                        if (("title".equals(titleNodes.item(k).getNodeName())) &&
                                titleNodes.item(k).getAttributes().getNamedItem("title").getNodeValue().equals(title)) {
                            isOwnerFounded = true;
                        }
                        if ((titleNodes.item(k).getNodeName().equals("title"))) {
                            titleNodes.getNamedItem("text").setTextContent(newText);

                        }
                    }
                }
            }
        }
        updateDocument();

    }

    public String getNoteText(User owner, String title) {
        NodeList userList = document.getElementsByTagName("owner"); //text
        NodeList titleList = document.getElementsByTagName("title");
        NodeList titleNodes;
        NamedNodeMap titleNodeAtt;
        int count = userList.getLength();
        String att ="";
        for (int i=0; i<count; i++) {
            //att= (NamedNodeMap) userList.item(i).getAttributes().getNamedItem("text");
            att = userList.item(i).getNodeValue();
            titleNodeAtt = userList.item(i).getAttributes();
            /// if(titleNodeAtt.getNamedItem("title")== title.get )
            if ((titleNodeAtt.getNamedItem("owner").getNodeValue().equals(owner))){
                for (int j=0; j< titleList.getLength();j++)
                {
                    titleNodes = titleList.item(j).getChildNodes();
                    for(int k=0; k<titleNodes.getLength(); k++)
                    {
                        if(titleNodes.item(k).getAttributes().getNamedItem("title").getNodeValue().equals(title))
                            att =titleNodes.item(k).getTextContent();
                    }
                }
            }
        }
        return att;

    }

}
