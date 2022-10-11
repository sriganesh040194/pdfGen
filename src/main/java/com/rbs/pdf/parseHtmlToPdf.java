package com.rbs.pdf;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.*;


public class parseHtmlToPdf {


        /**
         * Creates a PDF with the words "Hello World" for local Testing
         * @throws IOException
         * @throws DocumentException
         */
//        public static void main(String[] args) throws DocumentException, IOException {
//            parseHtmlToPdf.createPdf("<h1>Hello World</h1>", "/Users/sriganeshk/AnypointStudio/rbs-file-dumps/pdf-gen/test.pdf");
//        }
        public static void createPdf(String html, String destination) throws IOException, DocumentException {
            File file = new File(destination);
            file.getParentFile().mkdirs();
            generatePdf(html, destination);
        }

        /**
         * Creates a PDF with the words "Hello World"
         * @param file
         * @throws IOException
         * @throws DocumentException
         */
        public static void generatePdf(String html, String file) throws IOException, DocumentException {
            Document document = new Document();
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(file));
            document.open();
            PdfReader reader;


            reader = new PdfReader(parseHtml(html));
            copy.addDocument(reader);
            reader.close();

            document.close();
        }

        public static byte[] parseHtml(String html) throws DocumentException, IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            // step 3
            document.open();

            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));
            // step 5
            document.close();
            // return the bytes of the PDF
            return baos.toByteArray();
        }

}
