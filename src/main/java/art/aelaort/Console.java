package art.aelaort;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

import static art.aelaort.Utils.log;

@Component
public class Console implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		String path = getPath(args);
		log("path: %s", path);
	}

	private String getPath(String[] args) {
		if (args.length > 0) {
			String arg = args[0];
			if (Path.of(arg).isAbsolute()) {
				return arg;
			} else {
				return Path.of(System.getProperty("user.dir")).resolve(arg).toString();
			}
		} else {
			return System.getProperty("user.dir");
		}
	}
}
