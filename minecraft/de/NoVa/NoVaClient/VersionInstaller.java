package de.NoVa.NoVaClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.NoVa.NoVaClient.util.ExceptionHandler;
import de.Autoupdater.au.FileLoader;
import net.minecraft.client.Minecraft;

public class VersionInstaller {
	public static void install() {
		File mcDir = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().substring(
				0, Minecraft.getMinecraft().mcDataDir.getAbsolutePath().length()-2));
		File versionDir = new File(mcDir + "/versions");
		File medaVersionDir = new File(versionDir+"/NoVaClient"+FileLoader.loadFile(mcDir+"/NoVaUpdates/olVersion.txt").replace('.', '0'));
		File updateDir = new File(mcDir, "NoVaUpdates");
		
		if (!medaVersionDir.isDirectory()) {
			try {
				System.out.println(medaVersionDir.mkdir());
				Files.copy(new File(updateDir.getAbsolutePath()+"/NoVaClient.jar").toPath(), new File(medaVersionDir.getAbsolutePath(),"MedaClient.jar").toPath());
			} catch (IOException e) {
				ExceptionHandler.getHandler().reportException(e, "An error occured whilst Updating NoVaClient: IOException");
			}
		} else {
			return;
		}
	}
}
