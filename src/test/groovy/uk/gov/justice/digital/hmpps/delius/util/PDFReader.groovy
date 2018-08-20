package uk.gov.justice.digital.hmpps.delius.util

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper


class PDFReader {
    static def textContent(data) {
        def document = toDocument(data)

        try {
            def reader = new PDFTextStripper()
            reader.getText document
        } finally {
            document.close()
        }
    }

    static def toDocument(byteData) {
        PDDocument.load(new ByteArrayInputStream(byteData))
    }

}
