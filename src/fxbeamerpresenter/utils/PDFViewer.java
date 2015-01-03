/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxbeamerpresenter.utils;

import fxbeamerpresenter.model.PDFModel;
import java.io.IOException;

/**
 *
 * @author Ced
 */
public class PDFViewer {

	public PDFViewer() {

	}

	public void viewPDF(PDFModel pdfModel) {
		viewPDFUtils(pdfModel.getPdfPath(), pdfModel.getPage());
		viewPDFUtils(pdfModel.getNotesPath(), pdfModel.getPage());
	}

	private void viewPDFUtils(String path, int page) {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "sumatrapdf \"" + path + "\" -reuse-instance -page " + page);
		try {
			Process p = builder.start();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
