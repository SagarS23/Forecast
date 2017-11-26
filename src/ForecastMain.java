import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import Utils.Constants;

public class ForecastMain {

	public static StringBuilder sb;

	private static PrintWriter pw = null;

	private static Parsing parsing;
	
	public boolean isWinter, isSpring, isSummer, isAutumn;
	
	public int Current_Month_Val;

	public static String getForecast() throws IOException {

		pw = new PrintWriter(new File("weather.csv"));
		sb = new StringBuilder();

		parsing = new Parsing("region_code", "weather_param", "year", "key",
				"value");
		
		// UK

		// Region - UK(Max Temperature)
		parsing.readFile(Constants.MAX_TEMP + "/UK.txt", "UK", "Max temp");

		// Region - UK(Min Temperature)
		parsing.readFile(Constants.MIN_TEMP + "/UK.txt", "UK", "Min temp");

		// Region - UK(Mean Temperature)
		parsing.readFile(Constants.MEAN_TEMP + "/UK.txt", "UK", "Mean temp");

		// Region - UK(Sunshine)
		parsing.readFile(Constants.SUNSHINE + "/UK.txt", "UK", "Sunshine");

		// Region - UK(Rainfall)
		parsing.readFile(Constants.RAINFALL + "/UK.txt", "UK", "Rainfall");

		// England

		// Region - England(Max Temperature)
		parsing.readFile(Constants.MAX_TEMP + "/England.txt", "England",
				"Max temp");

		// Region - England(Min Temperature)
		parsing.readFile(Constants.MIN_TEMP + "/England.txt", "England",
				"Min temp");

		// Region - England(Mean Temperature)
		parsing.readFile(Constants.MEAN_TEMP + "/England.txt", "England",
				"Mean temp");

		// Region - England(Sunshine)
		parsing.readFile(Constants.SUNSHINE + "/England.txt", "England",
				"Sunshine");

		// Region - England(Rainfall)
		parsing.readFile(Constants.RAINFALL + "/England.txt", "England",
				"Rainfall");

		// Wales

		// Region - Wales(Max Temperature)
		parsing.readFile(Constants.MAX_TEMP + "/Wales.txt", "Wales", "Max temp");

		// Region - Wales(Min Temperature)
		parsing.readFile(Constants.MIN_TEMP + "/Wales.txt", "Wales", "Min temp");

		// Region - Wales(Mean Temperature)
		parsing.readFile(Constants.MEAN_TEMP + "/Wales.txt", "Wales",
				"Mean temp");

		// Region - Wales(Sunshine)
		parsing.readFile(Constants.SUNSHINE + "/Wales.txt", "Wales", "Sunshine");

		// Region - Wales(Rainfall)
		parsing.readFile(Constants.RAINFALL + "/Wales.txt", "Wales", "Rainfall");

		// Scotland

		// Region - Scotland(Max Temperature)
		parsing.readFile(Constants.MAX_TEMP + "/Scotland.txt", "Scotland",
				"Max temp");

		// Region - Scotland(Min Temperature)
		parsing.readFile(Constants.MIN_TEMP + "/Scotland.txt", "Scotland",
				"Min temp");

		// Region - Scotland(Mean Temperature)
		parsing.readFile(Constants.MEAN_TEMP + "/Scotland.txt", "Scotland",
				"Mean temp");

		// Region - Scotland(Sunshine)
		parsing.readFile(Constants.SUNSHINE + "/Scotland.txt", "Scotland",
				"Sunshine");

		// Region - Scotland(Rainfall)
		parsing.readFile(Constants.RAINFALL + "/Scotland.txt", "Scotland",
				"Rainfall");

		
		pw.write(sb.toString());
		pw.close();

		System.out
				.println("weather.csv created successfully in workspace/Forecast!");
		
		return "weather.csv created successfully in workspace/Forecast!";

	}
	
	public void calcForSeasons(int NoOfMonths) {

		if (NoOfMonths < 5 && NoOfMonths > 1) {
			isWinter = true;
		} else if (NoOfMonths < 8 && NoOfMonths > 4) {
			Current_Month_Val = NoOfMonths;
			isSpring = true;
		} else if (NoOfMonths < 11 && NoOfMonths > 7) {
			Current_Month_Val = NoOfMonths;
			isSummer = true;
		} else if (NoOfMonths < 12 && NoOfMonths > 10) {
			isAutumn = true;
		}

	}


}
