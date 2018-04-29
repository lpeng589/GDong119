package com.pg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;

/**
 * 该示例代码展示了如果在OSS中创建和删除一个Bucket，以及如何上传和下载一个文件。
 * 
 * 该示例代码的执行过程是：
 * 1. 检查指定的Bucket是否存在，如果不存在则创建它；
 * 2. 上传一个文件到OSS；
 * 3. 下载这个文件到本地；
 * 4. 清理测试资源：删除Bucket及其中的所有Objects。
 * 
 * 尝试运行这段示例代码时需要注意：
 * 1. 为了展示在删除Bucket时除了需要删除其中的Objects,
 *    示例代码最后为删除掉指定的Bucket，因为不要使用您的已经有资源的Bucket进行测试！
 * 2. 请使用您的API授权密钥填充ACCESS_ID和ACCESS_KEY常量；
 * 3. 需要准确上传用的测试文件，并修改常量uploadFilePath为测试文件的路径；
 *    修改常量downloadFilePath为下载文件的路径。
 * 4. 该程序仅为示例代码，仅供参考，并不能保证足够健壮。
 *
 */
public class OSSObjectSample {
	private static final Logger logger = LoggerFactory.getLogger(OSSObjectSample.class);
    public static Object lock = new Object();
    
    /**
     * 上传本地文件到阿里云OSS
     * @param filePath 文件存储到阿里云OSS的路径
     * @param file	本地文件对象
     * @throws OSSException
     * @throws ClientException
     * @throws FileNotFoundException
     */
	public static void save(String filePath, File file) {
		try {
//			save0(filePath, file);
//			logger.error("filePath="+filePath);
	    	// 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
	        ClientConfiguration config = new ClientConfiguration();
	        
			String ACCESS_ID =  Config.getValue(Config.CONFIG_OSSACCESSID);
	        String OSS_ENDPOINT=Config.getValue(Config.CONFIG_OSSENDPOINT);
	        String ACCESS_KEY=Config.getValue(Config.CONFIG_OSSACCESSKEY);
	        String bucketName = Config.getValue(Config.CONFIG_OSSBUCKETNAME);
	        
	        OSSClient client = new OSSClient(OSS_ENDPOINT,ACCESS_ID, ACCESS_KEY, config);

	        ensureBucket(client, bucketName);
	        setBucketPublicReadable(client, bucketName);
	        // 获取Object，返回结果为OSSObject对象
	        OSSObject object = client.getObject(bucketName, "attached/");
	        // 获取Object的输入流
	        InputStream objectContent = object.getObjectContent();
	        ObjectMetadata objectMeta = new ObjectMetadata();
	        objectMeta.setContentLength(file.length());
	        InputStream input = new FileInputStream(file);
	        client.putObject(bucketName, filePath, input);//上传
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /*
     * Java文件操作 获取文件扩展名
     *
     *  Created on: 2011-8-2
     *      Author: blueeagle
     */
    public static String getExtensionName(String filename) { 
        if ((filename != null) && (filename.length() > 0)) { 
            int dot = filename.lastIndexOf('.'); 
            if ((dot >-1) && (dot < (filename.length() - 1))) { 
                return filename.substring(dot + 1); 
            } 
        } 
        return filename; 
    } 

    /**
     * @param args
     */
    public static void main(String[] args)throws Exception {
        String ACCESS_ID = "lO5k6anESQXkK8dY";
        String OSS_ENDPOINT="http://oss-cn-shenzhen.aliyuncs.com";
        String ACCESS_KEY="4SW3NQbpOov0puNaPNLP2IA3otIpYd";
        String bucketName = "pnkooshop";
        // 可以使用ClientConfiguration对象设置代理服务器、最大重试次数等参数。
        ClientConfiguration config = new ClientConfiguration();
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY, config);

        ensureBucket(client, bucketName);

        try {
            setBucketPublicReadable(client, bucketName);
            // 获取Object，返回结果为OSSObject对象
//            OSSObject object = client.getObject(bucketName, "attached");

            // 获取Object的输入流
//            InputStream objectContent = object.getObjectContent();
//            System.out.println(objectContent);
//            System.out.println("正在上传...");
//            uploadFile(client, bucketName, key, uploadFilePath);
            
//            ObjectListing list = client.lis
         // 构造ListObjectsRequest请求
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
            listObjectsRequest.setDelimiter("/");
         // 列出fun目录下的所有文件和文件夹
            listObjectsRequest.setPrefix("attached/image/");
//            listObjectsRequest.setMarker("123");
            ObjectListing list = client.listObjects(listObjectsRequest);
         // 遍历所有Object
            System.out.println("Objects:");
            for (OSSObjectSummary objectSummary : list.getObjectSummaries()) {
                System.out.println(objectSummary.getKey()+","+objectSummary.getETag());
            }
            
         // 遍历所有CommonPrefix
            System.out.println("文件夹CommonPrefixs:");
            for (String commonPrefix : list.getCommonPrefixes()) {
                System.out.println(commonPrefix);
            }
//            System.out.println("正在下载...");
//            downloadFile(client, bucketName, key, downloadFilePath);
            
            // 设置URL过期时间为1小时
//            Date expiration = new Date(new Date().getTime() + 3600 * 1000);

            // 生成URL
//            URL url = client.generatePresignedUrl
//            System.out.println("url="+url);
        } catch(OSSException e){ 
        	e.printStackTrace();
        	if(e.getErrorCode().equals("NoSuchKey")){
        		//文件不存在
        		System.out.println("文件不存在");
//        		client.c
        	}
        }finally {
//            deleteBucket(client, bucketName);
        }
    }

    // 如果Bucket不存在，则创建它。
    @SuppressWarnings("deprecation")
	private static void ensureBucket(OSSClient client, String bucketName)
            throws OSSException, ClientException{

        if (client.isBucketExist(bucketName)){
        	logger.error("isBucketExist true");
            return;
        }

        //创建bucket
        client.createBucket(bucketName);
    }

    // 删除一个Bucket和其中的Objects 
    @SuppressWarnings("unused")
	private static void deleteBucket(OSSClient client, String bucketName)
            throws OSSException, ClientException {

        ObjectListing ObjectListing = client.listObjects(bucketName);
        List<OSSObjectSummary> listDeletes = ObjectListing
                .getObjectSummaries();
        for (int i = 0; i < listDeletes.size(); i++) {
            String objectName = listDeletes.get(i).getKey();
            // 如果不为空，先删除bucket下的文件
            client.deleteObject(bucketName, objectName);
        }
        client.deleteBucket(bucketName);
    }

    // 把Bucket设置为所有人可读
    private static void setBucketPublicReadable(OSSClient client, String bucketName)
            throws OSSException, ClientException {
        //创建bucket
        client.createBucket(bucketName);

        //设置bucket的访问权限，public-read-write权限
        client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }


    // 下载文件
    @SuppressWarnings("unused")
	private static void downloadFile(OSSClient client, String bucketName, String key, String filename)
            throws OSSException, ClientException {
        client.getObject(new GetObjectRequest(bucketName, key),
                new File(filename));
    }
}
