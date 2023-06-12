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
    
	public static void descargar(String[] arg) throws Exception {

		String directorio = "./datos";
		if (arg != null && arg.length > 0) {
			directorio = arg[0];
		}

		String url = "https://www.set.gov.py/web/portal-institucional/listado-de-ruc-con-sus-equivalencias";
		String link = "https://www.set.gov.py";
		
		Scrap s = new Scrap();
		Unzip unzip = new Unzip();

		Logger logger = Logger.getLogger("setrucdownloader");
		FileHandler fh = new FileHandler("setrucdownloader.log", true);
		logger.addHandler(fh);

		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);

		logger.info("############# Iniciando descarga de archivos #############");

		if (s.getStatusConnectionCode(url) == 200) {

			Document document = s.getHtmlDocument(url);
			Elements elements = document.getElementsByClass("link");

			logger.info("     Links encontrados = " + elements.size());
			for (Element elem2 : elements) {

				String d = link + elem2.attr("href");
				logger.info("     " + d);
				unzip.unzip(d, directorio);
			}
		}else {
			logger.info("Error al descargar");
			logger.info("Status != 200: " + url);
		}
	}

	public static void descargar_OLD(String[] args) throws Exception {
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

			String urlAux = url + "ruc" + i + ".zip";

			System.out.println(urlAux);

			if (s.getStatusConnectionCode(urlAux) == 200) {

				Document document = s.getHtmlDocument(urlAux);
				Elements elements = document.getElementsByClass("btn btn-primary");
				for (Element elem2 : elements) {
					String d = link + elem2.attr("href");
					logger.info(d);
					if (args.length > 0) {
						unzip.unzip(d, args[0]);
					} else {
						unzip.unzip(d, "datos/");
					}
				}
			} else {
				System.out.println("     Status <> 200");
			}
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception {

		// descargar_OLD(args);
		descargar(args);

	}

}
