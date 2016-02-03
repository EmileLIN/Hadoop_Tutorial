public class InitConfReader{
	private String confFileUrl;

	public Map<String,String> getConfs(List<String> keys){
		Map<String,String> result = new HashMap<String,String>();
		Properties properties = new Properties();

		//Get information from conf file
		try{
			properties.load(new FileReader(new File(confFileUrl)));
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		for(String key:keys){
			String value =(String) properties.get(key);
			result.put(key,value);
		}

	}


}

