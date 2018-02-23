package Hinverter;

import edu.cross.ucsc.hse.core.chart.ChartConfiguration;
// import edu.cross.ucsc.hse.core.chart.ChartConfiguration;
import edu.ucsc.cross.hse.core.environment.Environment;
import edu.ucsc.cross.hse.core.setting.ComputationSettings.IntegratorType;
import edu.ucsc.cross.hse.core.task.TaskManager;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InverterRun extends TaskManager
{

	/*
	 * Queue method that executes everything listed within when application starts*
	 */
	@Override
	public void taskQueue()
	{
		// openEnvironmentAndPlot();
		runInverterSimulation();
	}

	/*
	 * Main class needed to run java application
	 */
	public static void main(String args[])
	{
		launch();
	}

	/*
	 * Fixes : Make event handler checking threshold smaller Make maximum & minimum step size smaller
	 * 
	 */
	public static Environment getConfiguredEnvironment()
	{

		Environment env = new Environment(); // Environment with default settings

		///// Execution Parameters

		// Maximum time allowed before execution terminates
		env.getSettings().getExecutionParameters().maximumTime = 2.0;
		// Maximum number of jumps allowed before execution terminates
		env.getSettings().getExecutionParameters().maximumJumps = 1000000;

		///// Log settings

		// Total progress updates to print, ie 10 prints an update at 10% intervals. -1 disables progress output
		env.getSettings().getLogSettings().numProgressUpdateOutputs = 10;
		// Inverval between status print outs
		env.getSettings().getLogSettings().statusPrintInterval = 2.0;
		// Print debug messages
		env.getSettings().getLogSettings().printDebug = true;
		// Print error messages
		env.getSettings().getLogSettings().printError = true;
		// Print information messages
		env.getSettings().getLogSettings().printInfo = true;
		// Print integrator exception warnings
		env.getSettings().getLogSettings().printIntegratorExceptions = true;
		// Print warning messages
		env.getSettings().getLogSettings().printWarning = true;

		///// Output settings

		// Flag indicating if file names should include the date at the end (for uniqueness)
		env.getSettings().getOutputSettings().appendFilesWithNumericDate = false;
		// Default image file format to be used when exporting charts with no format specified
		// env.getSettings().getOutputSettings().chartFileFormat = ImageFormat.EPS;
		// Name of the environment configuration file if it were to be saved
		env.getSettings().getOutputSettings().configurationFileName = "environmentConfig";
		// Time between data point storage
		env.getSettings().getOutputSettings().dataPointInterval = 1.0e-2;
		// Name of the environment file if it were to be saved
		env.getSettings().getOutputSettings().environmentFileName = "environment";
		// Location where results will be automatically stored if auto storage is enabled
		env.getSettings().getOutputSettings().outputDirectory = "outputzz";
		// Flag indicating if environment configuration should be saved in an output file when not specified by the user
		env.getSettings().getOutputSettings().saveChartsToFile = true;
		// Flag indicating if environment configuration should be saved in an output file when not specified by the user
		env.getSettings().getOutputSettings().saveConfigurationToFile = false;
		// Flag indicating if environment should be saved in an output file when not specified by the user
		env.getSettings().getOutputSettings().saveEnvironmentToFile = false;
		// Flag indicating if log should be saved to file
		env.getSettings().getOutputSettings().saveLogToFile = true;

		///// Computation Settings

		// Factor that event handling convergence value will be reduced when event handling error occurs
		env.getSettings().getComputationSettings().convergenceErrorCorrectionFactor = 1.0;
		// Convergence threshold of an event
		env.getSettings().getComputationSettings().eventHandlerConvergenceThreshold = 1.0e-15;
		// Event handler maximum interval to check for an event
		env.getSettings().getComputationSettings().eventHandlerMaximumCheckInterval = 1.0e-8;
		// Integrator type to be used
		env.getSettings().getComputationSettings().integratorType = IntegratorType.DORMAND_PRINCE_853;
		// Factor that event handling interval value will be reduced when event handling error occurs
		env.getSettings().getComputationSettings().intervalErrorCorrectionFactor = 1.0;
		// Factor that iteration count will be multiplied by when iteration count error occurs
		env.getSettings().getComputationSettings().iterationCountErrorCorrectionFactor = 2;
		// Maximum number of event handler iterations
		env.getSettings().getComputationSettings().maxEventHandlerIterations = 50;
		// Maximum step size for variable step integrator
		env.getSettings().getComputationSettings().odeMaximumStepSize = 1.0e-6;
		// Ode step size if using a fixed step integrator, or minimum ode step size of a variable step integrator
		env.getSettings().getComputationSettings().odeMinimumStepSize = 1.0e-8;
		// Relative tolerance of the ode solver
		env.getSettings().getComputationSettings().odeRelativeTolerance = 1.0e-6;
		// Absolute tolerance of the ode solver: this is the maximum
		env.getSettings().getComputationSettings().odeSolverAbsoluteTolerance = 1.0e-2;
		// Factor to reduce the maximum /fixed step size when a step size related error occurs
		env.getSettings().getComputationSettings().stepSizeErrorMaxCorrectionFactor = 1.0;
		// Factor to reduce minimum step size (if using variable step integrator) when a step size related error occurs
		env.getSettings().getComputationSettings().stepSizeErrorMinCorrectionFactor = 1.0;

		return env;
	}

	/*
	 * public static ChartConfiguration multiChart() { // Create a new plot configuration with specified width (600.0)
	 * and height (600.0) ChartConfiguration plot = new ChartConfiguration(1000.0, 800.0); // plot. // Set layout to
	 * generate two horizontal plots with plot 0 on top and plot 1 on the bottom plot.setLayout(new Integer[][] { { 0,
	 * 0, 0, 1, 2, 3 }, { 0, 0, 0, 4, 5, 5 } });
	 * 
	 * // Select data to display // * selections should be a string that matches the variable name of the data to be
	 * selected // * null is used to select time as the x axis values plot.chartProperties(0).setAxisSelections("iL",
	 * "vC"); plot.chartProperties(1).setAxisSelections(null, "p"); plot.chartProperties(2).setAxisSelections(null,
	 * "q"); plot.chartProperties(3).setAxisSelections(null, "iL"); plot.chartProperties(4).setAxisSelections(null,
	 * "vC"); plot.chartProperties(5).setAxisSelections(null, "tau"); // Select axis labels // * null is used to hide an
	 * axis label plot.chartProperties(0).setAxisLabels(null, null); plot.chartProperties(1).setAxisLabels(null, null);
	 * plot.chartProperties(2).setAxisLabels(null, null); plot.chartProperties(3).setAxisLabels(null, null);
	 * plot.chartProperties(4).setAxisLabels(null, null); plot.chartProperties(5).setAxisLabels(null, null);
	 * 
	 * // Specify legend visibility plot.chartProperties(0).setDisplayLegend(false);
	 * plot.chartProperties(1).setDisplayLegend(false); plot.chartProperties(2).setDisplayLegend(false);
	 * plot.chartProperties(3).setDisplayLegend(false); plot.chartProperties(4).setDisplayLegend(false);
	 * plot.chartProperties(5).setDisplayLegend(false);
	 * 
	 * // Specify overall title for the plot // * null is used to indicate no sub plot title // * there are no sub plot
	 * titles by default so following lines can be ommitted for no sub plot titles
	 * plot.chartProperties(0).setTitle("vC vs iL"); plot.chartProperties(1).setTitle("p");
	 * plot.chartProperties(2).setTitle("q"); plot.chartProperties(3).setTitle("iL");
	 * plot.chartProperties(4).setTitle("vC"); plot.chartProperties(5).setTitle("tau");
	 * 
	 * return plot; }
	 */
	public static ChartConfiguration multiChart()
	{
		// Create a new plot configuration with specified width (600.0) and height (600.0)
		ChartConfiguration plot = new ChartConfiguration(1000.0, 800.0);
		// plot.
		// Set layout to generate two horizontal plots with plot 0 on top and plot 1 on the bottom
		plot.setLayout(new Integer[][]
		{
				{ 0, 0, 0, 1, 2, 3 },
				{ 0, 0, 0, 4, 5, 5 } });

		// Select data to display
		// * selections should be a string that matches the variable name of the data to be selected
		// * null is used to select time as the x axis values
		plot.chartProperties(0).setAxisSelections("iL", "vC");
		plot.chartProperties(1).setAxisSelections(null, "p");
		plot.chartProperties(2).setAxisSelections(null, "q");
		plot.chartProperties(3).setAxisSelections(null, "iL");
		plot.chartProperties(4).setAxisSelections(null, "vC");
		plot.chartProperties(5).setAxisSelections(null, "tau");

		// Select axis labels
		// * null is used to hide an axis label
		plot.chartProperties(0).setAxisLabels(null, null);
		plot.chartProperties(1).setAxisLabels(null, null);
		plot.chartProperties(2).setAxisLabels(null, null);
		plot.chartProperties(3).setAxisLabels(null, null);
		plot.chartProperties(4).setAxisLabels(null, null);
		plot.chartProperties(5).setAxisLabels(null, null);

		// Specify legend visibility
		plot.chartProperties(0).setDisplayLegend(false);
		plot.chartProperties(1).setDisplayLegend(false);
		plot.chartProperties(2).setDisplayLegend(false);
		plot.chartProperties(3).setDisplayLegend(false);
		plot.chartProperties(4).setDisplayLegend(false);
		plot.chartProperties(5).setDisplayLegend(false);

		// Specify overall title for the plot
		// * null is used to indicate no sub plot title
		// * there are no sub plot titles by default so following lines can be ommitted for no sub plot titles
		plot.chartProperties(0).setTitle("vC vs iL");
		plot.chartProperties(1).setTitle("p");
		plot.chartProperties(2).setTitle("q");
		plot.chartProperties(3).setTitle("iL");
		plot.chartProperties(4).setTitle("vC");
		plot.chartProperties(5).setTitle("tau");

		return plot;
	}

	public static InverterSystem getTestInverterSystem()
	{
		InverterParameters params = InverterParameters.idealParameters(.05);
		Double p0 = 1.0;
		Double q0 = 1.0;
		// Double c = params.ci + Math.random() * (params.co - params.ci);
		Double tau0 = 0.0;
		Double iL0 = params.a * 1.0;
		Double vC0 = 0.0;// params.b * Math.sqrt(c - Math.pow((iL0 / params.a), 2));
		InverterState state = new InverterState(p0, q0, iL0, vC0, tau0);
		InverterSystem invSys = new InverterSystem(state, params);
		return invSys;
	}

	public void openEnvironmentAndPlot()
	{
		File envFile = new FileChooser().showOpenDialog(new Stage());
		Environment env = Environment.createEnvironment(envFile);
		// File datFile = new FileChooser().showOpenDialog(new Stage());
		// EnvironmentData dat = DataMonitor.getCSVData(datFile);
		// env.getData().load(dat.getStoreTimes(), dat.getGlobalStateData());
		// env.getOutputs().generateOutputs(env, true);
		// env.add(xyCombination());
		// System.out.println(XMLParser.serializeObject(env));
		// statesAndTimerChart().plot(env);
		// env.generateOutputs();
		multiChart().createChart(env);
		// env.add(HybridChart2);// .createChart(env);
		// env.generateOutputs();
	}

	public static void runInverterSimulation()
	{
		Environment env = getConfiguredEnvironment();
		InverterSystem s = getTestInverterSystem();
		env.add(s);
		// env.add(multiChart()); // adding the chart to the environment automatically generates output files
		env.start(.020, 1000000);
		multiChart().createChart(env);
		env.save(new File("makefile/file3/file4/yee"));
		env.getData().exportToCSVFile();
		env.getData().getSolution(s).exportToCSVFile(new File("makefile22/file/file/yee.csv"));
	}
}