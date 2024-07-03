/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemoney;

import com.Models.ClientCompte;
import com.Models.Transaction;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author macbookpro
 */
public class PdfGenerator {

    public PdfGenerator() {
        super();
    }

    public void generePDF(List<Transaction> transactions, ClientCompte cli) {
        String dest = "/Users/Bienvenu/Desktop/Pdf/transactions" + cli.getComptes().getNumero()+"numero.pdf";// Fetch the list of transactions

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();
            LocalDate currentDate = LocalDate.now();
            Month currentMonth = currentDate.getMonth();
            int currentYear = currentDate.getYear();
            // Add title and client information
            document.add(new Paragraph("Date: " + currentMonth + " " + currentYear, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            document.add(new Paragraph("Contact: " + cli.getComptes().getNumero()));
            document.add(new Paragraph(cli.getClient().getNoms()));
            document.add(new Paragraph(cli.getClient().getAge() + " ans"));
            document.add(new Paragraph(cli.getClient().getSexe()));
            document.add(new Paragraph("Solde actuel : " + cli.getComptes().getSolde() + " Ariary"));

            // Add a table for the transactions
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            addTableHeader(table);

            // Add transaction data
            for (Transaction transaction : transactions) {
                addRows(table, transaction, cli.getComptes().getNumero());
            }

            // Add total debit and credit
            addTotalRows(table, transactions, cli.getComptes().getNumero());

            document.add(table);
            document.close();
            System.out.println("PDF created successfully!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table) {

        Stream.of("Date", "Raison", "Crédit", "Débit")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, Transaction transaction, String numero) {
        table.addCell(transaction.getDate().toString());
        table.addCell(transaction.getRaison());
        table.addCell(transaction.getNumRecepteur() == null || transaction.getNumEnvoyeur().equals(numero) ? "" : String.valueOf(transaction.getMontant()));
        table.addCell(transaction.getNumRecepteur() == null || transaction.getNumEnvoyeur().equals(numero) ? String.valueOf(transaction.getMontant()) : "");
    }

    private static void addTotalRows(PdfPTable table, List<Transaction> transactions, String numero) {

        //double totalCredit = transactions.stream().filter(t -> t.getNumRecepteur() == null || t.getNumEnvoyeur().equals(numero)).mapToDouble(Transaction::getMontant).sum();
        //double totalDebit = transactions.stream().filter(t -> t.getNumRecepteur().equals(numero)).mapToDouble(Transaction::getMontant).sum();
        double totalCredit = transactions.stream()
                .filter(t -> t.getNumRecepteur() == null || t.getNumEnvoyeur().equals(numero))
                .mapToDouble(Transaction::getMontant)
                .sum();
        double totalDebit = transactions.stream()
                .filter(t -> numero.equals(t.getNumRecepteur()))
                .mapToDouble(Transaction::getMontant)
                .sum();
        PdfPCell cell = new PdfPCell(new Phrase("Total Crédit"));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell(String.valueOf(totalDebit));
        table.addCell("");

        cell = new PdfPCell(new Phrase("Total Débit"));
        cell.setColspan(3);
        table.addCell(cell);
        table.addCell(String.valueOf(totalCredit));
    }
}
