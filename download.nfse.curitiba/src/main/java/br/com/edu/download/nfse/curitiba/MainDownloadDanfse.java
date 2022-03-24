package br.com.edu.download.nfse.curitiba;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;

import javax.imageio.ImageIO;

import br.com.edu.download.nfse.curitiba.service.DanfseDownloadService;
import br.com.edu.download.nfse.curitiba.service.dto.RequestFilter;

/**
 * Realiza o download de NFSe de curitiba.
 *
 * @author Eduardo
 */
public class MainDownloadDanfse {

	public static void main(final String[] args) throws Exception {

		final DanfseDownloadService business = new DanfseDownloadService();
		final byte[] base64 = business.download(RequestFilter.getDefault());

		final BufferedImage image = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64)));

		final File file = new File("src/main/resources/out.png");
		ImageIO.write(image, "png", file);

		System.out.println("Arquivo salvo!");
	}

}
