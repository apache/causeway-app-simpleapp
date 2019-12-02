package domainapp.webapp.util;

import java.io.File;

import org.approvaltests.writers.ApprovalTextWriter;

public class ReceivedVsApprovedApprovalTextWriter extends ApprovalTextWriter {

    private final String fileExtensionWithoutDot;

    public ReceivedVsApprovedApprovalTextWriter(String received, String fileExtensionWithoutDot) {
        super(received, fileExtensionWithoutDot);
        this.fileExtensionWithoutDot = fileExtensionWithoutDot;
    }

    @Override
    public String getReceivedFilename(final String base) {
        return toFilename("received", base);
    }

    @Override
    public String getApprovalFilename(final String base) {
        return toFilename("approved", base);
    }

    private String toFilename(final String prefix, final String base) {
        final File file = new File(base);
        final File parentFile = file.getParentFile();
        final String localName = file.getName();
        final File newDir = new File(parentFile, prefix);
        final File newFile = new File(newDir, localName + "." + fileExtensionWithoutDot);
        return newFile.toString();
    }

}
