package com.zcbspay.platform.portal.website.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.portal.common.utils.DateUtil;
import com.zcbspay.platform.portal.common.utils.FtpUtil;
import com.zcbspay.platform.portal.common.utils.excel.ExcelUtil;
import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;
import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.website.constant.Constants;
import com.zcbspay.platform.portal.website.enums.FormTypeEnum;
import com.zcbspay.platform.portal.website.util.ConfigParams;
import com.zcbspay.platform.portal.website.util.HttpRequestParam;
import com.zcbspay.platform.portal.website.util.HttpUtils;
import com.zcbspay.platform.portal.website.util.UserHelper;

import net.sf.json.JSONObject;

/**
 * 交易报表模块
 * 
 * @author: zhangshd
 * @date: 2017年5月2日 下午2:36:04
 * @version :v1.0
 */
@Controller
@RequestMapping("/queryAndStat")
@SuppressWarnings("all")
public class QueryAndStatController {

	//@Autowired
	//private QueryAndStatService queryAndStatService;

	@Autowired
	private ConfigParams configParams;
	/**
	 * 对账单报表
	 * 
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年5月5日 上午10:38:23
	 * @version v1.0
	 */
	@RequestMapping("showBill")
	public String showBill() {
		return "tradeForm/bill";
	}

	/**
	 * 结算单报表
	 * 
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年5月5日 上午10:38:53
	 * @version v1.0
	 */
	@RequestMapping("showSetl")
	public String showSetl() {
		return "tradeForm/setl";
	}

	/**
	 * 交易明细报表
	 * 
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年5月5日 上午10:39:18
	 * @version v1.0
	 */
	@RequestMapping("showTradeDetail")
	public String showTradeDetail() {
		return "tradeForm/tradeDetail";
	}

	/**
	 * 交易汇总报表
	 * 
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年5月5日 上午10:40:33
	 * @version v1.0
	 */
	@RequestMapping("showTradeStat")
	public String showBusiRate() {
		return "tradeForm/tradeStat";
	}

	// 交易明细报表 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_txns_deta
	/**
	 * 1.交易明细报表查询
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsDeta")
	public String txnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		String url = configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsDeta");//"http://localhost:9911/fe/queryAndStat/txnsDeta";//
		return JSONObject.fromObject(excuteHttp(page, rows, txnsForPortalBean, request, url)).toString();
	}

	/**
	 * 2.交易明细生成excel报表文件放入ftp
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsDetaExcelForms")
	public Map<String, Object> txnsDetaExcelForms(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		String url =configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsDeta");// "http://localhost:9911/fe/queryAndStat/txnsDeta";//queryAndStat.txnsDeta
		String[] headers = { "MERCHNAME", "REMARKS", "BUSICODE", "TXNDATE", "RETINFO", "RN", "STATUS", "BUSINAME",
				"COMMITIME", "TXNSEQNO", "RETCODE", "RETTIME", "ORDERID", "TXNAMT", "NOTES" };
		return createExcel("deta", txnsForPortalBean, request, url, headers);
	}

	/**
	 * 3.交易明细生成Txt报表文件放入ftp
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsDetaTxtForms")
	public Map<String, Object> txnsDetaTxtForms(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		String url = configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsDeta");//"http://localhost:9911/fe/queryAndStat/txnsDeta";//queryAndStat.txnsDeta
		return createTxt("deta", request, txnsForPortalBean, url);
	}

	// 交易汇总报表 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_txns_stat
	/**
	 * 1.交易汇总报表 报表预查询功能
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsStat")
	public String txnsStat(String page, String rows, TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		String url = configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsStat");//"http://localhost:9911/fe/queryAndStat/txnsStat";//
		return JSONObject.fromObject(excuteHttp(page, rows, txnsForPortalBean, request, url)).toString();
	}

	/**
	 * 2.交易汇总报表生成excel报表文件放入ftp
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsStatExcelForms")
	public Map<String, Object> txnsStatExcelForms(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		String url =configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsStat");// "http://localhost:9911/fe/queryAndStat/txnsStat";//queryAndStat.txnsStat
		String[] headers = { "CANCELFAILNUM", "MERCHNAME", "ALLNUM", "REMARKS", "MERID", "CANCELNUM", "RN", "SUCCNUM",
				"BUSINAME", "CANCELFAILAMT", "CANCELSUCCNUM", "CYCEL", "CANCELSUCCAMT", "SUCCAMT", "FIALAMT", "FAILNUM",
				"NOTES" };
		return createExcel("stat", txnsForPortalBean, request, url, headers);
	}

	/**
	 * 3.交易汇总报表功能生成Txt报表文件放入ftp
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsStatTxtForms")
	public Map<String, Object> txnsStatTxtForms(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		String url =configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsStat");// "http://localhost:9911/fe/queryAndStat/txnsStat";//queryAndStat.txnsStat
		return createTxt("stat", request, txnsForPortalBean, url);
	}

	// 结算单报表 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_setl
	/**
	 * 1.结算单报表 报表预查询功能
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsSetl")
	public String txnsSetl(String page, String rows, TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		String url =configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsSetl");// "http://localhost:9911/fe/queryAndStat/txnsSetl";//
		return JSONObject.fromObject(excuteHttp(page, rows, txnsForPortalBean, request, url)).toString();
	}

	/**
	 * 2.结算单报表生成excel报表文件放入ftp
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsSetlExcelForms")
	public Map<String, Object> txnsSetlExcelForms(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		String url =configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsSetl");// "http://localhost:9911/fe/queryAndStat/txnsSetl";//queryAndStat.txnsSetl
		String[] headers = { "MERCHNAME", "ALLNUM", "CANCELAMT", "REFUNDNUM", "REMARKS", "MERID", "CANCELNUM", "FEES",
				"STIME", "ALLAMT", "SUCCNUM", "REFUNDAMT", "ETIME", "SUCCAMT", "SETLAMT", "ROWNUM", "NOTES" };
		return createExcel("setl", txnsForPortalBean, request, url, headers);
	}

	/**
	 * 3.结算单报表功能生成Txt报表文件放入ftp
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsSetlTxtForms")
	public Map<String, Object> txnsSetlTxtForms(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		String url =configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsSetl");// "http://localhost:9911/fe/queryAndStat/txnsSetl";//queryAndStat.txnsSetl
		return createTxt("setl", request, txnsForPortalBean, url);
	}

	// 对账单 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_bill
	/**
	 * 1.结算单报表 报表预查询功能
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsBill")
	public String txnsBill(String page, String rows, TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		String url = configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsBill");//"http://localhost:9911/fe/queryAndStat/txnsBill";//
		return JSONObject.fromObject(excuteHttp(page, rows, txnsForPortalBean, request, url)).toString();
	}

	/**
	 * 2.结算单报表生成excel报表文件放入ftp
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsBillExcelForms")
	public Map<String, Object> txnsBillExcelForms(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		String url =configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsBill");// "http://localhost:9911/fe/queryAndStat/txnsBill";//queryAndStat.txnsBill
		String[] headers = { "SETL", "CURRENCY", "TXNDATE", "TXNFEE", "ACCSETTLEDATE", "RN", "AMOUNT", "BUSINAME",
				"TXNSEQNO", "ACCORDNO" };
		return createExcel("bill", txnsForPortalBean, request, url, headers);
	}

	/**
	 * 3.结算单报表功能生成Txt报表文件放入ftp
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/txnsBillTxtForms")
	public Map<String, Object> txnsBillTxtForms(HttpServletRequest request, String page, String rows,
			TxnsForPortalBean txnsForPortalBean) {
		String url = configParams.getUrls().get("basepath")+configParams.getUrls().get("queryAndStat.txnsBill");//"http://localhost:9911/fe/queryAndStat/txnsBill";//queryAndStat.txnsBill
		return createTxt("bill", request, txnsForPortalBean, url);
	}

	/**
	 * 文件下载
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/downloadFile")
	public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response,
			String packageName) {
		boolean localhost = request.getRequestURL().toString().contains("localhost");
		String rootPath = localhost ? request.getSession().getServletContext().getRealPath("/") : "/";// 获取项目根目录
		String path = rootPath + "Data" + File.separatorChar;
		boolean flag = false;
		try {
			File file = new File(path + File.separatorChar + packageName + File.separatorChar + fileName);
			InputStream inputStream;
			inputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			inputStream.close();
			response.reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			os.write(buffer);// 输出文件
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取ftp文件信息
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:42:32
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/getFileInfo")
	public Map<String, Object> getFileInfo(String packageName, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean localhost = request.getRequestURL().toString().contains("localhost");
		String rootPath = localhost ? request.getSession().getServletContext().getRealPath("/") : "/";// 获取项目根目录
		String path = rootPath + "Data" + File.separatorChar;
		boolean flag = false;
		try {
			File dirDeta = new File(path + File.separatorChar + packageName + File.separatorChar);
			File[] files = dirDeta.listFiles();
			if (files[0] != null) {
				String filename = files[0].getName();
				String[] file = filename.split("\\.");
				resultMap.put("filename", file[0]);
				resultMap.put("filetype", FormTypeEnum.getFormTypeEnum(packageName).getType());
				resultMap.put("filepatten", file[1]);
				resultMap.put("date", filename.substring(4, 12));
				resultMap.put("fileAllName", filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 * 执行http
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @param request
	 * @param url
	 * @return Map<String,Object>
	 * @date: 2017年5月18日 上午11:24:23
	 * @version v1.0
	 */
	private Map<String, Object> excuteHttp(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request, String url) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		HttpRequestParam httpRequestParam = new HttpRequestParam("txnsForPortalBeanStr",
				JSONObject.fromObject(txnsForPortalBean).toString());
		HttpRequestParam httpRequestParam1 = new HttpRequestParam("page", page);
		HttpRequestParam httpRequestParam2 = new HttpRequestParam("rows", rows);

		List<HttpRequestParam> list = new ArrayList<>();
		list.add(httpRequestParam1);
		list.add(httpRequestParam2);
		list.add(httpRequestParam);

		HttpUtils httpUtils = new HttpUtils();
		httpUtils.openConnection();
		String responseContent = null;
		try {
			responseContent = httpUtils.executeHttpPost(url, list, Constants.Encoding.UTF_8);
		} catch (HttpException e) {
			e.printStackTrace();
		} finally {
			httpUtils.closeConnection();
		}
		Map<String, Class> mapClass = new HashMap<String, Class>();
		mapClass.put("rows", Map.class);
		Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(responseContent),
				Map.class, mapClass);
		return map;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> createTxt(String prefix, HttpServletRequest request,
			TxnsForPortalBean txnsForPortalBean, String url) {
		Map<String, Object> dataMap = excuteHttp("1", "1000000", txnsForPortalBean, request, url);
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get("rows");
		FileOutputStream outSTr = null;
		BufferedOutputStream Buff = null;
		String fileName = prefix + DateUtil.getCurrentDate() + ".txt";
		boolean localhost = request.getRequestURL().toString().contains("localhost");
		String rootPath = localhost ? request.getSession().getServletContext().getRealPath("/") : "/";// 获取项目根目录
		String path = rootPath + "Data" + File.separatorChar;
		String enter = "\r\n";
		StringBuffer write;
		boolean flag = false;
		try {
			File dir = new File(path);
			if (!dir.exists()) {// 目录不存在则创建
				dir.mkdir();
			}
			File dirDeta = new File(path + File.separatorChar + prefix + File.separatorChar);
			if (!dirDeta.exists()) {// 目录不存在则创建
				dirDeta.mkdir();
			} else {
				File[] files = dirDeta.listFiles();
				for (File file : files) {
					file.delete();
				}
				dirDeta.mkdir();
			}
			File fileServer = new File(path + File.separatorChar + prefix + File.separatorChar + fileName);
			outSTr = new FileOutputStream(fileServer);
			Buff = new BufferedOutputStream(outSTr);
			for (Map<String, Object> map : dataList) {
				write = new StringBuffer();
				for (String in : map.keySet()) {
					write.append(map.get(in));
					write.append(",");
				}
				write.append(enter);
				Buff.write(write.toString().getBytes("UTF-8"));
			}
			flag = true;
			Buff.flush();
			Buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> returnResult = new HashMap<>();
		if (flag) {
			returnResult.put("code", "00");
			returnResult.put("info", "生成成功");
		} else {
			returnResult.put("code", "99");
			returnResult.put("info", "生成失败");
		}
		return returnResult;
	}

	/**
	 * 生成excel
	 * 
	 * @author: zhangshd
	 * @param pre
	 * @param txnsForPortalBean
	 * @param request
	 * @param url
	 * @param headers
	 * @return Map<String,Object>
	 * @date: 2017年5月18日 上午11:24:36
	 * @version v1.0
	 */
	private Map<String, Object> createExcel(String pre, TxnsForPortalBean txnsForPortalBean, HttpServletRequest request,
			String url, String[] headers) {
		Map<String, Object> returnmap = new HashMap<>();
		Map<String, Object> dataMap = excuteHttp("1", "1000000", txnsForPortalBean, request, url);
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get("rows");
		OutputStream out;
		// TODO:制定命名规则
		String fileName = pre + DateUtil.getCurrentDate() + ".xls";
		Map<String, Object> returnResult = new HashMap<String, Object>();
		boolean flag = false;
		try {
			boolean localhost = request.getRequestURL().toString().contains("localhost");
			String rootPath = localhost ? request.getSession().getServletContext().getRealPath("/") : "/";// 获取项目根目录
			String path = rootPath + "Data" + File.separatorChar;
			File dir = new File(path);
			if (!dir.exists()) {// 目录不存在则创建
				dir.mkdir();
			}
			File dirDeta = new File(path + File.separatorChar + pre + File.separatorChar);
			if (!dirDeta.exists()) {// 目录不存在则创建
				dirDeta.mkdir();
			} else {
				File[] files = dirDeta.listFiles();
				for (File file : files) {
					file.delete();
				}
			}
			File fileServer = new File(path + File.separatorChar + pre + File.separatorChar + fileName);
			out = new FileOutputStream(fileServer);
			ExcelUtil.exportExcel(headers, dataList, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		returnmap.put("info", "生成成功");
		return returnmap;
	}

}
