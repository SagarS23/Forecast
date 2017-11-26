package Interface;
public interface ICreateOutput {

	public void createCSV(String Region_Code, String Weather_Param,
			String Year, String Month, String Value);
	
	public void printData(String Region_Code, String Weather_Param,
			String Year, String Month, String Value);

}
