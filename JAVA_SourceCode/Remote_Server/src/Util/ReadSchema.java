package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadSchema {
	//Read Statements from properties file
		private String[] statements = new String[9];
		
		public void ReadFile(String filename){
			if (filename == null)
				return;
			
			Properties props = new Properties();
			FileInputStream in = null;
			
			try {
	            in = new FileInputStream(filename);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return;
	        }
			
			try {
				props.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int i = 0;
			setStat(props.getProperty("drop"), i++);
			setStat(props.getProperty("create"), i++);
			setStat(props.getProperty("use"), i++);
			setStat(props.getProperty("category"), i++);
			setStat(props.getProperty("user"), i++);
			setStat(props.getProperty("item"), i++);
			setStat(props.getProperty("questions"), i++);
			setStat(props.getProperty("images"), i++);
			setStat(props.getProperty("answers"), i++);
		}
		
		public void setStat(String str, int i){
			this.statements[i] = str;
		}
		
		public String[] getStat(){
			return statements;
		}
}
