/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.doxa.setrucdownloader;


import com.doxa.setrucdownloader.util.Scrap;
import com.doxa.setrucdownloader.util.Unzip;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author BlackSpider
 */
public class main {

    /**
     * @param args the command line arguments
     */
    
   
    public static void main(String[] args) throws IOException {
        String url = "https://www.set.gov.py/portal/PARAGUAY-SET/detail?folder-id=repository:collaboration:/sites/PARAGUAY-SET/categories/SET/Informes%20Periodicos/listado-de-ruc-con-sus-equivalencias&content-id=/repository/collaboration/sites/PARAGUAY-SET/documents/informes-periodicos/ruc/";
        String link = "https://www.set.gov.py";
        Scrap s = new Scrap();
        Unzip unzip = new Unzip();
        
        Logger logger = Logger.getLogger("setrucdownloader");
        FileHandler fh = new FileHandler("setrucdownloader.log", true);
        logger.addHandler(fh);

        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

        logger.info("############# Iniciando descarga de archivos #############");
        
        for (int i = 0; i < 10; i++) {
            
            String urlAux = url+"ruc"+i+".zip"; 
            
        

            if (s.getStatusConnectionCode(urlAux) == 200) {

                Document document = s.getHtmlDocument(urlAux);
                Elements elements = document.getElementsByClass("btn btn-primary");
            
                //System.out.println("Elementos encontrados = "+elements.size());

                for (Element elem2 : elements) {

                    String d = link+elem2.attr("href");
                    
                    logger.info(d);
                    if (args.length > 0){
                         unzip.unzip(d,args[0]);
                    }else{
                         unzip.unzip(d,"datos/");
                    }
                   
                }

            }

        }

    }

   

}
