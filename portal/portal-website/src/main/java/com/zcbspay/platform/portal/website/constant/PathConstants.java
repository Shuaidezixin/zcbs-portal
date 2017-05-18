package com.zcbspay.platform.portal.website.constant;

import java.io.File;

public class PathConstants {
	public final static String DIR = PathConstants.class.getClassLoader().getResource("path").getPath();
	public final static String portalConfig = DIR + File.separator + "portal.xml";
}
