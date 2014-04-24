package io.zhpooer.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

public class DbUtil {

	public static String makeDerbyTempURL(String db) {
		Path p = makeTmpDir();
		return "jdbc:derby:" + p.toUri().getPath() + "/" + db + ";create=true";
	}

	public static String makeH2TempURL(String db) {
		Path p = makeTmpDir();
		return "jdbc:h2:" + p.toUri().getPath() + ":" + db;
	}

	private static Path makeTmpDir() {
		Path path = new File("/tmp").toPath();
		Path p = null;
		try {
			p = Files.createTempDirectory(path, "derby");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return p;
	}

	public static void execSQL(String scriptPath, String driveClass,
	        String url, String user, String passwd) {
		SQLExec sqlExec = new SQLExec();
		sqlExec.setDriver(driveClass);
		sqlExec.setUrl(url);
		sqlExec.setUserid(user);
		sqlExec.setPassword(passwd);
		sqlExec.setSrc(new File(scriptPath));
		sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(
		        SQLExec.OnError.class, "abort")));
		sqlExec.setPrint(true);
		sqlExec.setProject(new Project());
		sqlExec.execute();
	}
}
