package org.vaadin.easyuploads;

import java.io.File;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.easyuploads.MultiUpload.FileDetail;
import org.vaadin.easyuploads.UploadField.FieldType;

import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.server.StreamVariable;
import com.vaadin.server.StreamVariable.StreamingEndEvent;
import com.vaadin.server.StreamVariable.StreamingErrorEvent;
import com.vaadin.server.StreamVariable.StreamingProgressEvent;
import com.vaadin.server.StreamVariable.StreamingStartEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.WrapperTransferable;
import com.vaadin.ui.Html5File;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;

/**
 * MultiFileUpload makes it easier to upload multiple files. MultiFileUpload
 * releases upload button for new uploads immediately when a file is selected
 * (aka parallel uploads). It also displays progress indicators for pending
 * uploads.
 * <p>
 * MultiFileUpload always streams straight to files to keep memory consumption
 * low. To temporary files by default, but this can be overridden with
 * {@link #setFileFactory(FileFactory)} (eg. straight to target directory on the
 * server).
 * <p>
 * Developer handles uploaded files by implementing the abstract
 * {@link #handleFile(File, String, String, long)} method.
 * <p>
 * TODO Field version (type == Collection<File> or File where isDirectory() ==
 * true).
 * <p>
 * TODO a super progress indicator (total transferred per total, including
 * queued files)
 * <p>
 * TODO Time remaining estimates and current transfer rate
 * 
 */
@SuppressWarnings("serial")
public abstract class MultiFileUpload extends CssLayout implements DropHandler {

    private CssLayout progressBars = new CssLayout();
    private CssLayout uploads = new CssLayout();
    private String uploadButtonCaption = "...";
    private int oldPollInterval;

    public MultiFileUpload() {
        setWidth("200px");
        addComponent(progressBars);
        uploads.setStyleName("v-multifileupload-uploads");
        addComponent(uploads);
        prepareUpload();
        oldPollInterval = UI.getCurrent().getPollInterval();
    }

    private void prepareUpload() {
        final FileBuffer receiver = createReceiver();

        final MultiUpload upload = new MultiUpload();
        MultiUploadHandler handler = new MultiUploadHandler() {
            private LinkedList<ProgressBar> indicators;

            @Override
            public void streamingStarted(StreamingStartEvent event) {
                UI.getCurrent().setPollInterval(300);
            }

            @Override
            public void streamingFinished(StreamingEndEvent event) {
                if (!indicators.isEmpty()) {
                    progressBars.removeComponent(indicators.remove(0));
                }
                File file = receiver.getFile();
                handleFile(file, event.getFileName(), event.getMimeType(),
                        event.getBytesReceived());
                receiver.setValue(null);
                UI.getCurrent().setPollInterval(oldPollInterval);
            }

            @Override
            public void streamingFailed(StreamingErrorEvent event) {
                Logger.getLogger(getClass().getName()).log(Level.FINE,
                        "Streaming failed", event.getException());

                for (ProgressBar progressIndicator : indicators) {
                    progressBars.removeComponent(progressIndicator);
                }
                UI.getCurrent().setPollInterval(oldPollInterval);
            }

            @Override
            public void onProgress(StreamingProgressEvent event) {
                long readBytes = event.getBytesReceived();
                long contentLength = event.getContentLength();
                float f = (float) readBytes / (float) contentLength;
                indicators.get(0).setValue(f);
            }

            @Override
            public OutputStream getOutputStream() {
                FileDetail next = upload.getPendingFileNames().iterator()
                        .next();
                return receiver.receiveUpload(next.getFileName(),
                        next.getMimeType());
            }

            @Override
            public void filesQueued(Collection<FileDetail> pendingFileNames) {
                if (indicators == null) {
                    indicators = new LinkedList<ProgressBar>();
                }
                for (FileDetail f : pendingFileNames) {
                    ProgressBar pi = createProgressIndicator();
                    progressBars.addComponent(pi);
                    pi.setCaption(f.getFileName());
                    pi.setVisible(true);
                    indicators.add(pi);
                }
            }

            @Override
            public boolean isInterrupted() {
                return false;

            }
        };
        upload.setHandler(handler);
        upload.setButtonCaption(getUploadButtonCaption());
        uploads.addComponent(upload);

    }

    private ProgressBar createProgressIndicator() {
        ProgressBar progressIndicator = new ProgressBar();
        progressIndicator.setValue(0f);
        return progressIndicator;
    }

    public String getUploadButtonCaption() {
        return uploadButtonCaption;
    }

    public void setUploadButtonCaption(String uploadButtonCaption) {
        this.uploadButtonCaption = uploadButtonCaption;
        Iterator<Component> componentIterator = uploads.iterator();
        while (componentIterator.hasNext()) {
            Component next = componentIterator.next();
            if (next instanceof MultiUpload) {
                MultiUpload upload = (MultiUpload) next;
                if (upload.isVisible()) {
                    upload.setButtonCaption(getUploadButtonCaption());
                }
            }
        }
    }

    private FileFactory fileFactory;

    public FileFactory getFileFactory() {
        if (fileFactory == null) {
            fileFactory = new TempFileFactory();
        }
        return fileFactory;
    }

    public void setFileFactory(FileFactory fileFactory) {
        this.fileFactory = fileFactory;
    }

    protected FileBuffer createReceiver() {
        FileBuffer receiver = new FileBuffer(FieldType.FILE) {
            @Override
            public FileFactory getFileFactory() {
                return MultiFileUpload.this.getFileFactory();
            }
        };
        return receiver;
    }

    protected int getPollinInterval() {
        return 300;
    }

    @Override
    public void attach() {
        super.attach();
        if (supportsFileDrops()) {
            prepareDropZone();
        }
    }

    private DragAndDropWrapper dropZone;

    /**
     * Sets up DragAndDropWrapper to accept multi file drops.
     */
    private void prepareDropZone() {
        if (dropZone == null) {
            Component label = new Label(getAreaText(), ContentMode.HTML);
            label.setSizeUndefined();
            dropZone = new DragAndDropWrapper(label);
            dropZone.setStyleName("v-multifileupload-dropzone");
            dropZone.setSizeUndefined();
            addComponent(dropZone, 1);
            dropZone.setDropHandler(this);
            addStyleName("no-horizontal-drag-hints");
            addStyleName("no-vertical-drag-hints");
        }
    }

    protected String getAreaText() {
        return "<small>DROP<br/>FILES</small>";
    }

    @SuppressWarnings("deprecation")
    protected boolean supportsFileDrops() {
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        if (browser.isChrome()) {
            return true;
        } else if (browser.isFirefox()) {
            return true;
        } else if (browser.isSafari()) {
            return true;
        }
        return false;
    }

    abstract protected void handleFile(File file, String fileName,
            String mimeType, long length);

    /**
     * A helper method to set DirectoryFileFactory with given pathname as
     * directory.
     * 
     * @param file
     */
    public void setRootDirectory(String directoryWhereToUpload) {
        setFileFactory(new DirectoryFileFactory(
                new File(directoryWhereToUpload)));
    }

    @Override
    public AcceptCriterion getAcceptCriterion() {
        // TODO accept only files
        // return new And(new TargetDetailIs("verticalLocation","MIDDLE"), new
        // TargetDetailIs("horizontalLoction", "MIDDLE"));
        return AcceptAll.get();
    }

    @Override
    public void drop(DragAndDropEvent event) {
        DragAndDropWrapper.WrapperTransferable transferable = (WrapperTransferable) event
                .getTransferable();
        Html5File[] files = transferable.getFiles();
        for (final Html5File html5File : files) {
            final ProgressBar pi = new ProgressBar();
            pi.setCaption(html5File.getFileName());
            progressBars.addComponent(pi);
            final FileBuffer receiver = createReceiver();
            html5File.setStreamVariable(new StreamVariable() {

                private String name;
                private String mime;

                @Override
                public OutputStream getOutputStream() {
                    return receiver.receiveUpload(name, mime);
                }

                @Override
                public boolean listenProgress() {
                    return true;
                }

                @Override
                public void onProgress(StreamingProgressEvent event) {
                    float p = (float) event.getBytesReceived()
                            / (float) event.getContentLength();
                    pi.setValue(p);
                }

                @Override
                public void streamingStarted(StreamingStartEvent event) {
                    name = event.getFileName();
                    mime = event.getMimeType();
                    UI.getCurrent().setPollInterval(300);
                }

                @Override
                public void streamingFinished(StreamingEndEvent event) {
                    progressBars.removeComponent(pi);
                    handleFile(receiver.getFile(), html5File.getFileName(),
                            html5File.getType(), html5File.getFileSize());
                    receiver.setValue(null);
                    UI.getCurrent().setPollInterval(oldPollInterval);
                }

                @Override
                public void streamingFailed(StreamingErrorEvent event) {
                    progressBars.removeComponent(pi);
                    UI.getCurrent().setPollInterval(oldPollInterval);
                }

                @Override
                public boolean isInterrupted() {
                    return false;
                }
            });
        }

    }
}
