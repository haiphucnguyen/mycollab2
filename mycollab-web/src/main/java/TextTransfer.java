import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Base64;

import javax.imageio.ImageIO;

public final class TextTransfer implements ClipboardOwner {

    public static void main(String... aArguments) throws IOException {
        TextTransfer textTransfer = new TextTransfer();

        String content = textTransfer.getClipboardContents();
        LineNumberReader reader = new LineNumberReader(
                new StringReader(content));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            String[] split = line.split("\t");
            System.out.println("Line: " + split[0]);
            String orderId = split[1].trim();
            System.out.println("AAA: " + split[2].trim());
            String image = split[2].trim().substring(
                    "data:image/png;base64,".length());
            System.out.println("Image: " + image);
            byte[] decode = Base64.getDecoder().decode(image);
            BufferedImage imageBuffered = ImageIO
                    .read(new ByteArrayInputStream(decode));
            ImageIO.write(imageBuffered, "png", new FileOutputStream(
                    "/Users/baohan/Downloads/" + orderId + ".png"));
        }
    }

    /**
     * Empty implementation of the ClipboardOwner interface.
     */
    @Override
    public void lostOwnership(Clipboard aClipboard, Transferable aContents) {
        // do nothing
    }

    /**
     * Place a String on the clipboard, and make this class the owner of the
     * Clipboard's contents.
     */
    public void setClipboardContents(String aString) {
        StringSelection stringSelection = new StringSelection(aString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, this);
    }

    /**
     * Get the String residing on the clipboard.
     *
     * @return any text found on the Clipboard; if none found, return an empty
     *         String.
     */
    public String getClipboardContents() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null)
                && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String) contents
                        .getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return result;
    }
}