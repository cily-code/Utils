package com.cily.utils.base.log;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.file.FileType;
import com.cily.utils.base.file.FileUtils;
import com.cily.utils.base.io.StreamToStr;

public class Logs {
	private static int level = LogType.ALL;
	private static boolean writeLog = false;
	private static boolean consoleLog = true;
	
	public static void sysOut(String msg){
		if(getLevel() >= LogType.DEBUG && msg != null){
			if (consoleLog) {
				System.out.println(msg);
			}
			writeLog(LogType.TYPE_DEBUG, LogType.TAG_SYS_OUT, msg);
		}
	}
	
	public static void printException(Throwable e){
		if(getLevel() >= LogType.EXCEPTION && e != null){
			if (consoleLog) {
				e.printStackTrace();
			}
			writeLog(LogType.TYPE_EXCEPTION, LogType.TAG_THROWABLE, StreamToStr.throwableToStr(e));
		}
	}

	public static void sysErr(String msg){
		if (getLevel() >= LogType.ERROR && msg != null){
			if (consoleLog) {
				System.err.println(msg);
			}
			writeLog(LogType.TYPE_ERROR, LogType.TAG_SYS_ERR, msg);
		}
	}
	
	public static int getLevel() {
		return level;
	}

	public static void setLevel(int l) {
		if(l < LogType.NONE){
			l = LogType.NONE;
		}else if(l > LogType.ALL){
			l = LogType.ALL;
		}
	}

	public static boolean isWriteLog(){
		return writeLog;
	}

	public static void setConsoleLog(boolean c){
		consoleLog = c;
	}
	public static boolean isConsoleLog(){
		return consoleLog;
	}

	protected static void writeLog(String logType, String tag, String msg){
		if (!isWriteLog()){
			return;
		}

		if (StrUtils.isEmpty(filePath)){
			return;
		}
		if (!FileUtils.fileExist(filePath)){
			return;
		}

		try{
			LogFileUtils.getInstance().init(filePath, fileName, FileType.TXT);
			LogFileUtils.getInstance().saveLog(FileUtils.formcatLog(logType, tag, msg));
		}catch (RuntimeException e){
			printException(e);
		}
	}

	public static void stop(){
		LogFileUtils.getInstance().stop();
	}

	private static String filePath, fileName;
	public static void setLogFile(boolean w, String filePath, String fileName){
		Logs.writeLog = w;
		Logs.filePath = filePath;
		Logs.fileName = fileName;
	}

	protected static String appName, appSign, appVersion, sysVersion, imei, deviceBrand, sysModel, sysSDK;
	//sysModel手机型号，deviceBrand厂商，sysVersion系统版本，sdk版本
	public static void setSysInfo(String appName, String appSign, String appVersion, String sysVersion,
								  String imei, String deviceBrand, String sysModel, String sdk){
		Logs.appName = appName;
		Logs.appSign = appSign;
		Logs.appVersion = appVersion;
		Logs.sysVersion = sysVersion;
		Logs.imei = imei;
		Logs.deviceBrand = deviceBrand;
		Logs.sysModel = sysModel;
		Logs.sysSDK = sdk;
	}
}