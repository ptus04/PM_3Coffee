package coffee.shared.utility;

import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;

public class PrinterUtilities {
	private static PrinterUtilities instance;

	public static PrinterUtilities getInstance() {
		if (instance == null)
			instance = new PrinterUtilities();
		return instance;
	}

	public static final int NO_DIALOG = 0;
	public static final int PAGE_FORMAT_DIALOG_ONLY = 1;
	public static final int PRINT_DIALOG_ONLY = 2;
	public static final int BOTH_DIALOG = 3;

	public void print(Printable p, String jobName) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName(jobName == null ? "Không có tên" : jobName);
		printerJob.setPrintable(p);

		if (printerJob.printDialog()) {
			try {
				printerJob.print();
			} catch (PrinterException ex) {
				JOptionPane.showMessageDialog(null, "Không thể in: " + ex.getMessage(), "Lỗi in",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void print(Printable p, String jobName, int flag) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName(jobName == null ? "Không có tên" : jobName);
		printerJob.setPrintable(p);
		if (flag == PAGE_FORMAT_DIALOG_ONLY || flag == BOTH_DIALOG)
			printerJob.pageDialog(printerJob.defaultPage());

		if (flag == PRINT_DIALOG_ONLY || flag == BOTH_DIALOG || printerJob.printDialog()) {
			doPrint(printerJob);
		}

		if (flag == NO_DIALOG) {
			doPrint(printerJob);
		}
	}

	private void doPrint(PrinterJob printerJob) {
		try {
			printerJob.print();
		} catch (PrinterException ex) {
			JOptionPane.showMessageDialog(null, "Không thể in: " + ex.getMessage(), "Lỗi in",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
