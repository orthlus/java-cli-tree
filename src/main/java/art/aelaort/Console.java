package art.aelaort;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.stream.Stream;

import static art.aelaort.Utils.log;
import static java.lang.System.getProperty;
import static java.nio.file.Path.of;

@Component
public class Console implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		Path path = getPath(args);
		try (Stream<Path> walk = Files.walk(path)) {
			walk
					.map(Path::toString)
					.map(s -> s.replace(path.getParent().toString(), ""))
					.map(s -> s.replaceAll("^\\\\", ""))
					.forEach(Utils::log);
		} catch (NoSuchFileException e) {
			log("not found path %s\n", path);
		}

	}

	private Path getPath(String[] args) {
		if (args.length > 0) {
			Path arg = of(args[0].trim());
			if (arg.isAbsolute()) {
				return arg;
			} else {
				return of(getProperty("user.dir")).resolve(arg);
			}
		} else {
			return of(getProperty("user.dir"));
		}
	}
}
