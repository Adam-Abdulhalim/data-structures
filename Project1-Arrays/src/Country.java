/**
 * This class was made in order for us to have an object that can store and perform 
 * operations on all of the different values we needed to store for the countries
 * read from the Countries1.csv file. In Country objects, we store string values for 
 * both the name and capitol of the countries, while also having double values for 
 * population, GDP, Covid cases, Covid deaths, country area, GDPPC, CFR, Covid case rate,
 * Covid death rate, and population density. In our constructor, values for everything
 * except GDPPC, CFR, case rate, death rate, and population density are accepted and 
 * assigned. The latter five values are calculated and assigned from the accepted constructor
 * values. There are getters and setters for all of these values, as well as two separate
 * print methods. printCountryLn() prints name, capitol, GDPPC, CFR, case rate, death rate,
 * and population density in the format of the full Countries Report in Project1.java.
 * 
 * @author Adam Abdulhalim
 * @version 9/16/22
 */
public class Country {

	private String name;
	private String capitol;
	private double population, GDP, cases, deaths, area, GDPPC, CFR, caseRate, deathRate, popDens;

	public Country(String name, String cap, double pop, double gdp, double cases, double deaths, double area) {
		
		this.name = name;
		this.capitol = cap;
		this.population = pop;
		this.GDP = gdp;
		this.cases = cases;
		this.deaths = deaths;
		this.area = area;
		this.GDPPC = gdp / pop;
		this.CFR = deaths / cases;
		this.caseRate = cases / (pop / 100000);
		this.deathRate = deaths / (pop / 100000);
		this.popDens = pop / area;
	}
	
	/**
	 * The printCountryLn method prints the name of the country, the name of its capitol,
	 * its GDPPC, CFR, caseRate, deathRate, and popDens values. These are printed in a way
	 * so that each country takes only one line for its report, so that the method can be
	 * repeated in order to print the entire list of countries.
	 */
	
	public void printCountryLn() {

		System.out.printf("%-32s %-14s %10.3f %11.6f %12.3f %10.3f %10.3f\n", name, capitol, GDPPC, CFR, caseRate, deathRate, popDens);
		
	}

	/**
	 * The printCountrySearched method prints the name of the country, the name of its capitol,
	 * its GDPPC, CFR, caseRate, deathRate, and popDens values. These are printed in a 
	 * different format from printCountryLn, taking a line per value printed. Because of 
	 * this, this method is generally only called when searching for one specific Country
	 * object to return/print.
	 */
	
	public void printCountrySearched() {
		
		System.out.printf("Name:             %s\n", name);
		System.out.printf("Capitol:          %s\n", capitol);
		System.out.printf("GDPPC:            %.3f\n", GDPPC);
		System.out.printf("CFR:              %.6f\n", CFR);
		System.out.printf("CaseRate:         %.3f\n", caseRate);
		System.out.printf("DeathRate:        %.3f\n", deathRate);
		System.out.printf("PopDensity:       %.3f\n", popDens);
	}
	
	/**
	 * This method gets the name of the country from the object's "name" value.
	 * 
	 * @return The value of the Country object's "name" string is returned.
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * This method is used to set the value of the Country object's "name" string. 
	 * The parameter name is taken and set to the "name" value, holding the name
	 * of the country in the real world.
	 * 
	 * @param name This parameter provides the string value of the name of the country.
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the name of the country's capitol from the object's "capitol" value.
	 * 
	 * @return The value of the Country object's "capitol" string is returned.
	 */
	
	public String getCapitol() {
		return capitol;
	}

	/**
	 * This method is used to set the value of the Country object's "capitol" string. 
	 * The parameter capitol is taken and set to the "capitol" value, holding the capitol name
	 * of the country in the real world.
	 * 
	 * @param capitol This parameter provides the string value of the capitol of the country.
	 */
	
	public void setCapitol(String capitol) {
		this.capitol = capitol;
	}

	/**
	 * This method gets the population of the country from the object's "population" value.
	 * 
	 * @return The value of the Country object's "population" double is returned.
	 */
	
	public double getPopulation() {
		return population;
	}

	/**
	 * This method is used to set the value of the Country object's "population" double.
	 * The parameter population is taken and set to the "population" value, holding the 
	 * numerical population of the country in the real world. 
	 * 
	 * @param population This parameter provides the double value of the population of the country.
	 */
	
	public void setPopulation(double population) {
		this.population = population;
	}

	/**
	 * This method gets the GDP of the country from the object's "GDP" value.
	 * 
	 * @return The value of the Country object's "GDP" double is returned.
	 */
	
	public double getGDP() {
		return GDP;
	}

	/**
	 * This method is used to set the value of the Country object's "GDP" double.
	 * The parameter gDP is taken and set to the "GDP" value, holding the 
	 * numerical GDP of the country in the real world. 
	 * 
	 * @param gDP This parameter provides the double value of the GDP of the country.
	 */
	
	public void setGDP(double gDP) {
		GDP = gDP;
	}

	/**
	 * This method gets the number of Covid cases of the country from the object's "cases" value.
	 * 
	 * @return The value of the Country object's "cases" double is returned.
	 */
	
	public double getCases() {
		return cases;
	}

	/**
	 * This method is used to set the value of the Country object's "cases" double.
	 * The parameter cases is taken and set to the "cases" value, holding the 
	 * number of Covid cases of the country in the real world. 
	 * 
	 * @param cases This parameter provides the double value of the Covid cases of the country.
	 */
	
	public void setCases(double cases) {
		this.cases = cases;
	}

	/**
	 * This method gets the number of Covid deaths of the country from the object's "deaths" value.
	 * 
	 * @return The value of the Country object's "deaths" double is returned.
	 */
	
	public double getDeaths() {
		return deaths;
	}

	/**
	 * This method is used to set the value of the Country object's "deaths" double.
	 * The parameter cases is taken and set to the "deaths" value, holding the 
	 * number of Covid deaths of the country in the real world. 
	 * 
	 * @param deaths This parameter provides the double value of the Covid deaths of the country.
	 */
	
	public void setDeaths(double deaths) {
		this.deaths = deaths;
	}

	/**
	 * This method gets the area of the country in km^2 from the object's "area" value.
	 * 
	 * @return The value of the Country object's "area" double is returned.
	 */
	
	public double getArea() {
		return area;
	}

	/**
	 * This method is used to set the value of the Country object's "area" double.
	 * The parameter area is taken and set to the "area" value, holding the 
	 * numerical area value in km^2 of the country in the real world. 
	 * 
	 * @param area This parameter provides the double value of the area of the country in km^2.
	 */
	
	public void setArea(double area) {
		this.area = area;
	}
	
	/**
	 * This method gets the GDPPC of the country from the object's "GDPPC" value.
	 * 
	 * @return The value of the Country object's "GDPPC" double is returned.
	 */
	
	public double getGDPPC() {
		return GDPPC;
	}

	/**
	 * This method is used to set the value of the Country object's "GDPPC" double.
	 * The parameter gDPPC is taken and set to the "GDPPC" value, holding the 
	 * numerical value of the GDP per capita of the country in the real world. 
	 * 
	 * @param gDPPC This parameter provides the double value of the GDPPC of the country.
	 */
	
	public void setGDPPC(double gDPPC) {
		GDPPC = gDPPC;
	}

	/**
	 * This method gets the Covid case fatality rate of the country from the object's "CFR" value.
	 * 
	 * @return The value of the Country object's "CFR" double is returned.
	 */
	
	public double getCFR() {
		return CFR;
	}

	/**
	 * This method is used to set the value of the Country object's "CFR" double.
	 * The parameter cFR is taken and set to the "CFR" value, holding the 
	 * numerical value of the Covid case fatality rate of the country in the real world. 
	 * 
	 * @param cFR This parameter provides the double value of the CFR of the country.
	 */
	
	public void setCFR(double cFR) {
		CFR = cFR;
	}

	/**
	 * This method gets the Covid case rate of the country from the object's "caseRate" value.
	 * 
	 * @return The value of the Country object's "caseRate" double is returned.
	 */
	
	public double getCaseRate() {
		return caseRate;
	}

	/**
	 * This method is used to set the value of the Country object's "caseRate" double.
	 * The parameter caseRate is taken and set to the "caseRate" value, holding the 
	 * number of Covid cases of the country in the real world. 
	 * 
	 * @param caseRate This parameter provides the double value of the caseRate of the country.
	 */
	
	public void setCaseRate(double caseRate) {
		this.caseRate = caseRate;
	}

	/**
	 * This method gets the Covid death rate of the country from the object's "deathRate" value.
	 * 
	 * @return The value of the Country object's "deathRate" double is returned.
	 */
	
	public double getDeathRate() {
		return deathRate;
	}

	/**
	 * This method is used to set the value of the Country object's "deathRate" double.
	 * The parameter deathRate is taken and set to the "deathRate" value, holding the 
	 * number of Covid deaths of the country in the real world. 
	 * 
	 * @param deathRate This parameter provides the double value of the deathRate of the country.
	 */
	
	public void setDeathRate(double deathRate) {
		this.deathRate = deathRate;
	}

	/**
	 * This method gets the population density of the country from the object's "popDens" value.
	 * 
	 * @return The value of the Country object's "popDens" double is returned.
	 */
	
	public double getPopDens() {
		return popDens;
	}

	/**
	 * This method is used to set the value of the Country object's "popDens" double.
	 * The parameter popDens is taken and set to the "popDens" value, holding the 
	 * numerical population density of the country in the real world. 
	 * 
	 * @param popDens This parameter provides the double value of the popDens of the country.
	 */
	
	public void setPopDens(double popDens) {
		this.popDens = popDens;
	}

	
}
