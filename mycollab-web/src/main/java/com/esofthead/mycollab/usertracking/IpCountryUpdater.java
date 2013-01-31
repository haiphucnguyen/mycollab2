package com.esofthead.mycollab.usertracking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.esofthead.mycollab.usertracking.dao.IpCountryMapper;
import com.esofthead.mycollab.usertracking.domain.IpCountry;

public class IpCountryUpdater {
	private static final String CONFIG_FILE = "usertracking.properties";
	private static final String MYBATIS_CONFIG = "usertracking.mybatis.xml";
	
	private static final String URL = "URL";

	public static final void updateDB() throws Exception {
		Properties properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(CONFIG_FILE));

		String url = properties.getProperty(URL);
		/**
		 * download database from server
		 */

		String tmpDir = getTmpDir();
		String downloadFile;
		if (tmpDir.endsWith("/"))
			downloadFile = String.format("%s%s", tmpDir,
					encryptText(String.valueOf(System.currentTimeMillis()))
							+ ".zip");
		else
			downloadFile = String.format("%s/%s", tmpDir,
					encryptText(String.valueOf(System.currentTimeMillis()))
							+ ".zip");

		HttpDownloader.downloadFile(url, downloadFile);
		List<String> lsUnzipFile = Unzip.unzip(downloadFile, tmpDir);
		if (lsUnzipFile.size() > 0) {
			String databaseFile = lsUnzipFile.get(0);
			/**
			 * serialize plain text to list objects
			 */
			List<IpCountry> lsIpCountry = parse(databaseFile);				
			/**
			 * truncate database and insert new data we would like to do in
			 * an session
			 */
			updateDatabase(lsIpCountry);
		}
	}

	private static final String getTmpDir() throws Exception {
		File file = File
				.createTempFile(
						encryptText(String.valueOf(System.currentTimeMillis())),
						".tmp");
		return file.getParent();
	}

	private static String encryptText(String input) {
		try {
			byte[] bytesOfMessage = input.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			BigInteger bigInt = new BigInteger(1, thedigest);
			return bigInt.toString(16);
		} catch (Exception ex) {
			return input;
		}
	}

	private static final List<IpCountry> parse(String fileName)
			throws Exception {
		List<IpCountry> lsResult = new LinkedList<IpCountry>();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.length() > 0) {
				String[] items = line.split(",");
				long fromip = Long.parseLong(items[2].substring(1, items[2].length() - 1));
				long toip = Long.parseLong(items[3].substring(1, items[3].length() - 1));
				String countryCode = items[4].substring(1, items[4].length() - 1);
				String countryName = items[5].substring(1, items[5].length() - 1);
				
				IpCountry obj = new IpCountry();
				obj.setFromip(fromip);
				obj.setToip(toip);
				obj.setCountryCode(countryCode);
				obj.setCountryName(countryName);
				
				lsResult.add(obj);
			}
		}
		br.close();
		
		return lsResult;
	}

	private static final void updateDatabase(List<IpCountry> lsIpCountry)
			throws Exception {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sessionFactory = builder.build(Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(MYBATIS_CONFIG));
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			IpCountryMapper mapper = session.getMapper(IpCountryMapper.class);
			
			mapper.deleteByExample(null);
			for (IpCountry record : lsIpCountry) {
				mapper.insert(record);
			}
			session.commit();
		}
		finally {
			if (null != session)
				session.close();
		}
	}
}
