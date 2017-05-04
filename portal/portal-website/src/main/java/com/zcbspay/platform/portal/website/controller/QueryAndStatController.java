package com.zcbspay.platform.portal.website.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;

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

	@Autowired
	private QueryAndStatService queryAndStatService;

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
	public Map<String, Object> txnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.queryTxnsDeta(page, rows, txnsForPortalBean);
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
	public Map<String, Object> txnsDetaExcelForms(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.createTxnsDetaExcelForms(txnsForPortalBean);
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
	public Map<String, Object> txnsDetaTxtForms(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.createTxnsDetaTxtForms(txnsForPortalBean);
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
	public Map<String, Object> txnsStat(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.queryTxnsStat(page, rows, txnsForPortalBean);
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
	public Map<String, Object> txnsStatExcelForms(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.createTxnsStatExcelForms(txnsForPortalBean);
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
	public Map<String, Object> txnsStatTxtForms(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.createTxnsStatTxtForms(txnsForPortalBean);
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
	public Map<String, Object> txnsSetl(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.queryTxnsSetl(page, rows, txnsForPortalBean);
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
	public Map<String, Object> txnsSetlExcelForms(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.createTxnsSetlExcelForms(txnsForPortalBean);
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
	public Map<String, Object> txnsSetlTxtForms(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.createTxnsSetlTxtForms(txnsForPortalBean);
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
	public Map<String, Object> txnsBill(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.queryTxnsBill(page, rows, txnsForPortalBean);
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
	public Map<String, Object> txnsBillExcelForms(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.createTxnsBillExcelForms(txnsForPortalBean);
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
	public Map<String, Object> txnsBillTxtForms(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return queryAndStatService.createTxnsBillTxtForms(txnsForPortalBean);
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
	public void downloadFile(String fileName, HttpServletRequest request,HttpServletResponse response) {
		File file = queryAndStatService.downForms(fileName);
		String filenames = file.getName();
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			inputStream.close();
			response.reset();
			// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(filenames.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
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
	
	public static void main(String[] args) {
		File file = new File("D:/ftp/in/20170502/deta2170502.txt");
		String filenames = file.getName();
		
		System.out.println(file.exists());
	}
}
