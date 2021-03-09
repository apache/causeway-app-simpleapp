package domainapp.webapp.util;

import org.approvaltests.core.Options;
import org.approvaltests.writers.ApprovalTextWriter;

public class CurrentVsApprovedApprovalTextWriter extends ApprovalTextWriter {

    public CurrentVsApprovedApprovalTextWriter(String received, String fileExtensionWithoutDot) {
        super(received, new Options().forFile()
                .withExtension(fileExtensionWithoutDot));
    }

}
