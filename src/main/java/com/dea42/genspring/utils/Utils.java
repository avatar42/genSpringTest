package com.dea42.genspring.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Utils <br>
 * Description: General utils used by app from GenSpring static resources. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 * @author Gened by GenSpring version 0.7.1<br>
 * @version 0.7.1<br>
 */
@Slf4j
public class Utils {

	/**
	 * Checks if Ajax request
	 * 
	 * @param requestedWith
	 * @return
	 */
	public static boolean isAjaxRequest(String requestedWith) {
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	/**
	 * 
	 * @param bundle
	 * @param key
	 * @return returns bundle value or null if not found
	 */
	public static String getProp(ResourceBundle bundle, String key) {
		return getProp(bundle, key, null);
	}

	/**
	 * Get a comma separated property as a List<String>
	 * 
	 * @param bundle
	 * @param key
	 * @return
	 */
	public static List<String> getPropList(ResourceBundle bundle, String key) {
		String s = getProp(bundle, key, null);
		if (StringUtils.isBlank(s))
			return null;

		return Arrays.asList(s.split("\\s*,\\s*"));
	}

	/**
	 * 
	 * @param bundle
	 * @param key
	 * @param defaultVal
	 * @return returns bundle value or defaultVal if not found
	 */
	public static String getProp(ResourceBundle bundle, String key, String defaultVal, String... args) {
		try {
			String val = bundle.getString(key);
			for (int i = 0; i < args.length; i++) {
				String argkey = "%" + (i + 1) + "$s";
				val = val.replace(argkey, args[i]);
			}
			return new String(val.getBytes("ISO-8859-1"), "UTF-8");
		} catch (MissingResourceException | UnsupportedEncodingException e) {
			log.warn(key + " undefined in " + bundle.getBaseBundleName() + " using " + defaultVal);
		}

		return defaultVal;
	}

	public static int getProp(ResourceBundle bundle, String key, int defaultVal) {
		try {
			return Integer.parseInt(bundle.getString(key));
		} catch (MissingResourceException e) {
			log.warn(key + " undefined in " + bundle.getBaseBundleName() + " using " + defaultVal);
		}

		return defaultVal;
	}

	public static long getProp(ResourceBundle bundle, String key, long defaultVal) {
		try {
			return Long.parseLong(bundle.getString(key));
		} catch (MissingResourceException e) {
			log.warn(key + " undefined in " + bundle.getBaseBundleName() + " using " + defaultVal);
		}

		return defaultVal;
	}

	public static boolean getProp(ResourceBundle bundle, String key, boolean defaultVal) {
		try {
			return Boolean.parseBoolean(bundle.getString(key));
		} catch (MissingResourceException e) {
			log.warn(key + " undefined in " + bundle.getBaseBundleName() + " using " + defaultVal);
		}

		return defaultVal;
	}
	
	/**
	 * Returns a path that is this path with redundant name elements eliminated.
	 * Adds "../" to path if currently in target folder to deal with diffs in
	 * running from direct and as embedded maven install test
	 * 
	 * @param path the path string or initial part of the path string
	 * @param more additional strings to be joined to form the path string
	 * @return the resulting path or this path if it does not contain redundant name
	 *         elements; an empty path is returned if this path does have a root
	 *         component and all name elements are redundant
	 * 
	 */
	public static Path getPath(final String path, final String... more) {
		// toAbsolutePath() required for getParent() to work
		Path cwd = Paths.get(".").toAbsolutePath();
		String tpath = path.replace('\\', '/');
		// check for in target folder. If so make rel to parent
		if (cwd.getParent().endsWith("target")) {
			tpath = cwd.getParent().getParent() + "/" + path.replace('\\', '/');
		}

		return Paths.get(tpath, more).toAbsolutePath().normalize();
	}

	/**
	 * Creates all the parent folders as needed and an empty file. It returns null
	 * if the old file exists and deleteFirst is false.
	 * 
	 * @param baseDir     path of folder to use as root for relPath. If ends in
	 *                    target then target is removed.
	 * @param relPath     path of file to create
	 * @param deleteFirst delete file first
	 * @return
	 */
	public static Path createFile(String baseDir, String relPath, boolean deleteFirst) {
		Path p = getPath(baseDir, relPath);
		if (deleteFirst && p.toFile().exists()) {
			p.toFile().delete();
		}
		try {
			Files.createDirectories(p.getParent());
			p = Files.createFile(p);
		} catch (IOException e) {
			log.warn(e.getMessage() + " exists will skip");
			return null;
		}

		return p;
	}

	/**
	 * Creates all the parent folders as needed and an empty file. It returns null
	 * if the old file exists.
	 * 
	 * @param baseDir path of folder to use as root for relPath. If ends in target
	 *                then target is removed.
	 * @param relPath path of file to create
	 * @return
	 */
	public static Path createFile(String baseDir, String relPath) {
		return createFile(baseDir, relPath, false);
	}

	/**
	 * Delete the path
	 * 
	 * @param path
	 * @throws IOException
	 */
	public static void deletePath(Path path) throws IOException {
		if (path.toFile().exists()) {
			if (path.toFile().isDirectory()) {
				Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						if (!file.endsWith(".sqlite")) {
							log.debug("Deleting file:" + file);
							Files.delete(file);
						}
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
						// try to delete the file anyway, even if its attributes
						// could not be read, since delete-only access is
						// theoretically possible
						if (file.toFile().exists()) {
							log.debug("Deleting file again:" + file);
							Files.delete(file);
						}
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
						if (exc == null) {
							if (!path.equals(dir)) {
								log.debug("Deleting dir:" + dir);
								try {
									Files.delete(dir);
								} catch (IOException e) {
									// see an occasional race condition so give second try after a sec
									try {
										Thread.sleep(100);
									} catch (InterruptedException e1) {
										// ignored
									}
									Files.delete(dir);
								}
							}
							return FileVisitResult.CONTINUE;
						} else {
							// directory iteration failed; propagate exception
							// throw exc;
							// Windows seems to be slow noticing we've removed everything from the folder
							log.error("Ignoring failed to delete folder", exc);
							return FileVisitResult.CONTINUE;
						}
					}
				});
			}
			Files.delete(path);
		}
		if (path.toFile().exists()) {
			throw new IOException(path + " still exists");
		} else {
			log.debug("Deleted:" + path.toAbsolutePath());
		}
	}

}
