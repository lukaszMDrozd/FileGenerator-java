package com.lukasz.fileGenerator;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class CreateXMLFile {

    static void doCreateXMLFile(HashMap<Long, String> mapOfIds, double amount) {

        String packageId = mapOfIds.entrySet().iterator().next().getValue();

        final String fileName = "templateFile_" + packageId + ".xml";

        final String xmlTemplateFilePath = "/home/principium87/Documents/GeneratorPlikow/generatedFiles/" + fileName;

        try{

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();
            document.setXmlStandalone(true);

            //Root Element = Document
            Element root = document.createElement("Document");

            Attr xmlns = document.createAttribute("xmlns");
            xmlns.setValue("urn:iso:std:iso:20022:tech:xsd:pain.001.001.03");
            root.setAttributeNode(xmlns);

            Attr xmlnsXsi = document.createAttribute("xmlns:xsi");
            xmlnsXsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
            root.setAttributeNode(xmlnsXsi);

            document.appendChild(root);

            // CstmrCdtTrfInitn element
            Element cstmrCdtTrfInitn = document.createElement("CstmrCdtTrfInitn");
            root.appendChild(cstmrCdtTrfInitn);

                // GrpHdr element
                Element grpHdr = document.createElement("GrpHdr");

                    Element msgId = document.createElement("MsgId");
                    msgId.appendChild(document.createTextNode(packageId));

                    Element creDtTm = document.createElement("CreDtTm");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\'T'HH:mm:ss");
                    creDtTm.appendChild(document.createTextNode(dateFormat.format(Calendar.getInstance().getTime())));

                    Element ctrlSum = document.createElement("CtrlSum");
                    ctrlSum.appendChild(document.createTextNode(String.valueOf(String.format("%.2f", (RoundDouble.round((mapOfIds.size() - 1) * amount))))));

                    Element nbOfTxs = document.createElement("NbOfTxs");
                    nbOfTxs.appendChild(document.createTextNode(String.valueOf(mapOfIds.size() - 1)));

                    Element initgPty = document.createElement("InitgPty");

                        Element id = document.createElement("Id");
                        Element prvtId = document.createElement("PrvtId");
                        Element othr = document.createElement("Othr");
                        Element id2 = document.createElement("Id");
                        id2.appendChild(document.createTextNode("MY COMPANY FIN. SER.VDAC"));

                        grpHdr.appendChild(msgId);
                        grpHdr.appendChild(creDtTm);
                        grpHdr.appendChild(nbOfTxs);
                        grpHdr.appendChild(ctrlSum);
                        grpHdr.appendChild(initgPty);
                            initgPty.appendChild(id);
                                id.appendChild(prvtId);
                                    prvtId.appendChild(othr);
                                        othr.appendChild(id2);


                // PmtInf element
                Element pmtInf = document.createElement("PmtInf");

                    Element pmtInfId = document.createElement("PmtInfId");
                    pmtInfId.appendChild(document.createTextNode(packageId));

                    Element pmtMtd = document.createElement("PmtMtd");
                    pmtMtd.appendChild(document.createTextNode("TRF"));

                    Element reqDate = document.createElement("ReqdExctnDt");
                    reqDate.appendChild(document.createTextNode(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));

                    Element dbtr = document.createElement("Dbtr");

                    Element nm = document.createElement("Nm");
                    nm.appendChild(document.createTextNode("MY COMPANY FIN. SER.VDAC"));

                    Element dbtrAcct = document.createElement("DbtrAcct");

                    Element id3 = document.createElement("Id");

                    Element ibanDbtr = document.createElement("IBAN");
                    ibanDbtr.appendChild(document.createTextNode("PL21109010560000000132707792"));

                    Element finInstnId = document.createElement("FinInstnId");

                    Element clrSysMmbID = document.createElement("ClrSysMmbID");

                    Element clrSysID = document.createElement("ClrSysID");

                    Element cd = document.createElement("Cd");
                    cd.appendChild(document.createTextNode("PLKNR"));

            pmtInf.appendChild(pmtInfId);
                    pmtInf.appendChild(pmtMtd);
                    pmtInf.appendChild(reqDate);
                    pmtInf.appendChild(dbtr);
                        dbtr.appendChild(nm);
                    pmtInf.appendChild(dbtrAcct);
                        dbtrAcct.appendChild(id3);
                            id3.appendChild(ibanDbtr);
                        dbtrAcct.appendChild(finInstnId);
                            finInstnId.appendChild(clrSysMmbID);
                                clrSysMmbID.appendChild(clrSysID);
                                    clrSysID.appendChild(cd);

                cstmrCdtTrfInitn.appendChild(grpHdr);
                cstmrCdtTrfInitn.appendChild(pmtInf);
                    pmtInf.appendChild(pmtInfId);
                    pmtInf.appendChild(pmtMtd);
                    pmtInf.appendChild(reqDate);
                    pmtInf.appendChild(dbtr);
                        dbtr.appendChild(nm);
                    pmtInf.appendChild(dbtrAcct);
                        dbtrAcct.appendChild(id3);
                            id3.appendChild(ibanDbtr);
                        dbtrAcct.appendChild(finInstnId);
                            finInstnId.appendChild(clrSysMmbID);
                                clrSysMmbID.appendChild(clrSysID);
                                    clrSysID.appendChild(cd);

            //CdtTrfTxInf element
            for(Map.Entry<Long, String> entry: mapOfIds.entrySet()) {

                if(entry.getValue().equals(packageId)){
                    continue;
                }

                Element cdtTrfTxInf = document.createElement("CdtTrfTxInf");

                Element pmtId = document.createElement("PmtId");

                Element instrId = document.createElement("InstrId");
                instrId.appendChild(document.createTextNode(entry.getValue()));

                Element endToEndId = document.createElement("EndToEndId");
                endToEndId.appendChild(document.createTextNode(entry.getValue()));

                Element amt = document.createElement("Amt");
                Element instdAmt = document.createElement("InstdAmt");
                Attr ccy = document.createAttribute("Ccy");
                ccy.setValue("PLN");
                instdAmt.setAttributeNode(ccy);
                instdAmt.appendChild(document.createTextNode(String.valueOf((String.format("%.2f", amount)))));

                Element cdtr = document.createElement("Cdtr");

                Element nm2 = document.createElement("Nm");
                nm2.appendChild(document.createTextNode("COMPANY AAA"));

                Element cdtrAcct = document.createElement("CdtrAcct");

                Element id4 = document.createElement("Id");

                Element ibanCdtr = document.createElement("IBAN");
                ibanCdtr.appendChild(document.createTextNode("PL71114020620000541236001002"));

                Element rmtInf = document.createElement("RmtInf");

                Element ustrd = document.createElement("Ustrd");
                ustrd.appendChild(document.createTextNode("Transakcja " + entry.getValue()));

                    pmtInf.appendChild(cdtTrfTxInf);
                        cdtTrfTxInf.appendChild(pmtId);
                            pmtId.appendChild(instrId);
                            pmtId.appendChild(endToEndId);
                        cdtTrfTxInf.appendChild(amt);
                            amt.appendChild(instdAmt);
                        cdtTrfTxInf.appendChild(cdtr);
                            cdtr.appendChild(nm2);
                        cdtTrfTxInf.appendChild(cdtrAcct)    ;
                            cdtrAcct.appendChild(id4);
                                id4.appendChild(ibanCdtr);
                        cdtTrfTxInf.appendChild(rmtInf);
                            rmtInf.appendChild(ustrd);
            }

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // the output will be pushed to the standard output ...
            // You can use that for debugging
            StreamResult streamResult = new StreamResult(new File(xmlTemplateFilePath));
//            StreamResult streamResult = new StreamResult(System.out);

            DOMSource domSource = new DOMSource(document);

            transformer.transform(domSource, streamResult);

            System.out.println("Gnereowanie pliku: \"" + fileName + "\" - liczba rekordów w pliku: " + (mapOfIds.size()-1));

        } catch (ParserConfigurationException | TransformerException ex) {
            ex.printStackTrace();
        }
    }
}
