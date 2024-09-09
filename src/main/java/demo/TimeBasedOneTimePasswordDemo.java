package demo;

import java.io.IOException;
import java.net.URISyntaxException;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;

public class TimeBasedOneTimePasswordDemo {

	public static void main(String[] args) throws IOException, URISyntaxException {
		TimeProvider timeProvider = new SystemTimeProvider();
		int timePeriod = 30;
		long currentBucket = Math.floorDiv(timeProvider.getTime(), timePeriod);

		String secret = null;
		SecretGenerator secretGenerator = new DefaultSecretGenerator();
		secret = secretGenerator.generate();
		secret = "WQKTNTS4QQTUSUFASVK6X44NBTRZCQBA";
		System.out.println(secret);

		CodeGenerator codeGenerator = new DefaultCodeGenerator();
		CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);

		String actualCode = null;
		try {
			actualCode = codeGenerator.generate(secret, currentBucket);
			System.out.println(actualCode);
		} catch (CodeGenerationException e) {
			e.printStackTrace();
		}
		
		boolean successful = verifier.isValidCode(secret, actualCode);
		System.out.println(successful);
		
		actualCode = "758775";
		System.out.println(actualCode);
		successful = verifier.isValidCode(secret, actualCode);
		System.out.println(successful);
	}
}
