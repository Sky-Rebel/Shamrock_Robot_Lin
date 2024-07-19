package com.rebel.lin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;

/* 
 * @Author Sky-Rebel 
 * @Date 2024-7-19
 *
 * This class creates a server and listens for messages on the local port.
 * This class is mainly used to monitor the reporting message of Shamrock.
*/

public class HttpServer
{
	
	private int port;
	
	// @param Port number
	// @return null
	
	public HttpServer(int port)
	{
		this.port = port;
	}
	 
	// @Param null
	// @Return null
	
	public void start() throws IOException
	{	
		while (true)
		{	
			ServerSocket serverSocket = new ServerSocket(port);	
			handler(serverSocket.accept());	
		}	
	}
	
	// @param Socket
	// @return Message from shamrock
	
	public String handler(Socket socket) throws IOException
	{
		
		InputStream inputStream = socket.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		int line;
		StringBuilder content = new StringBuilder();	
		while ((line = inputStreamReader.read()) != -1)
		{	
			content.append((char)line);		
		}	
		return content.toString();
	}
}
