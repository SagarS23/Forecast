import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import Interface.ICreateOutput;
import Model.WeatherDataRes;
import Utils.Constants;

public class Parsing extends ForecastMain {

	private List<WeatherDataRes> list_WeatherData = new ArrayList<>();

	private List<String> list_Month = new ArrayList<>();

	private List<String> list_Values = new ArrayList<>();

	private List<String> seasonValues = new ArrayList<>();

	private String[] strArr_Line;

	private String[] strArr_Val;

	private int current_Year;

	private String region_code, weather_param, year, key, value, Month,
			str_Value, str_Current_Year, str_Current_Month, is_DataAval = "",
			str_FinalMinValue, str_GetMinFinalValue, str_FinalMaxValue,
			str_GetMaxFinalValue;

	private Calendar now;

	ICreateOutput iCreateOutput = new Output();

	public Parsing(String region_code, String weather_param, String year,
			String key, String value) {
		super();
		this.region_code = region_code;
		this.weather_param = weather_param;
		this.year = year;
		this.key = key;
		this.value = value;

		System.out
				.print(" " + "region_code" + ",\t" + "weather_param" + ",\t\t"
						+ "year" + ",\t\t" + "key" + ",\t\t" + "value" + "\n");

		iCreateOutput.createCSV("region_code", "weather_param", "year", "key",
				"value");
	}

	public void readFile(String Main_URL, String Region_Code,
			String Weather_Param) throws IOException {

		list_Month.add("JAN");
		list_Month.add("FEB");
		list_Month.add("MAR");
		list_Month.add("APR");
		list_Month.add("MAY");
		list_Month.add("JUN");
		list_Month.add("JUL");
		list_Month.add("AUG");
		list_Month.add("SEP");
		list_Month.add("OCT");
		list_Month.add("NOV");
		list_Month.add("DEC");
		list_Month.add("WIN");
		list_Month.add("SPR");
		list_Month.add("SUM");
		list_Month.add("AUT");
		list_Month.add("ANN");

		// Getting current year
		now = Calendar.getInstance();
		current_Year = now.get(Calendar.YEAR);
		str_Current_Year = String.valueOf(current_Year);

		// Getting current month
		str_Current_Month = new SimpleDateFormat("MMM").format(now.getTime());

		try {

			URL url = new URL(Constants.BASE_URL + Main_URL);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));

			for (int i = 0; i < 7; ++i)
				in.readLine();
			String str_line = in.readLine();

			while ((str_line = in.readLine()) != null) {

				strArr_Line = str_line.split(",");

				region_code = Region_Code;
				weather_param = Weather_Param;
				year = strArr_Line[0];
				key = strArr_Line[0];
				value = strArr_Line[0];

				// create temporary instance of object
				// and load with data values
				WeatherDataRes dataRes = new WeatherDataRes(region_code,
						weather_param, year, key, value);

				// add to array list
				list_WeatherData.add(dataRes);

			}
			in.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("file not found");
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		
		for (int i = 0; i < list_WeatherData.size(); i++) {

			list_Values.clear();

			String Year = list_WeatherData
					.get(i)
					.getYear()
					.substring(
							0,
							Math.min(
									list_WeatherData.get(i).getYear().length(),
									4));

			strArr_Val = list_WeatherData.get(i).getYear().split(" ");

			for (int l = 1; l < strArr_Val.length; l++) {

				if (strArr_Val[l].equalsIgnoreCase("---")) {

					list_Values.add("N/A" + "\n");
				} else if (strArr_Val[l].equalsIgnoreCase("")) {

					list_Values.add(strArr_Val[l].trim());
				}

				else {

					list_Values.add(strArr_Val[l] + "\n");
				}

			}

			list_Values.removeAll(Arrays.asList(null, ""));

			str_GetMinFinalValue = list_Values.get(0).trim();

			str_GetMaxFinalValue = list_Values.get(0).trim();

			// All Month list
			for (int k = 0; k < list_Month.size(); k++) {

				// Parsed Month
				Month = list_Month.get(k);

				if (Year.equalsIgnoreCase(str_Current_Year)
						&& Month.equalsIgnoreCase(str_Current_Month)) {
					str_Value = "N/A";
					is_DataAval = "Data_Not_Aval";

					// Calculating seasons for current year
					calcForSeasons(k);

					// Print data on console
					iCreateOutput.printData(list_WeatherData.get(i)
							.getRegion_code(), list_WeatherData.get(i)
							.getWeather_param(), Year, Month, str_Value);

				} else if (is_DataAval.equalsIgnoreCase("Data_Not_Aval")
						&& !Month.equalsIgnoreCase("WIN")) {
					str_Value = "N/A";

					iCreateOutput.printData(list_WeatherData.get(i)
							.getRegion_code(), list_WeatherData.get(i)
							.getWeather_param(), Year, Month, str_Value);

				} else if (is_DataAval.equalsIgnoreCase("Data_Not_Aval")
						&& Month.equalsIgnoreCase("WIN")) {

					// Seasons calculation for current year
					if (list_Values.size() < 17 && list_Values.size() > 1) {

						if (isWinter) {
							seasonValues
									.add(list_Values.get(list_Values.size() - 1));
						} else if (isSpring) {
							if (Current_Month_Val < 7)
								seasonValues.addAll(list_Values.subList(5, 7));
							else
								seasonValues.addAll(list_Values.subList(7, 9));
						} else if (isSummer) {
							if (Current_Month_Val < 9)
								seasonValues.addAll(list_Values.subList(8, 11));
							else if (Current_Month_Val < 10)
								seasonValues.addAll(list_Values.subList(9, 12));
							else
								seasonValues
										.addAll(list_Values.subList(10, 13));
						} else if (isAutumn)
							seasonValues.addAll(list_Values.subList(11, 15));

						for (int j = 0; j < seasonValues.size(); j++) {
							str_Value = seasonValues.get(j).trim();

							String season_Month = list_Month.get(k++);

							iCreateOutput.printData(list_WeatherData.get(i)
									.getRegion_code(), list_WeatherData.get(i)
									.getWeather_param(), Year, season_Month,
									str_Value);

						}
						k--;

					} else {

						str_Value = "N/A";

						iCreateOutput.printData(list_WeatherData.get(i)
								.getRegion_code(), list_WeatherData.get(i)
								.getWeather_param(), Year, Month, str_Value);
					}

				} else {

					str_Value = list_Values.get(k).trim();

					if (!str_Value.equalsIgnoreCase("N/A")
							&& Float.parseFloat(str_GetMinFinalValue) >= Float
									.parseFloat(str_Value)) {

						str_GetMinFinalValue = str_Value;

					}

					if (!str_Value.equalsIgnoreCase("N/A")
							&& Float.parseFloat(str_GetMaxFinalValue) <= Float
									.parseFloat(str_Value)) {

						str_GetMaxFinalValue = str_Value;

					}

					if (k == 11
							&& Year.equalsIgnoreCase(String
									.valueOf(current_Year - 1))) {
						str_FinalMinValue = str_GetMinFinalValue;
						str_FinalMaxValue = str_GetMaxFinalValue;

					}
					iCreateOutput.printData(list_WeatherData.get(i)
							.getRegion_code(), list_WeatherData.get(i)
							.getWeather_param(), Year, Month, str_Value);

				}

			}

			System.out.println("\n");

			iCreateOutput.createCSV("", "", "", "", "");

		}

		// Print Last year's Max & Min values
		System.out.println(" Last year's " + "("
				+ (String.valueOf(current_Year - 1)) + ")" + " minimum value"
				+ "- \n" + " " + Region_Code + "\t\t" + Weather_Param + "\t\t"
				+ str_FinalMinValue + "\n" + " Last year's " + "("
				+ (String.valueOf(current_Year - 1)) + ")" + " maximum value"
				+ "- \n" + " " + Region_Code + "\t\t" + Weather_Param + "\t\t"
				+ str_FinalMaxValue + "\n\n");

		ClearData();

	}

	public void ClearData() {

		list_WeatherData.clear();

		list_Values.clear();

		list_Month.clear();

		seasonValues.clear();

		strArr_Val = new String[0];

		Current_Month_Val = 0;

		strArr_Line = new String[0];

		isWinter = false;
		isSpring = false;
		isSummer = false;
		isAutumn = false;

		is_DataAval = "";
		str_Value = "";
		region_code = "";
		weather_param = "";
		year = "";
		key = "";
		value = "";
		Month = "";

	}

}
