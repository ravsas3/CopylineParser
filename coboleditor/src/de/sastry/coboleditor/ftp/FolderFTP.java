package de.sastry.coboleditor.ftp;

//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


import java.io.File;
import java.io.IOException;

 
/**
 * A program demonstrates how to upload files from local computer to a remote
 * FTP server using Apache Commons Net API.
 * @author www.codejava.net
 */

public class FolderFTP{

	FolderFTP()
	{
		super();
	}
    public void Send(String[] args) {
    	
    	if ( args.length != 6)
    	{
    		System.out.println("Usage is: FTPDownloadFileDemo <Server> <Username> <Password> <get/put> <localfile> <remotefile>");
    		return;
    	}
        String server = args[0];
        int port = 21;
        String user = args[1];
        String pass = args[2];
        String localDirPath = args[4];
        String remoteParentDir = args[5];
        
        FTPClient ftpClient = new FTPClient();
        try {
                System.out.println("Connecting to :"+server);
        	    try{ ftpClient.connect(server, port); } catch(Exception e){System.out.println("Problem Connecting :"+e.getMessage());return;};
                System.out.println("Logging in :"+user);
        	    ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                File localDir = new File(localDirPath);
        	    if ( !localDir.isFile() )
        	    {
        	        String[] pathsegs = localDirPath.split("/");
        	        String remoteFilePath=remoteParentDir+"/"+pathsegs[pathsegs.length - 1];
        	        FTPUtils.createdirectory(ftpClient,remoteFilePath);
        	        remoteParentDir=remoteFilePath;
        	    }
        	    else
        	    {
        	    	FTPUtils.uploadSingleFile(ftpClient,localDirPath, remoteParentDir);
        	    }
                FTPUtils.uploadDirectory(ftpClient, localDirPath, remoteParentDir);
	            System.out.println("Upload Done!!");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                boolean connected = ftpClient.isConnected();
				if (connected) {
                    ftpClient.logout();
                    System.out.println("Logged Out");
                    ftpClient.disconnect();
                    System.out.println("Disconnected");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void Get(String[] args) {
    	
    	if ( args.length != 6)
    	{
    		System.out.println("Usage is: FTPDownloadFileDemo <Server> <Username> <Password> <get/put> <localfile> <remotefile>");
    		return;
    	}
        String server = args[0];
        int port = 21;
        String user = args[1];
        String pass = args[2];
        String localDirPath = args[4];
        String remoteParentDir = args[5];
        
        FTPClient ftpClient = new FTPClient();
        try {
                System.out.println("Connecting to :"+server);
        	    try{ ftpClient.connect(server, port); } catch(Exception e){System.out.println("Problem Connecting :"+e.getMessage());return;};
                System.out.println("Logging in :"+user);
        	    ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                File localDir = new File(localDirPath);
        	    if ( !localDir.isFile() )
        	    {
        	        String[] pathsegs = localDirPath.split("/");
        	        String remoteFilePath=remoteParentDir+"/"+pathsegs[pathsegs.length - 1];
        	        FTPUtils.createdirectory(ftpClient,remoteFilePath);
        	        remoteParentDir=remoteFilePath;
        	    }
        	    else
        	    {
        	    	FTPUtils.uploadSingleFile(ftpClient,localDirPath, remoteParentDir);
        	    }
                FTPUtils.uploadDirectory(ftpClient, localDirPath, remoteParentDir);
	            System.out.println("Upload Done!!");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                boolean connected = ftpClient.isConnected();
				if (connected) {
                    ftpClient.logout();
                    System.out.println("Logged Out");
                    ftpClient.disconnect();
                    System.out.println("Disconnected");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }    
}