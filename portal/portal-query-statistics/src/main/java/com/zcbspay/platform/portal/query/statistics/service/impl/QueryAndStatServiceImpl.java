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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zcbspay.platform.manager.trade.bean.TxnsForPortalBean;
import com.zcbspay.platform.manager.trade.service.TradeService;
import com.zcbspay.platform.manager.utils.DateUtils;
import com.zcbspay.platform.portal.common.utils.ExcelUtil;
import com.zcbspay.platform.portal.common.utils.FtpUtil;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;

@Service("queryAndStatService")
public class QueryAndStatServiceImpl implements QueryAndStatService {

	@Autowired
	private TradeService tradeService;

	@Value("#{ftp['local.path']}")
	private String localPath;

	@Value("#{ftp['ftp.address']}")
	private String ftpAddress;

	@Value("#{ftp['ftp.port']}")
	private int ftpPort;

	@Value("#{ftp['ftp.user']}")
	private String ftpUser;

	@Value("#{ftp['ftp.pwd']}")
	private String ftpPwd;

	@Value("#{ftp['ftp.path']}")
	private String ftpPath;
	
	private String errorCode="99";
	private String successCode="00";
	private String errorMessage="失败";
	private String successMessage="成功";

	@Override
	public Map<String, Object> queryTxnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsTxnsDetaPortal(page, rows, txnsForPortalBean);
	}

	@Override
	public Map<String, Object> createTxnsDetaExcelForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsTxnsDetaPortal("1", "1000000", txnsForPortalBean);
		//TODO:这里需要修改
		String[] headers = { "zhang", "shi", "dong" };
		return exportExcelAnd2Ftp(dataMap, headers,"deta");
	}

	@Override
	public Map<String, Object> createTxnsDetaTxtForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsTxnsDetaPortal("1", "1000000", txnsForPortalBean);
		return exportTxtAnd2Ftp(dataMap,"deta");
	}

	

	@Override
	public boolean downForms(String fileName) {
		return FtpUtil.downloadFile(ftpAddress, ftpPort, ftpUser, ftpPwd, "/"+DateUtils.getCurrentDateString(), fileName, localPath);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Map<String, Object> queryTxnsStat(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsTxnsStatPortal(page, rows, txnsForPortalBean);
	}

	@Override
	public Map<String, Object> createTxnsStatExcelForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsTxnsStatPortal("1", "1000000", txnsForPortalBean);
		//TODO:这里需要修改
		String[] headers = { "zhang", "shi", "dong" };
		return exportExcelAnd2Ftp(dataMap, headers,"stat");
	}

	

	@Override
	public Map<String, Object> createTxnsStatTxtForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsTxnsStatPortal("1", "1000000", txnsForPortalBean);
		return exportTxtAnd2Ftp(dataMap,"stat");
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Map<String, Object> queryTxnsSetl(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsSetlPortal(page, rows, txnsForPortalBean);
	}

	@Override
	public Map<String, Object> createTxnsSetlExcelForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsSetlPortal("1", "1000000", txnsForPortalBean);
		//TODO:这里需要修改
		String[] headers = { "zhang", "shi", "dong" };
		return exportExcelAnd2Ftp(dataMap, headers,"setl");
	}

	@Override
	public Map<String, Object> createTxnsSetlTxtForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsSetlPortal("1", "1000000", txnsForPortalBean);
		return exportTxtAnd2Ftp(dataMap,"setl");
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Map<String, Object> queryTxnsBill(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selFormsBillPortal(page, rows, txnsForPortalBean);
	}

	@Override
	public Map<String, Object> createTxnsBillExcelForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsBillPortal("1", "1000000", txnsForPortalBean);
		//TODO:这里需要修改
		String[] headers = { "zhang", "shi", "dong" };
		return exportExcelAnd2Ftp(dataMap, headers,"bill");
	}

	@Override
	public Map<String, Object> createTxnsBillTxtForms(TxnsForPortalBean txnsForPortalBean) {
		Map<String, Object> dataMap = tradeService.selFormsBillPortal("1", "1000000", txnsForPortalBean);
		return exportTxtAnd2Ftp(dataMap,"bill");
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> exportTxtAnd2Ftp(Map<String, Object> dataMap,String prefix) {
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get("rows");
		FileOutputStream outSTr = null;
		BufferedOutputStream Buff = null;
		String fileName = prefix + DateUtils.getCurrentDateString() + ".txt";
		String path= localPath+fileName;
		String enter = "\r\n";
		StringBuffer write;
		boolean flag=false;
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
			FileInputStream in = new FileInputStream(new File(localPath + fileName));
			flag = FtpUtil.uploadFile(ftpAddress, ftpPort, ftpUser, ftpPwd, ftpPath, DateUtils.getCurrentDateString(),
					fileName, in);
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
	private Map<String, Object> exportExcelAnd2Ftp(Map<String, Object> dataMap, String[] headers,String prefix) {
		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get("rows");
		OutputStream out;
		// TODO:制定命名规则
		String fileName = prefix + DateUtils.getCurrentDateString() + ".xls";
		Map<String, Object> returnResult = new HashMap<String, Object>();
		boolean flag = false;
		try {
			out = new FileOutputStream(localPath + fileName);
			ExcelUtil.exportExcel(headers, dataList, out);
			FileInputStream in = new FileInputStream(new File(localPath + fileName));
			flag = FtpUtil.uploadFile(ftpAddress, ftpPort, ftpUser, ftpPwd, ftpPath, DateUtils.getCurrentDateString(),
					fileName, in);
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
}
