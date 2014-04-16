package com.candy_code.tinytools.shell;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UnixExecutor
{
	public static UnixExecutor.Result execute(String cmd) throws IOException, InterruptedException{
		Process p = null;
		InputStream in = null;
		InputStream error = null;
		ByteArrayOutputStream baos = null;
		Result result = new Result();
		try{
			p = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",cmd});
			in = p.getInputStream();
			error = p.getErrorStream();
			baos = new ByteArrayOutputStream();
			int size = 0;
			byte[] buffer = new byte[1024];
			while((size = in.read(buffer)) != -1){
				baos.write(buffer,0,size);
			}
			result.setInfo(baos.toString());
			
			size = 0;
			buffer = new byte[1024];
			while((size = error.read(buffer)) != -1){
				baos.write(buffer,0,size);
			}
			result.setError(baos.toString());
		}catch(Exception e){
			result.setError(e.getMessage());
		}
		finally{
			if(p!=null){
				p.waitFor();
				p.destroy();
			}
			if(in != null){
				in.close();
			}
			if(error != null){
				error.close();
			}
			if(baos != null){
				baos.close();
			}
		}
		return result;
	}
	public static class Result{
		private String info;
		private String error;
		public String getInfo()
		{
			return info;
		}
		public void setInfo(String info)
		{
			this.info = info;
		}
		public String getError()
		{
			return error;
		}
		public void setError(String error)
		{
			this.error = error;
		}
	}
}

