package util;

import java.security.cert.X509Certificate;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

public class SelfSignedCertificateGeneration {

	public static void genCerteficate() {
		try {
			CertAndKeyGen keyGen = new CertAndKeyGen("RSA", Const.SHA256_with_RSA, null);
			keyGen.generate(1024);
			// Generate self signed certificate
			X509Certificate[] chain = new X509Certificate[1];
			chain[0] = keyGen.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);

			System.out.println("Certificate : " + chain[0].toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}