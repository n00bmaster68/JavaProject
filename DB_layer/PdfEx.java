import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Font;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.Date;
import java.util.*; 
import java.text.SimpleDateFormat;
import java.io.File;
 
public class PdfEx
{
   public static String unAccent(String s) 
   {
      String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
      Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
      return pattern.matcher(temp).replaceAll("");
   }
   public static void exportPDF(String mahd, String nv, String total, String [][] data, String tabNo)
   {
      System.out.println("xuat hoa don");
      Document document = new Document();
      try
      {
         String dir = "hoadon/hoadon" + new SimpleDateFormat("yyyy-MM").format(new Date());
         File theDir = new File(dir);
         if (!theDir.exists())
             theDir.mkdirs();
         String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
         String date = "Date: " + time;
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dir + "/id_" + data[0][0] + ".pdf"));
         document.open();
         Paragraph a = new Paragraph();
         document.add(new Paragraph("Bill code: " + unAccent(mahd), new Font(Font.FontFamily.TIMES_ROMAN, 15)));
         document.add(new Paragraph("Cashier: " + unAccent(nv), new Font(Font.FontFamily.TIMES_ROMAN, 15)));
         document.add(new Paragraph("Table No: " + unAccent(tabNo), new Font(Font.FontFamily.TIMES_ROMAN, 15)));
         document.add(new Paragraph(date, new Font(Font.FontFamily.TIMES_ROMAN, 15)));
         document.add(new Paragraph("Total: " + total + "VND", new Font(Font.FontFamily.TIMES_ROMAN, 15)));
         document.add(new Paragraph("  "));
         
         PdfPTable table = new PdfPTable(4);

         PdfPCell c1 = new PdfPCell(new Phrase("Dish"));
         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(c1);

         c1 = new PdfPCell(new Phrase("Detail"));
         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(c1);

         c1 = new PdfPCell(new Phrase("Quantity"));
         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(c1);

         c1 = new PdfPCell(new Phrase("Unit price"));
         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(c1);
         table.setHeaderRows(1);

         for (int i = 0; i < 1000; i++)
            if (!(data[i][0].equals("")))
               for (int j = 1; j < 5; j++)
                  table.addCell(unAccent(data[i][j]));
                           
         // Adding Table to document        
         document.add(table);   
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
   }
}