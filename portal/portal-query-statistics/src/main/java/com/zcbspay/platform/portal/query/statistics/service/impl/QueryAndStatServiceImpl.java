package com.zcbspay.platform.portal.query.statistics.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.portal.common.utils.DateUtil;
import com.zcbspay.platform.portal.common.utils.FtpUtil;
import com.zcbspay.platform.portal.common.utils.excel.ExcelUtil;
import com.zcbspay.platform.portal.query.enums.FormTypeEnum;
import com.zcbspay.platform.portal.query.statistics.bean.FtpBean;
import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.dao.QueryAndStatDao;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;

@Service("queryAndStatService")
public class QueryAndStatServiceImpl implements QueryAndStatService {

	@Autowired
	private QueryAndStatDao tradeService;
	@Autowired
	private FtpBean ftp;

	private String errorCode = "99";
	private String successCode = "00";
	private String errorMessage = "失败";
	private String successMessage = "成功";

	@Override
	public Map<String, Object> queryTxnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsTxnsDeta(page, rows, txnsForPortalBean);
	}

	@Override
	public Map<String, Object> createTxnsDetaExcelForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsTxnsDeta("1", "1000000", txnsForPortalBean);
		String[] headers = { "MERCHNAME", "REMARKS", "BUSICODE", "TXNDATE", "RETINFO", "RN", "STATUS", "BUSINAME",
				"COMMITIME", "TXNSEQNO", "RETCODE", "RETTIME", "ORDERID", "TXNAMT", "NOTES" };
		return exportExcelAnd2Ftp(dataMap, headers, "deta");
	}

	@Override
	public Map<String, Object> createTxnsDetaTxtForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsTxnsDeta("1", "1000000", txnsForPortalBean);
		return exportTxtAnd2Ftp(dataMap, "deta");
	}

	@Override
	public File downForms(String fileName,String packageName) {
		File file = null;//
		file = new File(ftp.getLocalPath() + "/" + fileName);
		if (!file.exists()) {
			FtpUtil.downloadFile(ftp.getFtpAddress(), ftp.getFtpPort(), ftp.getFtpUser(), ftp.getFtpPwd(),
					"/" + packageName, fileName, ftp.getLocalPath());
			file = new File(ftp.getLocalPath() + "/" + fileName);
		}
		return file;
	}

	@Override
	public Map<String, Object> getFileInfo(String packageName) {
		Map<String, Object> resultMap=new HashMap<>();
		String filename=FtpUtil.getFileInfo(ftp.getFtpAddress(), ftp.getFtpPort(), ftp.getFtpUser(), ftp.getFtpPwd(),packageName);
		if (filename==null) {
			resultMap.put("err", "不存在文件");
			return resultMap;
		}
		String[] file=filename.split("\\.");
		resultMap.put("filename",  file[0]);
		resultMap.put("filetype", FormTypeEnum.getFormTypeEnum(packageName).getType());
		resultMap.put("filepatten", file[1]);
		resultMap.put("date", filename.substring(4,12));
		resultMap.put("fileAllName", filename);
		return resultMap;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Map<String, Object> queryTxnsStat(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsTxnsStat(page, rows, txnsForPortalBean);
	}

	@Override
	public Map<String, Object> createTxnsStatExcelForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsTxnsStat("1", "1000000", txnsForPortalBean);
		String[] headers = { "CANCELFAILNUM", "MERCHNAME", "ALLNUM", "REMARKS", "MERID", "CANCELNUM", "RN", "SUCCNUM",
				"BUSINAME", "CANCELFAILAMT", "CANCELSUCCNUM", "CYCEL", "CANCELSUCCAMT", "SUCCAMT", "FIALAMT", "FAILNUM",
				"NOTES" };
		return exportExcelAnd2Ftp(dataMap, headers, "stat");
	}

	@Override
	public Map<String, Object> createTxnsStatTxtForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsTxnsStat("1", "1000000", txnsForPortalBean);
		return exportTxtAnd2Ftp(dataMap, "stat");
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Map<String, Object> queryTxnsSetl(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsSetl(page, rows, txnsForPortalBean);
	}

	@Override
	public Map<String, Object> createTxnsSetlExcelForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsSetl("1", "1000000", txnsForPortalBean);
		String[] headers = { "MERCHNAME", "ALLNUM", "CANCELAMT", "REFUNDNUM", "REMARKS", "MERID", "CANCELNUM", "FEES",
				"STIME", "ALLAMT", "SUCCNUM", "REFUNDAMT", "ETIME", "SUCCAMT", "SETLAMT", "ROWNUM", "NOTES" };
		return exportExcelAnd2Ftp(dataMap, headers, "setl");
	}

	@Override
	public Map<String, Object> createTxnsSetlTxtForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsSetl("1", "1000000", txnsForPortalBean);
		return exportTxtAnd2Ftp(dataMap, "setl");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Map<String, Object> queryTxnsBill(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsBill(page, rows, txnsForPortalBean);
	}

	@Override
	public Map<String, Object> createTxnsBillExcelForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsBill("1", "1000000", txnsForPortalBean);
		String[] headers = { "SETL", "CURRENCY", "TXNDATE", "TXNFEE", "ACCSETTLEDATE", "RN", "AMOUNT", "BUSINAME",
				"TXNSEQNO", "ACCORDNO" };
		return exportExcelAnd2Ftp(dataMap, headers, "bill");
	}

	@Override
	public Map<String, Object> createTxnsBillTxtForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsBill("1", "1000000", txnsForPortalBean);
		return exportTxtAnd2Ftp(dataMap, "bill");
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> exportTxtAnd2Ftp(Map<String, Object> dataMap, String prefix) {
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get("rows");
		FileOutputStream outSTr = null;
		BufferedOutputStream Buff = null;
		String fileName = prefix + DateUtil.getCurrentDate() + ".txt";
		String path = ftp.getLocalPath() + fileName;
		String enter = "\r\n";
		StringBuffer write;
		boolean flag = false;
		try {
			outSTr = new FileOutputStream(new File(path));
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
			Buff.flush();
			Buff.close();
			FileInputStream in = new FileInputStream(new File(ftp.getLocalPath() + fileName));
			flag = FtpUtil.uploadFile(ftp.getFtpAddress(), ftp.getFtpPort(), ftp.getFtpUser(), ftp.getFtpPwd(),
					ftp.getFtpPath(), prefix, fileName, in);
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
			returnResult.put("code", successCode);
			returnResult.put("info", successMessage);
		} else {
			returnResult.put("code", errorCode);
			returnResult.put("info", errorMessage);
		}
		return returnResult;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> exportExcelAnd2Ftp(Map<String, Object> dataMap, String[] headers, String prefix) {
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get("rows");
		OutputStream out;
		// TODO:制定命名规则
		String fileName = prefix + DateUtil.getCurrentDate() + ".xls";
		Map<String, Object> returnResult = new HashMap<String, Object>();
		boolean flag = false;
		try {
			out = new FileOutputStream(ftp.getLocalPath() + fileName);
			ExcelUtil.exportExcel(headers, dataList, out);
			FileInputStream in = new FileInputStream(new File(ftp.getLocalPath() + fileName));
			flag = FtpUtil.uploadFile(ftp.getFtpAddress(), ftp.getFtpPort(), ftp.getFtpUser(), ftp.getFtpPwd(),
					ftp.getFtpPath(), prefix, fileName, in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (flag) {
			returnResult.put("code", successCode);
			returnResult.put("info", successMessage);
		} else {
			returnResult.put("code", errorCode);
			returnResult.put("info", errorMessage);
		}
		return returnResult;
	}
	
	
	@Override
	public Map<String, Object> selTxnsSingle(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selTxnsSingle(page,rows,txnsForPortalBean);
	}
	@Override
	public Map<String, Object> selTxnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selTxnsDeta(page,rows,txnsForPortalBean);
	}
	@Override
	public Map<String, Object> selTxnsInfo(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selTxnsInfo(page,rows,txnsForPortalBean);
	}
	@Override
	public Map<String, Object> selTxnsStat(TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selTxnsStat(txnsForPortalBean);
	}
	@Override
	public Map<String, Object> selOrder(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selOrder(page,rows,txnsForPortalBean);
	}
	@Override
	public Map<String, Object> selFormsTxnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsTxnsDeta(page,rows,txnsForPortalBean);
	}
	@Override
	public Map<String, Object> selFormsTxnsStat(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsTxnsStat(page,rows,txnsForPortalBean);
	}
	@Override
	public Map<String, Object> selFormsSetl(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsSetl(page,rows,txnsForPortalBean);
	}
	@Override
	public Map<String, Object> selFormsBill(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsBill(page,rows,txnsForPortalBean);
	}

	@Override
	public Map<String, Object> orderForBatchAndSingle(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selOrderForBatchAndSingle(page, rows, txnsForPortalBean);
	}
}
