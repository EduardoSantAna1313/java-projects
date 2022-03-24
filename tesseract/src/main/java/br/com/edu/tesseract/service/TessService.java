package br.com.edu.tesseract.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.recognition.software.jdeskew.ImageDeskew;

import br.com.edu.tesseract.util.Gaussian;
import br.com.edu.tesseract.util.ImageUtils;
import br.com.edu.tesseract.util.Otsu;
import net.sourceforge.tess4j.ITessAPI.TessPageSegMode;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.ImageHelper;

/**
 * Class to Tesseract Service.
 *
 * @author Eduardo
 */
public class TessService {

	public static final String LANGUAGE_POR = "por";

	private static final float IMAGE_RESOLUTION_DPI = 300;

	private boolean hocr = false;

	private final Path path;

	private boolean showForm = false;

	private boolean preserveSpaces = false;

	private boolean deskew = false;

	private boolean applyThreshold = false;

	public TessService(final Path path) {
		super();
		this.path = path;
	}

	public TessService showForm(final boolean showForm) {
		this.showForm = showForm;
		return this;
	}

	public TessService deskew(final boolean deskew) {
		this.deskew = deskew;
		return this;
	}

	/**
	 * Salva a imagem como html.
	 *
	 * @param hocr
	 *
	 * @return
	 */
	public TessService hocr(final boolean hocr) {
		this.hocr = hocr;
		return this;
	}

	/**
	 * Configura o ocr para manter a formatação original do arquivos inserindo
	 * spaços entre as palavras
	 *
	 * @param preserveSpaces
	 *
	 * @return
	 */
	public TessService preserveSpaces(final boolean preserveSpaces) {
		this.preserveSpaces = preserveSpaces;
		return this;
	}

	/**
	 * Aplica o threshold.
	 *
	 * @param applyThreshold
	 *
	 * @return
	 */
	public TessService applyThreshold(final boolean applyThreshold) {
		this.applyThreshold = applyThreshold;
		return this;
	}

	public String doOcr() throws Exception {
		final File imageFile = path.toFile();

		final BufferedImage original;

		if (imageFile.getName().endsWith(".pdf")) {
			original = convert(imageFile);
		} else {
			original = ImageIO.read(imageFile);
		}

		if (showForm) {
			ImageUtils.showImage(original);
		}

		BufferedImage corrigida = original;

		if (deskew) {
			final ImageDeskew id = new ImageDeskew(corrigida);

			final double imageSkewAngle = id.getSkewAngle();

			corrigida = ImageHelper.rotateImage(original, -imageSkewAngle);
		}

		if (applyThreshold) {
			corrigida = applyThreshold(corrigida);
			ImageIO.write(corrigida, "png", new File("src/main/resources/out.png"));
		}

		if (showForm) {
			ImageUtils.showImage(corrigida);
		}

		final Tesseract tess = new Tesseract();
		tess.setDatapath("src/main/resources");
		tess.setLanguage(LANGUAGE_POR);

		if (hocr) {
			tess.setTessVariable("tessedit_create_hocr", "1");
			tess.setTessVariable("hocr_font_info", "0");
			return tess.doOCR(corrigida);
		} else {

			if (preserveSpaces) {
				tess.setTessVariable("preserve_interword_spaces", "1");
			}

			tess.setPageSegMode(TessPageSegMode.PSM_SINGLE_BLOCK);
			return tess.doOCR(corrigida);
		}

	}

	private static BufferedImage applyThreshold(final BufferedImage corrigida) {
		final int[][] pixels = ImageUtils.gsArray(corrigida);

		// calculate the Threshold
		final Otsu otsu = new Otsu(pixels);
		final int threshold = otsu.getThreshold() - 30;

		final Gaussian gaussian = new Gaussian(pixels);
		final int[][] blurred = gaussian.blur(Gaussian.GAUSSIAN_RADIUS, Gaussian.GAUSSIAN_INTENSITY);

		return ImageUtils.applyThreshold(blurred, threshold, true);
	}

	private static BufferedImage convert(final File file) {

		try (final PDDocument document = PDDocument.load(Files.readAllBytes(file.toPath()))) {

			final PDFRenderer pdfRenderer = new PDFRenderer(document);

			return pdfRenderer.renderImageWithDPI(0, IMAGE_RESOLUTION_DPI, ImageType.RGB);

		} catch (final Exception error) {
			error.printStackTrace();
		}

		return null;
	}

}
