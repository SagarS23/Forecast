import Interface.ICreateOutput;

public class Output implements ICreateOutput {

	@Override
	public void printData(String Region_Code, String Weather_Param,
			String Year, String Month, String Value) {
		// TODO Auto-generated method stub

		System.out.println(" " + Region_Code + ",\t\t" + Weather_Param
				+ ",\t\t" + Year + ",\t\t" + Month + ",\t\t" + Value);

		createCSV(Region_Code, Weather_Param, Year, Month, Value);

	}

	@Override
	public void createCSV(String Region_Code, String Weather_Param,
			String Year, String Month, String Value) {
		// TODO Auto-generated method stub

		ForecastMain.sb.append(Region_Code);
		ForecastMain.sb.append(',');
		ForecastMain.sb.append(Weather_Param);
		ForecastMain.sb.append(',');
		ForecastMain.sb.append(Year);
		ForecastMain.sb.append(',');
		ForecastMain.sb.append(Month);
		ForecastMain.sb.append(',');
		ForecastMain.sb.append(Value);
		ForecastMain.sb.append('\n');

	}

}
